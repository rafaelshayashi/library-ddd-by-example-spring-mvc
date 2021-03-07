package br.com.rafaelshayashi.library.controller.request;

import br.com.rafaelshayashi.library.model.BookInstance;
import br.com.rafaelshayashi.library.model.LibraryBranch;

import java.util.UUID;

public class BookInstanceRequest {

    private String bookUuid;
    private String libraryUuid;

    public String getBookUuid() {
        return bookUuid;
    }

    public void setBookUuid(String bookUuid) {
        this.bookUuid = bookUuid;
    }

    public String getLibraryUuid() {
        return libraryUuid;
    }

    public void setLibraryUuid(String libraryUuid) {
        this.libraryUuid = libraryUuid;
    }

    public BookInstance toModel(UUID bookUuid, LibraryBranch library) {
        return BookInstance.builder().book(bookUuid).library(library).build();
    }
}
