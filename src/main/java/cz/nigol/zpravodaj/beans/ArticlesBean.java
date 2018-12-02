package cz.nigol.zpravodaj.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    @Inject
    private FacesContext facesContext;
    private List<Article> articles;
    private List<Article> myArticles;
    private Article article;
    private String body;
    private List<User> users;

    public static final String NEW_ID = "NewlyCreatedArticleId";

    @PostConstruct
    public void init() {
	user = userService.getUserById(user.getId());
	users = userService.getAllUsers();
	prepareArticleLists();
    }

    private void prepareArticleLists() {
	articles = articleService.getAllArticles();
	myArticles = articleService.getArticlesByUser(user);
    }

    public void newArticle() {
	article = new Article();
	article.setId(NEW_ID);
	body = "";
	myArticles.add(article);
    }

    public void save() {
	article.setChangedAt(new Date());
	if (NEW_ID.equals(article.getId())) {
	    article.setCreatedBy(user);
	    article.setId(article.getLabel());
	}
	if ("".equals(article.getFeaturedUrl())) {
	    article.setFeaturedUrl(null);
	}
	articleService.saveArticle(article, body);
	prepareArticleLists();
	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uloženo", "Článek byl uložen."));
    }

    public void publish(Article articleToPublish) {
	Article art = articleService.loadArticleBody(articleToPublish);
	art.setPublishedAt(new Date());
	articleService.saveArticle(art, art.getBody());
	prepareArticleLists();
	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Publikováno", "Publikováno s dnešním datem."));
    }

    public void unpublish(Article articleToPublish) {
	Article art = articleService.loadArticleBody(articleToPublish);
	art.setPublishedAt(null);
	articleService.saveArticle(art, art.getBody());
	prepareArticleLists();
	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Staženo", "Publikace byla zrušena."));
    }

    public void delete() {
	articleService.deleteArticle(article);
	article = null;
	body = null;
	prepareArticleLists();
	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Smazáno", "Článek byl smazán."));
    }

    public void onArticleSelect() {
	article = articleService.loadArticleBody(article);
	body = article.getBody();
    }

    public boolean isPreviewAllowed() {
	return article != null && !NEW_ID.equals(article.getId());
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

    /**
     * @return the users
     */
    public List<User> getUsers() {
	return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
	this.users = users;
    }
}
