package org.bookstore.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.bookstore.model.Book;

@ApplicationScoped
public class BookRepository {
	final AtomicLong counter = new AtomicLong();

	@Inject
	EntityManager em;

	public Book save(Book book) {
		book.setId(counter.incrementAndGet());
		em.persist(book);
		return book;
	}

	public Book findById(long id) {
		return em.find(Book.class, id);
	}

	public List<Book> findAll() {
		return em.createQuery("select b from Book b").getResultList();
	}
}
