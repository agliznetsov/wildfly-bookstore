package org.bookstore;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.bookstore.model.Book;
import org.bookstore.service.BookService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.archive.importer.MavenImporter;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ServiceTest {
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(MavenImporter.class)
                .loadPomFromFile("pom.xml")
                .importBuildOutput()
                .as(WebArchive.class);
    }
 
    @Inject
    BookService bookService;

    @Test
    public void testSave() {
        Book book = bookService.save(new Book());
        assertTrue(bookService.findAll().size() > 0);
    }
}
