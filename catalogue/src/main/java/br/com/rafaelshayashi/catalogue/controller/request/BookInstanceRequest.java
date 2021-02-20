package br.com.rafaelshayashi.catalogue.controller.request;

import br.com.rafaelshayashi.catalogue.model.Book;
import br.com.rafaelshayashi.catalogue.model.BookInstance;
import br.com.rafaelshayashi.catalogue.model.Library;

import java.util.UUID;

public class BookInstanceRequest {

    private String bookUuid;
    private String libraryUuid;

    public UUID getBookUuid() {
        return UUID.fromString(bookUuid);
    }

    public void setBookUuid(String bookUuid) {
        this.bookUuid = bookUuid;
    }

    public UUID getLibraryUuid() {
        return UUID.fromString(libraryUuid);
    }

    public void setLibraryUuid(String libraryUuid) {
        this.libraryUuid = libraryUuid;
    }

    public BookInstance toModel(Book book, Library library) {
        return BookInstance.builder().book(book).library(library).build();
    }
}
