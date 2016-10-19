package com.teamtreehouse.blog;

import com.teamtreehouse.blog.controller.AuthController;
import com.teamtreehouse.blog.controller.BlogEntryController;
import com.teamtreehouse.blog.controller.IndexController;
import com.teamtreehouse.blog.exceptions.NotFoundException;
import com.teamtreehouse.blog.util.Filters;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

public class BlogService {
    public static void main(String[] args) {
        staticFileLocation("/public");

        before((req, res) -> {
           if(req.cookie("username") != null) {
               req.attribute("username", req.cookie("username"));
           }
        });

        before(Routes.ENTRY_NEW, Filters.authenticate);
        before(Routes.ENTRY_EDIT, Filters.authenticate);

        get(Routes.INDEX, IndexController.fetchAllEntries);
        get(Routes.LOGIN, AuthController.renderLogin);

        get(Routes.ENTRY, BlogEntryController.fetchSingleEntry);
        get(Routes.ENTRY_NEW, BlogEntryController.renderNew);
        get(Routes.ENTRY_EDIT, BlogEntryController.renderEdit);
        get(Routes.BY_TAG, BlogEntryController.fetchEntriesByTag);

        post(Routes.ENTRY_NEW, BlogEntryController.addEntry);
        post(Routes.ENTRY_EDIT, BlogEntryController.updateEntry);
        post(Routes.ENTRY_DELETE, BlogEntryController.deleteEntry);

        post(Routes.LOGIN, AuthController.authenticate);
        post(Routes.COMMENT_NEW, BlogEntryController.addComment);

        exception(NotFoundException.class, (exc, req, res) -> {
            res.status(404);
            HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
            String html = engine.render(new ModelAndView(null, "404.hbs"));
            res.body(html);
        });
    }
}
