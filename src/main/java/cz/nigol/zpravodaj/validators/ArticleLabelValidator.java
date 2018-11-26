package cz.nigol.zpravodaj.validators;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.zpravodaj.beans.ArticlesBean;
import cz.nigol.zpravodaj.services.ArticleService;

@Named
@ApplicationScoped
public class ArticleLabelValidator implements Validator {
    @Inject
    private ArticleService articleService;
    @Inject
    private ArticlesBean articlesBean;

    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
	String label = (String) arg2;
	if (ArticlesBean.NEW_ID.equals(articlesBean.getArticle().getId()) &&
	    articleService.getArticleById(label) != null) {
	    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
							  "Článek s tímto jménem již existuje.", null));
	}
    }
}
