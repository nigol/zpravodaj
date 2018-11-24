package cz.nigol.zpravodaj.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import cz.nigol.zpravodaj.entities.Article;

@Named
@RequestScoped
public class IndexBean {
    private List<Article> articles;

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
