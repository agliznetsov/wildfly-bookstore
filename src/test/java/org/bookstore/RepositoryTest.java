package org.bookstore;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

import org.bookstore.app.Resources;
import org.bookstore.dao.BookRepository;
import org.bookstore.model.Book;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class RepositoryTest {
    @Deployment
    public static Archive<?> createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
            .addClass(Resources.class)
            .addPackage(Book.class.getPackage())
            .addPackage(BookRepository.class.getPackage())
            .addAsManifestResource("test-persistence.xml", "persistence.xml")
//            .addAsManifestResource("jbossas-ds.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        System.err.println(jar);
        return jar;
    }
 
    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction utx;

    @Inject
    BookRepository bookRepository;

    @Before
    public void preparePersistenceTest() throws Exception {
        startTransaction();
    }

    private void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }
    
    @After
    public void rollbackTransaction() throws Exception {
        utx.rollback();
    }

    @Test
    public void testSave() {
        Book book = bookRepository.save(new Book());
        assertNotNull(bookRepository.findById(book.getId()));
    }
}
