package cz.nigol.zpravodaj.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.zpravodaj.entities.Article;
import cz.nigol.zpravodaj.services.ArticleService;

@Named
@RequestScoped
public class AuthorBean {
    @Inject
    private ArticleService articleService;
    private String userId;
    private List<Article> articles = new ArrayList<>();

    public void onLoad() {
        articles = articleService.getPublishedArticlesByUserId(userId);
    }

    /**
     * @return the articleService
     */
    public ArticleService getArticleService() {
        return articleService;
    }

    /**
     * @param articleService the articleService to set
     */
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the articles
     */
    public List<Article> getArticles() {
        return articles;
    }

    /**
     * @param articles the articles to set
     */
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
