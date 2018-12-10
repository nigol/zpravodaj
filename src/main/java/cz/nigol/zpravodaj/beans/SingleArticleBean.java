package cz.nigol.zpravodaj.beans;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.zpravodaj.config.Configuration;
import cz.nigol.zpravodaj.entities.Article;
import cz.nigol.zpravodaj.services.ArticleService;

@Named
@RequestScoped
public class SingleArticleBean {
    @Inject
    private ArticleService articleService;
    @Resource
    private Configuration configuration;
    private String id;
    private Article article;
    private String articleUrl;

    public void onLoad() {
	articleUrl = configuration.getUrl() + "clanek.jsf?id=" + id;
	article = articleService.getArticleById(id);
	if (article != null) {
	    article = articleService.loadArticleBody(article);
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
}
