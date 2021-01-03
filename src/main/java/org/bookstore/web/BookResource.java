package org.bookstore.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.bookstore.service.BookService;
import org.bookstore.web.dto.Book;

@Path("/books")
@Produces({ "application/json" })
public class BookResource {

    @Inject
    BookService bookService;

    @GET
    @Path("")
    public List<Book> books() {
        return bookService.findAll().stream().map(this::mapDto).collect(Collectors.toList());
    }

    private Book mapDto(org.bookstore.model.Book book) {
        return new Book(book.getId(), book.getName());
    }

}
