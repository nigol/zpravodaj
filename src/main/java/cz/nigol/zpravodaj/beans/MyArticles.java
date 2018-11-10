package cz.nigol.zpravodaj.beans;

import java.io.Serializable;
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
public class MyArticles implements Serializable {
    private static final long serialVersionUID = 6524810090110687361L;
    @Inject
    @LoggedUser
    private User user;
    @Inject
    private UserService userService;
    @Inject
    private ArticleService articleService;
    private List<Article> articles;

    @PostConstruct
    public void init() {
	user = userService.getUserById(user.getId());
	articles = articleService.getArticlesByUser(user);
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
