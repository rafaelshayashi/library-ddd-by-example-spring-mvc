package br.com.rafaelshayashi.catalogue.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class BookInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Library library;

    public BookInstance() {
    }

    public BookInstance(Book book, Library library) {
        this.uuid = UUID.randomUUID();
        this.book = book;
        this.library = library;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public static BookInstanceBuilder builder(){
        return new BookInstanceBuilder();
    }

    public static class BookInstanceBuilder {
        private Book book;
        private Library library;

        public BookInstanceBuilder book(Book book){
            this.book = book;
            return this;
        }

        public BookInstanceBuilder library(Library library){
            this.library = library;
            return this;
        }

        public BookInstance build(){
            return new BookInstance(book, library);
        }
    }
}
