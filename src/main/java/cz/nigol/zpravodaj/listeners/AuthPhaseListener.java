package cz.nigol.zpravodaj.listeners;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;

import cz.nigol.svj.beans.SessionBean;
import cz.nigol.svj.entities.User;
import cz.nigol.svj.qualifiers.LoggedUser;

@Named
@RequestScoped
public class AuthPhaseListener implements PhaseListener {
    public static final long serialVersionUID = 1L;
    @Inject
    private Log log;
    @Inject
    private SessionBean sessionBean;
    
    public void beforePhase(PhaseEvent pe) {
        User user = sessionBean.getUser();
        HttpServletRequest req = (HttpServletRequest) pe.getFacesContext().getExternalContext().getRequest();
        String uri = req.getRequestURI();
        boolean reject = pe.getPhaseId() == PhaseId.RESTORE_VIEW;
        reject = reject && uri.contains("admin") && (user == null || !user.isActive() || !user.isAdmin());
        reject = reject || uri.contains("auth") && (user == null || !user.isActive());
        reject = reject || uri.contains("owner") && 
            (user == null || !user.isActive() || !sessionBean.isUserOwner(user));
        if (reject) {
            FacesContext facesContext = pe.getFacesContext();
            facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "index.xhtml?faces-redirect=true");
            log.info("---UNAUTHORIZED ACCESS.");
        }
    }
    
    public void afterPhase(PhaseEvent pe) {
        
    }
    
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
