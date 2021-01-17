package br.com.rafaelshayashi.catalogue.controller.request;

import br.com.rafaelshayashi.catalogue.model.Book;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BookRequest {

    @NotEmpty
    private String name;
    @NotNull
    private Integer value;
    @NotEmpty
    private String isbn;

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

    public Book toModel() {
        return Book.builder().name(name).value(value).isbn(isbn).build();
    }
}
