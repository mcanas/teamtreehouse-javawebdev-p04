package com.teamtreehouse.blog.controller;

import com.teamtreehouse.blog.dao.BlogDao;
import com.teamtreehouse.blog.dao.SimpleBlogDao;

public class BaseRouteController {
    public static BlogDao dao = new SimpleBlogDao();
}
