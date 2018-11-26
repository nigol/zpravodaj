package cz.nigol.zpravodaj.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.mindrot.jbcrypt.BCrypt;

import cz.nigol.zpravodaj.entities.User;
import cz.nigol.zpravodaj.enums.Role;
import cz.nigol.zpravodaj.services.UserService;

@Named
@ViewScoped
public class UsersBean implements Serializable {
    private static final long serialVersionUID = -9142079800335130185L;
    @Inject
    private UserService userService;
    @Inject
    private FacesContext facesContext;
    private List<User> users;
    private User user;
    private String password;

    @PostConstruct
    public void init() {
	users = userService.getAllUsers();
    }

    public void newUser() {
	user = new User();
	user.setId("new");
	users.add(user);
    }

    public void save() {
	if (password != null && !"".equals(password)) {
	    user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
	}
	userService.saveUser(user);
	init();
	user = null;
	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uloženo", "Uživatel byl uložen."));
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

    /**
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
	this.password = password;
    }
}
