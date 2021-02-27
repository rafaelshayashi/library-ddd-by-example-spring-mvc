package br.com.rafaelshayashi.catalogue.controller;

import br.com.rafaelshayashi.catalogue.controller.request.BookRequest;
import br.com.rafaelshayashi.catalogue.controller.response.BookResponse;
import br.com.rafaelshayashi.catalogue.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody @Valid BookRequest request,
                                               UriComponentsBuilder uriBuilder) {

        BookResponse bookResponse = BookResponse.of(service.create(request));
        URI uri = uriBuilder.path("/books/{uuid}").buildAndExpand(bookResponse.getUuid()).toUri();
        return ResponseEntity.created(uri).body(bookResponse);
    }

    @GetMapping
    public Page<BookResponse> list(Pageable pageable) {
        List<BookResponse> bookResponseList = service.list(pageable)
                .stream()
                .map(BookResponse::new)
                .collect(Collectors.toList());
        return new PageImpl<>(bookResponseList);
    }
}
