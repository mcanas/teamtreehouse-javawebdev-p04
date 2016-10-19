package com.teamtreehouse.blog.controller;

import com.github.slugify.Slugify;
import com.teamtreehouse.blog.Routes;
import com.teamtreehouse.blog.exceptions.NotFoundException;
import com.teamtreehouse.blog.model.BlogComment;
import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.util.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlogEntryController extends BaseRouteController {

    private static Slugify slg = new Slugify();

    public static Route fetchSingleEntry = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        BlogEntry entry = BaseRouteController.dao.findEntryBySlug(req.params("slug"));

        if(entry == null) {
            throw new NotFoundException();
        }

        model.put("entry", entry);

        return ViewUtil.render(req, model, "detail.hbs");
    };

    public static Route renderNew = (Request req, Response res) -> ViewUtil.render(req, null, "new.hbs");

    public static Route renderEdit = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        BlogEntry entry = BaseRouteController.dao.findEntryBySlug(req.params("slug"));
        model.put("entry", entry);

        return ViewUtil.render(req, model, "edit.hbs");
    };

    public static Route addEntry = (Request req, Response res) -> {
        String title = req.queryParams("title");
        String slug = slg.slugify(title);
        String body = req.queryParams("body");
        Set<String> tags = parseTags(req.queryParams("tags").trim());

        BaseRouteController.dao.addEntry(new BlogEntry(
            slug,
            title,
            body,
            new Date(),
            tags
        ));

        res.redirect("/entry/"+slug);
        return null;
    };

    public static Route updateEntry = (Request req, Response res) -> {
        String slug = req.params("slug");
        String title = req.queryParams("title");
        String body = req.queryParams("body");
        Set<String> tags = parseTags(req.queryParams("tags").trim());

        BlogEntry entry = BaseRouteController.dao.findEntryBySlug(slug);

        entry.setTitle(title);
        entry.setBody(body);
        entry.setCreatedOn(new Date());
        entry.setTags(tags);

        res.redirect("/entry/"+slug);
        return null;
    };

    public static Route deleteEntry = (Request req, Response res) -> {
        String slug = req.params("slug");
        BlogEntry entry = BaseRouteController.dao.findEntryBySlug(slug);
        BaseRouteController.dao.removeEntry(entry);

        res.redirect(Routes.INDEX);
        return null;
    };

    public static Route fetchEntriesByTag = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        String tag = req.params("tag");

        model.put("tag", tag);
        model.put("entries", BaseRouteController.dao.findEntriesByTag(tag));

        return ViewUtil.render(req, model, "bytag.hbs");
    };

    public static Route addComment = (Request req, Response res) -> {
        String slug = req.params("slug");
        String name = req.queryParams("name");
        String comment = req.queryParams("comment");

        if(name.isEmpty()) {
            name = "Anonymous";
        }

        BaseRouteController.dao.addComment(slug, new BlogComment(
            name,
            comment,
            new Date()
        ));

        res.redirect("/entry/"+slug);
        return null;
    };

    private static Set<String> parseTags(String rawTags) {
        Set<String> tags = new TreeSet<>();
        Pattern p = Pattern.compile("#([\\w-]+)");
        Matcher m = p.matcher(rawTags);

        while(m.find()) {
            tags.add(m.group(1));
        }

        return tags;
    }
}
