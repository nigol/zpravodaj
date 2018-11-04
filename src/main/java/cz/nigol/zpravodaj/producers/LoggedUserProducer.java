package cz.nigol.zpravodaj.producers;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import cz.nigol.svj.beans.SessionBean;
import cz.nigol.svj.entities.User;
import cz.nigol.svj.qualifiers.LoggedUser;

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
