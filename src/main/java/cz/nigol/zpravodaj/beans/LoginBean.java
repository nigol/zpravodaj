package cz.nigol.zpravodaj.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginBean {
    private String loginName;
    private String password;

    public String login() {
	return "";
    }	

    /**
     * @return the loginName
     */
    public String getLoginName() {
	return loginName;
    }

    /**
     * @param loginName the loginName to set
     */
    public void setLoginName(String loginName) {
	this.loginName = loginName;
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
