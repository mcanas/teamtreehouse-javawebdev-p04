package com.teamtreehouse.blog.model;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class BlogEntry {
    private String slug;
    private String title;
    private String body;
    private Date createdOn;
    private List<BlogComment> comments;
    private Set<String> tags;
    private String formattedCreatedOn;
    private String formattedTags;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMM d, yyyy 'at' h:mm a");

    public BlogEntry(String slug, String title, String body, Date createdOn) {
        this.slug = slug;
        this.title = title;
        this.body = body;
        this.createdOn = createdOn;
        this.comments = new ArrayList<>();
        this.tags = new TreeSet<>();
    }

    public BlogEntry(String slug, String title, String body, Date createdOn, Set<String> tags) {
        this.slug = slug;
        this.title = title;
        this.body = body;
        this.createdOn = createdOn;
        this.comments = new ArrayList<>();
        this.tags = tags;
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

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getFormattedCreatedOn() {
        return dateFormat.format(getCreatedOn());
    }

    public String getFormattedTags() {
        return tags.stream().map(t->"#"+t.toString()).collect(Collectors.joining(" "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogEntry blogEntry = (BlogEntry) o;

        if (!getSlug().equals(blogEntry.getSlug())) return false;
        if (!getTitle().equals(blogEntry.getTitle())) return false;
        if (!getBody().equals(blogEntry.getBody())) return false;
        if (!getCreatedOn().equals(blogEntry.getCreatedOn())) return false;
        if (getComments() != null ? !getComments().equals(blogEntry.getComments()) : blogEntry.getComments() != null)
            return false;
        return tags != null ? tags.equals(blogEntry.tags) : blogEntry.tags == null;

    }

    @Override
    public int hashCode() {
        int result = getSlug().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getBody().hashCode();
        result = 31 * result + getCreatedOn().hashCode();
        result = 31 * result + (getComments() != null ? getComments().hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }
}
