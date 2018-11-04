package cz.nigol.zpravodaj.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import cz.nigol.zpravodaj.entities.User;

@Named
@SessionScoped
public class SessionBean implements Serializable {
    private static final long serialVersionUID = 9212583897602369531L;
	private User user;

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
