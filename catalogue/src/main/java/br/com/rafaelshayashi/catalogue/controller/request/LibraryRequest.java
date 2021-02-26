package br.com.rafaelshayashi.catalogue.controller.request;

import br.com.rafaelshayashi.catalogue.model.Library;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LibraryRequest {

    @NotEmpty
    private String name;
    @NotNull
    private LibraryAddressRequest address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LibraryAddressRequest getAddress() {
        return address;
    }

    public void setAddress(LibraryAddressRequest address) {
        this.address = address;
    }

    public Library toModel() {
        return Library.builder().name(name).address(address.toModel()).build();
    }
}
