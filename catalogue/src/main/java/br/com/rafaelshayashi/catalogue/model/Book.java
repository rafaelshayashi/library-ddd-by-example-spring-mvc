package br.com.rafaelshayashi.catalogue.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NotNull
    private Integer value;
    @NotEmpty
    private String isbn;

    public Book() {
    }

    public Book(String name, Integer value, String isbn) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.value = value;
        this.isbn = isbn;
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

    public static FluentBuilder builder() {
        return new FluentBuilder();
    }

    public static class FluentBuilder {
        private String name;
        private Integer value;
        private String isbn;

        public FluentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FluentBuilder value(Integer value){
            this.value = value;
            return this;
        }

        public FluentBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Book build() {
            return new Book(name, value, isbn);
        }
    }
}
