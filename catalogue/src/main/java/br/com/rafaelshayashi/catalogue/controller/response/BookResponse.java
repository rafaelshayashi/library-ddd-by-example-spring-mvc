package br.com.rafaelshayashi.catalogue.controller.response;

import br.com.rafaelshayashi.catalogue.model.Book;

public class BookResponse {

    private final String uuid;
    private final String name;
    private final BookValueResponse value;
    private final String isbn;

    public BookResponse(Book book) {
        this.uuid = book.getUuid().toString();
        this.name = book.getName();
        this.value = BookValueResponse.of(book.getValue());
        this.isbn = book.getIsbn();
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public BookValueResponse getValue() {
        return value;
    }

    public String getIsbn() {
        return isbn;
    }

    public static BookResponse of(Book book) {
        return new BookResponse(book);
    }
}
