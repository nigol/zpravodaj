package cz.nigol.zpravodaj.services;

import java.io.OutputStream;
import java.util.List;

import cz.nigol.zpravodaj.entities.Article;
import cz.nigol.zpravodaj.entities.User;
import cz.nigol.zpravodaj.enums.Category;

public interface ArticleService {
    List<Article> getAllArticles();
    Article getArticleById(String id);
    Article saveArticle(Article article, String body);
    Article loadArticleBody(Article article);
    List<Article> getArticlesByUser(User user);
    void deleteArticle(Article article);
    List<Article> getPublishedArticles();
    Article getFeaturedArticle();
    List<Article> getArticlesByCategory(Category category);
    void generateRss(OutputStream outputStream);
    List<Article> getLatestPublishedArticles();
    List<Article> getPublishedArticlesByUserId(String userId);
}
