package cz.nigol.zpravodaj.producers;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
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
    @RequestScoped
    public User loggedUser() {
        return sessionBean.getUser();
    }
}
