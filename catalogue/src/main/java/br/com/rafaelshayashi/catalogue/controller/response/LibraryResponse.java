package br.com.rafaelshayashi.catalogue.controller.response;

import br.com.rafaelshayashi.catalogue.model.Address;
import br.com.rafaelshayashi.catalogue.model.Library;

import java.util.UUID;

public class LibraryResponse {

    public String uuid;
    public String name;
    public AddressResponse address;

    public LibraryResponse(Library library) {
        this(library.getUuid(), library.getName(), library.getAddress());
    }

    public LibraryResponse(UUID uuid, String name, Address address) {
        this.uuid = uuid.toString();
        this.name = name;
        this.address = AddressResponse.of(address);
    }

    public static LibraryResponse of(Library library) {
        return new LibraryResponse(library.getUuid(), library.getName(), library.getAddress());
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public AddressResponse getAddress() {
        return address;
    }
}
