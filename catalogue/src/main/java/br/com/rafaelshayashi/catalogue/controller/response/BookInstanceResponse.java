package br.com.rafaelshayashi.catalogue.controller.response;

import br.com.rafaelshayashi.catalogue.model.BookInstance;

public class BookInstanceResponse {

    private final String uuid;
    private final BookResponse book;
    private final LibraryResponse library;

    public BookInstanceResponse(BookInstance bookInstance) {
        this.uuid = bookInstance.getUuid().toString();
        this.book = BookResponse.of(bookInstance.getBook());
        this.library = LibraryResponse.of(bookInstance.getLibrary());
    }

    public static BookInstanceResponse of(BookInstance bookInstance) {
        return new BookInstanceResponse(bookInstance);
    }

    public String getUuid() {
        return uuid;
    }

    public BookResponse getBook() {
        return book;
    }

    public LibraryResponse getLibrary() {
        return library;
    }
}
