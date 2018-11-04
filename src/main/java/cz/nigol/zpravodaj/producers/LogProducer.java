package cz.nigol.zpravodaj.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Named
@ApplicationScoped
public class LogProducer {

    @Produces
    public Log createLogger(InjectionPoint injectionPoint) {
        return LogFactory.getLog(injectionPoint.getMember().getDeclaringClass());
    }
}