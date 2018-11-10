package cz.nigol.zpravodaj.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.zpravodaj.entities.Article;
import cz.nigol.zpravodaj.entities.User;
import cz.nigol.zpravodaj.qualifiers.LoggedUser;
import cz.nigol.zpravodaj.services.ArticleService;
import cz.nigol.zpravodaj.services.UserService;

@Named
@ViewScoped
public class ArticlesBean implements Serializable {
    private static final long serialVersionUID = 6524810090110687361L;
    @Inject
    @LoggedUser
    private User user;
    @Inject
    private UserService userService;
    @Inject
    private ArticleService articleService;
    private List<Article> articles;
    private List<Article> myArticles;
    private Article article;
    private String body;

    @PostConstruct
    public void init() {
	user = userService.getUserById(user.getId());
	articles = articleService.getAllArticles();
	myArticles = articleService.getArticlesByUser(user);
    }

    public void newArticle() {
	article = new Article();
	body = "";
    }

    public void save() {
	article.setChangedAt(new Date());
    }

    public void delete() {
	
    }

    public void onArticleSelect() {
	article = articleService.loadArticleBody(article);
	body = article.getBody();
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
     * @return the myArticles
     */
    public List<Article> getMyArticles() {
	return myArticles;
    }

    /**
     * @param myArticles the myArticles to set
     */
    public void setMyArticles(List<Article> myArticles) {
	this.myArticles = myArticles;
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
     * @return the body
     */
    public String getBody() {
	return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
	this.body = body;
    }
}
