package br.com.rafaelshayashi.catalogue.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @NotEmpty
    private String name;
    @Embedded
    @NotNull
    private Address address;

    public Library() {
    }

    public Library(String name, Address address) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static LibraryBuilder builder(){
        return new LibraryBuilder();
    }

    public static class LibraryBuilder {

        private String name;
        private Address address;

        public LibraryBuilder(){

        }

        public LibraryBuilder name(String name){
            this.name = name;
            return this;
        }

        public LibraryBuilder address(Address address){
            this.address = address;
            return this;
        }

        public Library build() {
            return new Library(name, address);
        }
    }
}
