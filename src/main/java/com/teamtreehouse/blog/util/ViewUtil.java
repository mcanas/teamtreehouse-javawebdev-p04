package com.teamtreehouse.blog.util;

import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

public class ViewUtil {

    public static String render(Request req, Map<String, Object> model, String templatePath) {
        return (new HandlebarsTemplateEngine()).render(new ModelAndView(model, templatePath));
    }
}
