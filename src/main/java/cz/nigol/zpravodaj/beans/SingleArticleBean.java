package cz.nigol.zpravodaj.beans;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.zpravodaj.config.Configuration;
import cz.nigol.zpravodaj.entities.*;
import cz.nigol.zpravodaj.qualifiers.LoggedUser;
import cz.nigol.zpravodaj.services.ArticleService;

@Named
@RequestScoped
public class SingleArticleBean {
    @Inject
    private ArticleService articleService;
    @Resource
    private Configuration configuration;
    @Inject
    @LoggedUser
    private User user;
    private String id;
    private Article article;
    private String articleUrl;
    private Article featuredArticle;

    public void onLoad() {
        featuredArticle = articleService.getFeaturedArticle();
        articleUrl = configuration.getUrl() + "clanek.jsf?id=" + id;
        article = articleService.getArticleById(id);
        if (article != null) {
            article = articleService.loadArticleBody(article);
            if (article.getPublishedAt() == null && user.getId() == null) {
                article = null;
            }
        }
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the article
     */
    public Article getArticle() {
        return article;
    }

    /**
     * @param article the article to set
     */
    public void setArticle(Article article) {
        this.article = article;
    }

    /**
     * @return the articleUrl
     */
    public String getArticleUrl() {
        return articleUrl;
    }

    /**
     * @param articleUrl the articleUrl to set
     */
    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    /**
     * @return the featuredArticle
     */
    public Article getFeaturedArticle() {
        return featuredArticle;
    }

    /**
     * @param featuredArticle the featuredArticle to set
     */
    public void setFeaturedArticle(Article featuredArticle) {
        this.featuredArticle = featuredArticle;
    }
}
