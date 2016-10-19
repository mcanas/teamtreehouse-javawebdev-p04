package com.teamtreehouse.blog;

public class Routes {
    public static final String INDEX = "/";
    public static final String ENTRY = "/entry/:slug";
    public static final String ENTRY_NEW = "/new";
    public static final String ENTRY_EDIT = "/entry/:slug/edit";
    public static final String ENTRY_DELETE = "/entry/:slug/delete";
    public static final String BY_TAG = "/tag/:tag";
    public static final String COMMENT_NEW = "/entry/:slug/comment/new";
    public static final String LOGIN = "/login";

    public Routes() {}

    public static String getIndex() {
        return INDEX;
    }

    public static String getEntry() {
        return ENTRY;
    }

    public static String getEntryNew() {
        return ENTRY_NEW;
    }

    public static String getEntryEdit() {
        return ENTRY_EDIT;
    }

    public static String getEntryDelete() {
        return ENTRY_DELETE;
    }

    public static String getByTag() { return BY_TAG; }

    public static String getCommentNew() {
        return COMMENT_NEW;
    }

    public static String getLogin() {
        return LOGIN;
    }
}
