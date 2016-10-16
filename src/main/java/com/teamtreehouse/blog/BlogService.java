package com.teamtreehouse.blog;

import com.github.slugify.Slugify;
import com.teamtreehouse.blog.dao.BlogDao;
import com.teamtreehouse.blog.dao.SimpleBlogDao;
import com.teamtreehouse.blog.exceptions.NotFoundException;
import com.teamtreehouse.blog.model.BlogComment;
import com.teamtreehouse.blog.model.BlogEntry;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class BlogService {
    public static void main(String[] args) {
        staticFileLocation("/public");

        Slugify slg = new Slugify();
        BlogDao dao = new SimpleBlogDao();

        before((req, res) -> {
           if(req.cookie("username") != null) {
               req.attribute("username", req.cookie("username"));
           }
        });

        before("/new", (req, res) -> {
            if(req.attribute("username") == null) {
                req.session().attribute("referrer", req.uri());
                res.redirect("/login");
                halt();
            }
        });

        before("/entry/:slug/edit", (req, res) -> {
            if(req.attribute("username") == null) {
                req.session().attribute("referrer", req.uri());
                res.redirect("/login");
                halt();
            }
        });

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<BlogEntry> entries = dao.findAllEntries();
            model.put("entries", entries);

            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/entry/:slug", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            BlogEntry entry = dao.findEntryBySlug(req.params("slug"));

            if(entry == null) {
                throw new NotFoundException();
            }

            model.put("entry", entry);

            return new ModelAndView(model, "detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/entry/:slug/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            BlogEntry entry = dao.findEntryBySlug(req.params("slug"));
            model.put("entry", entry);

            return new ModelAndView(model, "edit.hbs");
        }, new HandlebarsTemplateEngine());

        get("/new", (req, res) -> new ModelAndView(null, "new.hbs"), new HandlebarsTemplateEngine());

        get("/login", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("referrer", req.session().attribute("referrer"));
            return new ModelAndView(model, "login.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new", (req, res) -> {
            String title = req.queryParams("title");
            String body = req.queryParams("body");
            String slug = slg.slugify(title);

            dao.addEntry(new BlogEntry(
                slug,
                title,
                body,
                new Date()
            ));

            res.redirect("/entry/"+slug);
            return null;
        });

        post("/entry/:slug/edit", (req, res) -> {
            String slug = req.params("slug");
            String title = req.queryParams("title");
            String body = req.queryParams("body");
            BlogEntry entry = dao.findEntryBySlug(slug);

            entry.setTitle(title);
            entry.setBody(body);
            entry.setCreatedOn(new Date());

            res.redirect("/entry/"+slug);
            return null;
        });

        post("/entry/:slug/delete", (req, res) -> {
            String slug = req.params("slug");
            BlogEntry entry = dao.findEntryBySlug(slug);
            dao.removeEntry(entry);

            res.redirect("/");
            return null;
        });

        post("/entry/:slug/comment/new", (req, res) -> {
            String slug = req.params("slug");
            String name = req.queryParams("name");
            String comment = req.queryParams("comment");

            if(name.isEmpty()) {
                name = "Anonymous";
            }

            dao.addComment(slug, new BlogComment(
                name,
                comment,
                new Date()
            ));

            res.redirect("/entry/"+slug);
            return null;
        });

        post("/login", (req, res) -> {
            if(req.queryParams("password").equals("admin")) {
                res.cookie("username", "admin");
                res.redirect(req.session().attribute("referrer"));
            } else {
                res.redirect("/login");
            }

            return null;
        });

        exception(NotFoundException.class, (exc, req, res) -> {
            res.status(404);
            HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
            String html = engine.render(new ModelAndView(null, "404.hbs"));
            res.body(html);
        });
    }
}
