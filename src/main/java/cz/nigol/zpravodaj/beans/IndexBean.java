package cz.nigol.zpravodaj.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.zpravodaj.entities.Article;
import cz.nigol.zpravodaj.services.ArticleService;

@Named
@RequestScoped
public class IndexBean {
    @Inject
    private ArticleService articleService;
    private List<Article> articles;

    @PostConstruct
    public void init() {
	articles = articleService.getPublishedArticles();
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