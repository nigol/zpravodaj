package cz.nigol.zpravodaj.beans;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.zpravodaj.entities.Article;
import cz.nigol.zpravodaj.enums.Category;
import cz.nigol.zpravodaj.services.ArticleService;

@Named
@RequestScoped
public class IndexBean {
    @Inject
    private ArticleService articleService;
    @Inject
    private FacesContext facesContext;
    private List<Article> articles;
    private Article featuredArticle;
    private String categoryId;
    private Category category;
    private List<Article> categoryArticles;
    private String rss;

    @PostConstruct
    public void init() {
	articles = articleService.getPublishedArticles();
	featuredArticle = articleService.getFeaturedArticle();
    }

    public void onLoad() throws IOException {
	if (rss != null && "true".equals(rss)) {
	    generateRssChannel();
	}
	if (categoryId != null) {
	    category = Category.valueOf(categoryId);
	    categoryArticles = articleService.getArticlesByCategory(category);
	}
    }

    public void generateRssChannel() throws IOException {
	ExternalContext externalContext = facesContext.getExternalContext();
	externalContext.responseReset();
	externalContext.setResponseContentType("application/rss+xml");
	OutputStream outputStream = externalContext.getResponseOutputStream();
	articleService.generateRss(outputStream);
	outputStream.close();
	facesContext.responseComplete();
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

    /**
     * @return the categoryId
     */
    public String getCategoryId() {
	return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(String categoryId) {
	this.categoryId = categoryId;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
	return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
	this.category = category;
    }

    /**
     * @return the categoryArticles
     */
    public List<Article> getCategoryArticles() {
	return categoryArticles;
    }

    /**
     * @param categoryArticles the categoryArticles to set
     */
    public void setCategoryArticles(List<Article> categoryArticles) {
	this.categoryArticles = categoryArticles;
    }

    /**
     * @return the rss
     */
    public String getRss() {
	return rss;
    }

    /**
     * @param rss the rss to set
     */
    public void setRss(String rss) {
	this.rss = rss;
    }
}
