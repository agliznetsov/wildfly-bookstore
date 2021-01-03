package org.bookstore.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.bookstore.dao.BookRepository;
import org.bookstore.model.Book;

@Transactional
@ApplicationScoped
public class BookService {

	@Inject
	BookRepository bookRepository;

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book save(Book book) {
		return bookRepository.save(book);
	}
}
