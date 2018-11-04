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

import cz.nigol.zpravodaj.beans.SessionBean;

@Named
@RequestScoped
public class AuthPhaseListener implements PhaseListener {
    public static final long serialVersionUID = 1L;
    @Inject
    private Log log;
    @Inject
    private SessionBean sessionBean;
    
    public void beforePhase(PhaseEvent pe) {
        HttpServletRequest req = (HttpServletRequest) pe.getFacesContext().getExternalContext().getRequest();
        String uri = req.getRequestURI();
        boolean reject = pe.getPhaseId() == PhaseId.RESTORE_VIEW;
    }
    
    public void afterPhase(PhaseEvent pe) {
        
    }
    
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
