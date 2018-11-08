package cz.nigol.zpravodaj.services;

import java.util.List;

import cz.nigol.zpravodaj.entities.Article;

public interface ArticleService {
    List<Article> getAllArticles();
    Article getArticleById(String id);
    Article saveArticle(Article article, String body);
    Article loadArticleBody(Article article);
}
