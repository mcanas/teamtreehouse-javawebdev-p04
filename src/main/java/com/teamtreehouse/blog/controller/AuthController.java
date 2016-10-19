package com.teamtreehouse.blog.controller;

import com.teamtreehouse.blog.Routes;
import com.teamtreehouse.blog.util.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class AuthController extends BaseRouteController {

    public static Route renderLogin = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("referrer", req.session().attribute("referrer"));
        return ViewUtil.render(req, model, "login.hbs");
    };

    public static Route authenticate = (Request req, Response res) -> {
        if(req.queryParams("password").equals("admin")) {
            res.cookie("username", "admin");
            res.redirect(req.session().attribute("referrer"));
        } else {
            res.redirect(Routes.LOGIN);
        }

        return null;
    };
}
