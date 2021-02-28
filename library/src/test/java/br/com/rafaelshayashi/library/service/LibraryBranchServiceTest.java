package br.com.rafaelshayashi.library.service;

import br.com.rafaelshayashi.library.controller.request.LibraryAddressRequest;
import br.com.rafaelshayashi.library.controller.request.LibraryBranchRequest;
import br.com.rafaelshayashi.library.exception.ResourceAlreadyExistsException;
import br.com.rafaelshayashi.library.model.Address;
import br.com.rafaelshayashi.library.model.LibraryBranch;
import br.com.rafaelshayashi.library.repository.LibraryBranchRepository;
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
class LibraryBranchServiceTest {

    @MockBean
    private LibraryBranchRepository repository;

    @Autowired
    private LibraryService service;

    @Test
    @DisplayName("Should create a library")
    void should_create_a_library() {
        Address addressMock = Address.builder().street("Rua da Consolação, 94").state("São Paulo").country("Brasil").zipCode("01302-000").build();
        LibraryBranch libraryMock = LibraryBranch.builder().name("Biblioteca mario de andrade").address(addressMock).build();

        Mockito.doReturn(Optional.empty()).when(repository).findByName(ArgumentMatchers.any(String.class));
        Mockito.doReturn(libraryMock).when(repository).save(ArgumentMatchers.any(LibraryBranch.class));

        LibraryBranchRequest libraryRequest = new LibraryBranchRequest();
        libraryRequest.setName("Biblioteca mario de andrade");
        LibraryAddressRequest libraryAddressRequest = new LibraryAddressRequest();
        libraryAddressRequest.setStreet("Rua da Consolação, 94");
        libraryAddressRequest.setState("São Paulo");
        libraryAddressRequest.setCountry("Brasil");
        libraryAddressRequest.setZipCode("01302-000");
        libraryRequest.setAddress(libraryAddressRequest);
        LibraryBranch library = service.create(libraryRequest);

        Assertions.assertEquals("Biblioteca mario de andrade", library.getName());
    }

    @Test
    @DisplayName("Try to create a already existing library")
    void try_to_create_a_already_existing_library() {
        LibraryBranchRequest libraryRequest = new LibraryBranchRequest();
        libraryRequest.setName("Biblioteca mario de andrade");
        LibraryAddressRequest libraryAddressRequest = new LibraryAddressRequest();
        libraryAddressRequest.setStreet("Rua da Consolação, 94");
        libraryAddressRequest.setState("São Paulo");
        libraryAddressRequest.setCountry("Brasil");
        libraryAddressRequest.setZipCode("01302-000");
        libraryRequest.setAddress(libraryAddressRequest);

        Address addressMock = Address.builder().street("Rua da Consolação, 94").state("São Paulo").country("Brasil").zipCode("01302-000").build();
        LibraryBranch libraryMock = LibraryBranch.builder().name("Biblioteca mario de andrade").address(addressMock).build();
        Mockito.doReturn(Optional.of(libraryMock)).when(repository).findByName(ArgumentMatchers.any(String.class));

        Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> service.create(libraryRequest));
    }
}
