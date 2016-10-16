package com.teamtreehouse.blog.model;

import java.util.Date;

public class BlogComment {
    private String name;
    private String comment;
    private Date createdOn;

    public BlogComment(String name, String comment, Date createdOn) {
        this.name = name;
        this.comment = comment;
        this.createdOn = createdOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogComment that = (BlogComment) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (!getComment().equals(that.getComment())) return false;
        return getCreatedOn().equals(that.getCreatedOn());

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getComment().hashCode();
        result = 31 * result + getCreatedOn().hashCode();
        return result;
    }
}
