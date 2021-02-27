package br.com.rafaelshayashi.catalogue.service;

import br.com.rafaelshayashi.catalogue.controller.request.BookRequest;
import br.com.rafaelshayashi.catalogue.controller.request.BookValueRequest;
import br.com.rafaelshayashi.catalogue.model.Book;
import br.com.rafaelshayashi.catalogue.model.BookValue;
import br.com.rafaelshayashi.catalogue.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BookServiceImpl.class)
public class BookServiceTest {

    @MockBean
    private BookRepository repository;

    @Autowired
    private BookService service;

    @Test
    @DisplayName("Service - Should create a book")
    public void should_create_a_book() {
        BookValue bookValue = BookValue.builder().currency("BRL").amount(6200).build();
        Book bookMock = Book.builder().title("Effective Java").isbn("978-0134685991").value(bookValue).build();
        doReturn(Optional.empty()).when(repository).findByIsbn(any(String.class));
        doReturn(bookMock).when(repository).save(any(Book.class));

        BookValueRequest bookValueRequest = new BookValueRequest();
        bookValueRequest.setCurrency("BRL");
        bookValueRequest.setAmount(6200);
        BookRequest request = new BookRequest();
        request.setTitle("Effective Java");
        request.setIsbn("978-0134685991");
        request.setValue(bookValueRequest);
        Book book = service.create(request);

        Assertions.assertEquals("Effective Java", book.getTitle());
        Assertions.assertEquals("978-0134685991", book.getIsbn());

    }
}
