package br.com.rafaelshayashi.library.web;

import br.com.rafaelshayashi.library.controller.request.BookInstanceRequest;
import br.com.rafaelshayashi.library.model.BookInstance;
import br.com.rafaelshayashi.library.model.LibraryBranch;
import br.com.rafaelshayashi.library.service.BookInstanceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BookInstanceControllerTest {

    @MockBean
    private BookInstanceService service;

    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(BookInstanceRequest library) {
        try {
            return new ObjectMapper().writeValueAsString(library);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @Test
    @DisplayName("POST /books/instances - Should create a book instance")
    void should_create_a_book_instance() throws Exception{

        LibraryBranch libraryBranchMock = LibraryBranch.builder()
                .name("The New York Public Library")
                .build();

        BookInstance bookInstanceMock = BookInstance
                .builder()
                .book(UUID.fromString("03e9ffcc-2191-4dd1-8d05-86976a5f431d"))
                .library(libraryBranchMock)
                .build();

        doReturn(bookInstanceMock).when(service).create(any());

        BookInstanceRequest request = new BookInstanceRequest();

        mockMvc.perform(post("/books/instances")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isCreated());
    }
}
