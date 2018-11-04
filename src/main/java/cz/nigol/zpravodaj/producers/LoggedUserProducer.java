package cz.nigol.zpravodaj.producers;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.zpravodaj.beans.SessionBean;
import cz.nigol.zpravodaj.entities.User;
import cz.nigol.zpravodaj.qualifiers.LoggedUser;

@Named
@ApplicationScoped
class LoggedUserProducer {
    @Inject
    private SessionBean sessionBean;

    @Produces
    @LoggedUser
    @SessionScoped
    public User loggedUser() {
        return sessionBean.getUser();
    }
}
