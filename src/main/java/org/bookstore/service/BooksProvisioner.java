package org.bookstore.service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.bookstore.model.Book;

@Startup
@Singleton
public class BooksProvisioner {
	@Inject
	BookService bookService;

	@PostConstruct
	public void init() {
		Book book = new Book();
		book.setName("test");
		bookService.save(book);
	}
}
