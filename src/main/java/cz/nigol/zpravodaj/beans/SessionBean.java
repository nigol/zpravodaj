package cz.nigol.zpravodaj.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.zpravodaj.entities.User;

@Named
@SessionScoped
public class SessionBean implements Serializable {
    private static final long serialVersionUID = 9212583897602369531L;
    @Inject
    private FacesContext facesContext;
    private User user = new User();

    public String logout() {
	user = null;
	facesContext.getExternalContext().invalidateSession();
	return "/index.xhtml?faces-redirect=true";
    }

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
