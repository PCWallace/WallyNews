package com.example.wallace.wallynews.Model;

import java.util.List;

/**
 * Created by wallace on 3/2/18.
 */

public class News {
    private String status;
    private String source;
    private String sortBy;
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
