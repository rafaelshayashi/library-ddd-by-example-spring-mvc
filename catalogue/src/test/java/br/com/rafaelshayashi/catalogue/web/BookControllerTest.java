package br.com.rafaelshayashi.catalogue.web;

import br.com.rafaelshayashi.catalogue.controller.request.BookRequest;
import br.com.rafaelshayashi.catalogue.model.Book;
import br.com.rafaelshayashi.catalogue.model.BookValue;
import br.com.rafaelshayashi.catalogue.model.UnitTypeEnum;
import br.com.rafaelshayashi.catalogue.service.BookService;
import br.com.rafaelshayashi.catalogue.util.exception.ResourceAlreadyExistsException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BookControllerTest {

    @MockBean
    private BookService service;

    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(Book bookMock) {
        try {
            return new ObjectMapper().writeValueAsString(bookMock);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @Test
    @DisplayName("POST /books - Should create a Book")
    void should_create_a_book() throws Exception {
        Book bookMock = Book
                .builder()
                .title("Métricas ágeis")
                .value(BookValue.builder().amount(2900).currency("BRL").unit(UnitTypeEnum.FRACTIONAL).build())
                .isbn("978-85-5519-276-19")
                .build();

        doReturn(bookMock).when(service).create(any(BookRequest.class));

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bookMock)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("Métricas ágeis")))
                .andExpect(jsonPath("$.value.currency", is("BRL")))
                .andExpect(jsonPath("$.value.amount", is(2900)))
                .andExpect(jsonPath("$.value.unit", is("FRACTIONAL")))
                .andExpect(jsonPath("$.isbn", is("978-85-5519-276-19")));
    }

    @Test
    @DisplayName("POST /books - Try to create an existing book")
    void try_to_create_an_existing_book() throws Exception {

        when(service.create(any())).thenThrow(new ResourceAlreadyExistsException());

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(getBookMock())))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message", is("The resource already Exists")));
    }

    @Test
    @DisplayName("POST /books - Try to create a book without name")
    void try_to_create_a_book_without_name() throws Exception {
        Book bookRequest = Book.builder()
                .value(BookValue.builder().amount(2900).currency("BRL").unit(UnitTypeEnum.FRACTIONAL).build())
                .isbn("978-85-5519-276-19")
                .build();

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bookRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Erro validação dados")));
    }

    @Test
    @DisplayName("POST /books - Try to create a book without value")
    void try_to_create_a_book_without_value() throws Exception {
        Book bookRequest = Book.builder()
                .title("Métricas ágeis")
                .isbn("978-85-5519-276-19")
                .build();

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bookRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Erro validação dados")));
    }

    @Test
    @DisplayName("POST /books - Try to create a book without isbn")
    void try_to_create_a_book_without_isbn() throws Exception {
        Book bookRequest = Book.builder()
                .title("Métricas ágeis")
                .value(BookValue.builder().amount(2900).currency("BRL").unit(UnitTypeEnum.FRACTIONAL).build())
                .build();

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bookRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Erro validação dados")));
    }

    @Test
    @DisplayName("GET /books - Should get a paginated list of books")
    void should_get_a_paginated_list_of_books() throws Exception {

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(getBookMock());
        PageImpl<Book> bookPageMock = new PageImpl<>(bookList);
        doReturn(bookPageMock).when(service).list(any());

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title", is("Métricas ágeis")));
    }

    private Book getBookMock() {
        return Book.builder()
                .title("Métricas ágeis")
                .value(BookValue.builder().amount(2900).currency("BRL").unit(UnitTypeEnum.FRACTIONAL).build())
                .isbn("978-85-5519-276-19")
                .build();
    }
}
