package org.bookstore.app;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class Resources {

    @Produces
    @PersistenceContext
    EntityManager em;

//    @Resource(lookup = "java:/TransactionManager")
//    TransactionManager transactionManager;

}
