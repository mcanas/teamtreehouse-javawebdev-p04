package com.teamtreehouse.blog.util;

import com.teamtreehouse.blog.Routes;
import spark.Filter;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class Filters {
    public static Filter authenticate = (Request req, Response res) -> {
        if(req.attribute("username") == null) {
            req.session().attribute("referrer", req.headers("referer"));
            res.redirect(Routes.LOGIN);
            halt();
        }
    };
}
