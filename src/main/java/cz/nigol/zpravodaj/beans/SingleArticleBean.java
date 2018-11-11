package cz.nigol.zpravodaj.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.zpravodaj.entities.Article;
import cz.nigol.zpravodaj.services.ArticleService;

@Named
@RequestScoped
public class SingleArticleBean {
    @Inject
    private ArticleService articleService;
    private String id;
    private Article article;

    public void onLoad() {
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
}
