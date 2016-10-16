package com.teamtreehouse.blog.dao;

import com.teamtreehouse.blog.model.BlogComment;
import com.teamtreehouse.blog.model.BlogEntries;
import com.teamtreehouse.blog.model.BlogEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleBlogDao implements BlogDao {
    List<BlogEntry> entries;

    public SimpleBlogDao() {
        entries = new ArrayList<>(Arrays.asList(BlogEntries.load()));
    }

    @Override
    public boolean addEntry(BlogEntry entry) {
        return entries.add(entry);
    }

    @Override
    public boolean removeEntry(BlogEntry entry) {
        return entries.remove(entry);
    }

    @Override
    public List<BlogEntry> findAllEntries() {
        return entries;
    }

    @Override
    public BlogEntry findEntryBySlug(String slug) {
        return entries.stream()
                .filter(e -> slug.equals(e.getSlug()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean addComment(String slug, BlogComment blogComment) {
        BlogEntry entry = findEntryBySlug(slug);
        return entry.addComment(blogComment);
    }
}
