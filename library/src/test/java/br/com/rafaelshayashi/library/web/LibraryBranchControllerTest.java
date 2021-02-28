package br.com.rafaelshayashi.library.web;

import br.com.rafaelshayashi.library.controller.request.LibraryBranchRequest;
import br.com.rafaelshayashi.library.model.Address;
import br.com.rafaelshayashi.library.model.LibraryBranch;
import br.com.rafaelshayashi.library.service.LibraryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LibraryBranchControllerTest {

    private static final String RESOURCE_URL = "/branches";
    @MockBean
    private LibraryService service;
    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(LibraryBranch library) {
        try {
            return new ObjectMapper().writeValueAsString(library);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @Test
    @DisplayName("POST /libraries - Should create a library")
    void should_create_a_library() throws Exception {

        Address addressMock = Address.builder().street("Rua da Consolação, 94").state("São Paulo").country("Brasil").zipCode("01302-000").build();
        LibraryBranch libraryMock = LibraryBranch.builder().name("Biblioteca mario de andrade").address(addressMock).build();

        doReturn(libraryMock).when(service).create(any(LibraryBranchRequest.class));

        mockMvc.perform(post(RESOURCE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(libraryMock)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.is(("Biblioteca mario de andrade"))));
    }

    @Test
    @DisplayName("GET /libraries - Should get a list of libraries")
    void should_get_a_list_of_libraries() throws Exception {
        Address addressMock = Address.builder().street("Rua da Consolação, 94").state("São Paulo").country("Brasil").zipCode("01302-000").build();
        LibraryBranch libraryMock = LibraryBranch.builder().name("Biblioteca mario de andrade").address(addressMock).build();
        List<LibraryBranch> libraries = new ArrayList<>();
        libraries.add(libraryMock);
        PageImpl<LibraryBranch> libraryPage = new PageImpl<>(libraries);

        doReturn(libraryPage).when(service).list(any());

        mockMvc.perform(get(RESOURCE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name", Matchers.is("Biblioteca mario de andrade")));

    }
}
