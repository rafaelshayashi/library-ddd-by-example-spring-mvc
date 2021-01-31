package br.com.rafaelshayashi.catalogue.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private UUID uuid;
    @NotEmpty
    private String name;
    @Embedded
    private BookValue value;
    @NotEmpty
    private String isbn;

    public Book() {
    }

    public Book(String name, BookValue value, String isbn) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.value = value;
        this.isbn = isbn;
    }

    public static BookBuilder builder() {
        return new BookBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookValue getValue() {
        return value;
    }

    public void setValue(BookValue value) {
        this.value = value;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public static final class BookBuilder {
        private String name;
        private BookValue value;
        private String isbn;

        public BookBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BookBuilder value(BookValue value) {
            this.value = value;
            return this;
        }

        public BookBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Book build() {
            return new Book(name, value, isbn);
        }
    }
}
