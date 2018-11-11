package cz.nigol.zpravodaj.services;

import java.util.List;

import cz.nigol.zpravodaj.entities.Article;
import cz.nigol.zpravodaj.entities.User;

public interface ArticleService {
    List<Article> getAllArticles();
    Article getArticleById(String id);
    Article saveArticle(Article article, String body);
    Article loadArticleBody(Article article);
    List<Article> getArticlesByUser(User user);
    void deleteArticle(Article article);
}
