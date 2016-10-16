package com.teamtreehouse.blog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogEntry {
    private String slug;
    private String title;
    private String body;
    private Date createdOn;
    private List<BlogComment> comments;

    public BlogEntry(String slug, String title, String body, Date createdOn) {
        this.slug = slug;
        this.title = title;
        this.body = body;
        this.createdOn = createdOn;
        this.comments = new ArrayList<>();
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public List<BlogComment> getComments() {
        return comments;
    }

    public boolean addComment(BlogComment blogComment) {
        return comments.add(blogComment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogEntry blogEntry = (BlogEntry) o;

        if (!getSlug().equals(blogEntry.getSlug())) return false;
        if (!getTitle().equals(blogEntry.getTitle())) return false;
        if (!getBody().equals(blogEntry.getBody())) return false;
        return getCreatedOn().equals(blogEntry.getCreatedOn());

    }

    @Override
    public int hashCode() {
        int result = getSlug().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getBody().hashCode();
        result = 31 * result + getCreatedOn().hashCode();
        return result;
    }
}
