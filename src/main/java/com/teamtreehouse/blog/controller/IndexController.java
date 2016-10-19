package com.teamtreehouse.blog.controller;

import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.util.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexController extends BaseRouteController {

    public static Route fetchAllEntries = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        List<BlogEntry> entries = BaseRouteController.dao.findAllEntries();
        model.put("entries", entries);

        return ViewUtil.render(req, model, "index.hbs");
    };
}
