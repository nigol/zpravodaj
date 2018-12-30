package cz.nigol.zpravodaj.listeners;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

@Named
@ApplicationScoped
public class HttpHeaderPhaseListener implements PhaseListener {
    private static final long serialVersionUID = -7258056513685862543L;

    @Override
    public void afterPhase(PhaseEvent event) {
        // nothing to do
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        HttpServletResponse response = (HttpServletResponse) event.getFacesContext().getExternalContext().getResponse();
        // Activate XSS Protection even if it is turned of in IE settings
        response.addHeader("X-XSS-Protection", "1; mode=block");
        // prevent display in iframe
        response.addHeader("X-Frame-Options", "SAMEORIGIN");
        // prevent java script execution for certain content types
        response.addHeader("X-Content-Type-Options", "nosniff");
	// control referrer
	response.addHeader("Referrer-Policy", "no-referrer-when-downgrade");
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
}
