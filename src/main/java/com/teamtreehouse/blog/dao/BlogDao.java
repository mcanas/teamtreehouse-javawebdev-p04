package com.teamtreehouse.blog.dao;

import com.teamtreehouse.blog.model.BlogComment;
import com.teamtreehouse.blog.model.BlogEntry;

import java.util.List;

public interface BlogDao {
    boolean addEntry(BlogEntry entry);
    boolean removeEntry(BlogEntry entry);
    List<BlogEntry> findAllEntries();
    BlogEntry findEntryBySlug(String slug);
    boolean addComment(String slug, BlogComment blogComment);
}
