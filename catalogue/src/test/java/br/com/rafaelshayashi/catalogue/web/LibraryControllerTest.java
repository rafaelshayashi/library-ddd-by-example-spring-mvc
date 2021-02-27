package br.com.rafaelshayashi.catalogue.web;


import br.com.rafaelshayashi.catalogue.controller.LibraryController;
import br.com.rafaelshayashi.catalogue.controller.request.LibraryRequest;
import br.com.rafaelshayashi.catalogue.model.Address;
import br.com.rafaelshayashi.catalogue.model.Library;
import br.com.rafaelshayashi.catalogue.service.LibraryService;
import br.com.rafaelshayashi.catalogue.service.LibraryServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Arrays;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
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
public class LibraryControllerTest {

    @MockBean
    private LibraryService service;

    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(Library library) {
        try {
            return new ObjectMapper().writeValueAsString(library);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @Test
    @DisplayName("POST /libraries - Should create a library")
    public void should_create_a_library() throws Exception {

        Address addressMock = Address.builder().street("Rua da Consolação, 94").state("São Paulo").country("Brasil").zipCode("01302-000").build();
        Library libraryMock = Library.builder().name("Biblioteca mario de andrade").address(addressMock).build();

        doReturn(libraryMock).when(service).create(any(LibraryRequest.class));

        mockMvc.perform(post("/libraries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(libraryMock)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.is(("Biblioteca mario de andrade"))));
    }

    @Test
    @DisplayName("GET /libraries - Should get a list of libraries")
    public void should_get_a_list_of_libraries() throws Exception {
        Address addressMock = Address.builder().street("Rua da Consolação, 94").state("São Paulo").country("Brasil").zipCode("01302-000").build();
        Library libraryMock = Library.builder().name("Biblioteca mario de andrade").address(addressMock).build();
        List<Library> libraries = new ArrayList<>();
        libraries.add(libraryMock);
        PageImpl<Library> libraryPage = new PageImpl<>(libraries);

        doReturn(libraryPage).when(service).list(any());

        mockMvc.perform(get("/libraries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name", Matchers.is("Biblioteca mario de andrade")));

    }

}
