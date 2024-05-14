package org.varelacasas.configs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.varelacasas.util.JpaUtil;
import java.util.logging.Logger;

@ApplicationScoped
public class ProducerResources {

    @Inject
    private Logger log;

    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint){
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    @Produces
    @RequestScoped
    private EntityManager beanEntityManager(){
        return JpaUtil.getEntityManager();
    }

    public void close(@Disposes EntityManager entityManager){
        if(entityManager.isOpen()){
            entityManager.close();
            log.info(" === ¡ cerrando la conexión a la bbdd mysql ! ====");
        }
    }

}
