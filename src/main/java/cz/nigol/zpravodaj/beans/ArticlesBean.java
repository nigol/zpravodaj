package cz.nigol.zpravodaj.beans;

import java.io.Serializable;
import java.text.Normalizer;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.*;

import cz.nigol.zpravodaj.entities.*;
import cz.nigol.zpravodaj.qualifiers.LoggedUser;
import cz.nigol.zpravodaj.services.*;

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
    private boolean editSource;

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
        article.setEditSource(editSource);
        if (NEW_ID.equals(article.getId())) {
            article.setCreatedBy(user);
            String normalized = Normalizer.normalize(article.getLabel(), 
                    Normalizer.Form.NFKD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
            article.setId(normalized.replaceAll(" ", "-"));
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
        editSource = article.isEditSource();
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

    /**
     * @return the editSource
     */
    public boolean isEditSource() {
        return editSource;
    }

    /**
     * @param editSource the editSource to set
     */
    public void setEditSource(boolean editSource) {
        this.editSource = editSource;
    }
}
