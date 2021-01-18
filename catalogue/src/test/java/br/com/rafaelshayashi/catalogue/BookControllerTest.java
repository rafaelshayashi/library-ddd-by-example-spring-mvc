package br.com.rafaelshayashi.catalogue;

import br.com.rafaelshayashi.catalogue.controller.request.BookRequest;
import br.com.rafaelshayashi.catalogue.model.Book;
import br.com.rafaelshayashi.catalogue.service.BookService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {


    @MockBean
    private BookService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /books - Should create a Book")
    public void should_create_a_book() throws Exception {
        Book bookMock = Book
                .builder()
                .name("Métricas ágeis")
                .value(2900)
                .isbn("978-85-5519-276-19")
                .build();

        doReturn(bookMock).when(service).create(any(BookRequest.class));

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bookMock)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Métricas ágeis")))
                .andExpect(jsonPath("$.value", is(2900)))
                .andExpect(jsonPath("$.isbn", is("978-85-5519-276-19")));
    }

    private static String asJsonString(Book bookMock) {
        try {
            return new ObjectMapper().writeValueAsString(bookMock);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
