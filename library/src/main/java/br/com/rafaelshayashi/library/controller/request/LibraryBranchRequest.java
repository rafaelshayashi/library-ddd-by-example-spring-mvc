package br.com.rafaelshayashi.library.controller.request;

import br.com.rafaelshayashi.library.model.LibraryBranch;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LibraryBranchRequest {

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

    public LibraryBranch toModel() {
        return LibraryBranch.builder().name(name).address(address.toModel()).build();
    }
}
