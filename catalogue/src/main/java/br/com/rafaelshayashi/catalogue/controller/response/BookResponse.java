package br.com.rafaelshayashi.catalogue.controller.response;

import br.com.rafaelshayashi.catalogue.model.Book;

public class BookResponse {

    private String uuid;
    private String name;
    private Integer value;
    private String isbn;

    public BookResponse(Book book) {
        this.uuid = book.getUuid().toString();
        this.name = book.getName();
        this.value = book.getValue();
        this.isbn = book.getIsbn();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public static BookResponse of(Book book) {
        return new BookResponse(book);
    }
}
