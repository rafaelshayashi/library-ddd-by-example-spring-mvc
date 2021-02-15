package br.com.rafaelshayashi.catalogue.controller.request;

import br.com.rafaelshayashi.catalogue.model.Book;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BookRequest {

    @NotEmpty
    private String title;
    private String subTitle;
    @NotNull
    private BookValueRequest value;
    private String description;
    @NotEmpty
    private String isbn;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public BookValueRequest getValue() {
        return value;
    }

    public void setValue(BookValueRequest value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Book toModel() {
        return Book.builder()
                .title(title)
                .subTitle(subTitle)
                .value(value.toModel())
                .description(description)
                .isbn(isbn)
                .build();
    }
}
