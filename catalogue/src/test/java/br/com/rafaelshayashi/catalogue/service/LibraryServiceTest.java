package br.com.rafaelshayashi.catalogue.service;

import br.com.rafaelshayashi.catalogue.controller.request.LibraryAddressRequest;
import br.com.rafaelshayashi.catalogue.controller.request.LibraryRequest;
import br.com.rafaelshayashi.catalogue.model.Address;
import br.com.rafaelshayashi.catalogue.model.Library;
import br.com.rafaelshayashi.catalogue.repository.LibraryRepository;
import br.com.rafaelshayashi.catalogue.service.LibraryService;
import br.com.rafaelshayashi.catalogue.service.LibraryServiceImpl;
import br.com.rafaelshayashi.catalogue.util.exception.ResourceAlreadyExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryServiceImpl.class)
public class LibraryServiceTest {

    @MockBean
    private LibraryRepository repository;

    @Autowired
    private LibraryService service;

    @Test
    @DisplayName("Should create a library")
    public void should_create_a_library() {
        Address addressMock = Address.builder().street("Rua da Consolação, 94").state("São Paulo").country("Brasil").zipCode("01302-000").build();
        Library libraryMock = Library.builder().name("Biblioteca mario de andrade").address(addressMock).build();

        Mockito.doReturn(Optional.empty()).when(repository).findByName(ArgumentMatchers.any(String.class));
        Mockito.doReturn(libraryMock).when(repository).save(ArgumentMatchers.any(Library.class));

        LibraryRequest libraryRequest = new LibraryRequest();
        libraryRequest.setName("Biblioteca mario de andrade");
        LibraryAddressRequest libraryAddressRequest = new LibraryAddressRequest();
        libraryAddressRequest.setStreet("Rua da Consolação, 94");
        libraryAddressRequest.setState("São Paulo");
        libraryAddressRequest.setCountry("Brasil");
        libraryAddressRequest.setZipCode("01302-000");
        libraryRequest.setAddress(libraryAddressRequest);
        Library library = service.create(libraryRequest);

        Assertions.assertEquals("Biblioteca mario de andrade", library.getName());
    }

    @Test
    @DisplayName("Try to create a already existing library")
    public void try_to_create_a_already_existing_library() {
        LibraryRequest libraryRequest = new LibraryRequest();
        libraryRequest.setName("Biblioteca mario de andrade");
        LibraryAddressRequest libraryAddressRequest = new LibraryAddressRequest();
        libraryAddressRequest.setStreet("Rua da Consolação, 94");
        libraryAddressRequest.setState("São Paulo");
        libraryAddressRequest.setCountry("Brasil");
        libraryAddressRequest.setZipCode("01302-000");
        libraryRequest.setAddress(libraryAddressRequest);

        Address addressMock = Address.builder().street("Rua da Consolação, 94").state("São Paulo").country("Brasil").zipCode("01302-000").build();
        Library libraryMock = Library.builder().name("Biblioteca mario de andrade").address(addressMock).build();
        Mockito.doReturn(Optional.of(libraryMock)).when(repository).findByName(ArgumentMatchers.any(String.class));

        Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> service.create(libraryRequest));
    }
}
