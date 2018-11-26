package cz.nigol.zpravodaj.converters;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.zpravodaj.entities.User;
import cz.nigol.zpravodaj.services.UserService;

@Named
@ApplicationScoped
public class UserConverter implements Converter {
    @Inject
    private UserService userService;

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
	User result = null;
	if (arg2 != null && !"".equals(arg2)) {
	    result = userService.getUserById(arg2);
	}
	return result;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
	String result = "";
	if (arg2 != null) {
	    User user = (User) arg2;
	    result = user.getId();
	}
	return result;
    }
}
