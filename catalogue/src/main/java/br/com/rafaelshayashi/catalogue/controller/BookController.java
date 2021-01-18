package br.com.rafaelshayashi.catalogue.controller;

import br.com.rafaelshayashi.catalogue.controller.request.BookRequest;
import br.com.rafaelshayashi.catalogue.controller.response.BookResponse;
import br.com.rafaelshayashi.catalogue.service.BookService;
import br.com.rafaelshayashi.catalogue.util.validator.BookRequestValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;
    private final BookRequestValidator validator;

    public BookController(BookService service, BookRequestValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.setValidator(validator);
    }

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody @Valid BookRequest request,
                                               UriComponentsBuilder uriBuilder) {

        BookResponse bookResponse = BookResponse.of(service.create(request));
        URI uri = uriBuilder.path("/books/{uuid}").buildAndExpand(bookResponse.getUuid()).toUri();
        return ResponseEntity.created(uri).body(bookResponse);
    }
}
