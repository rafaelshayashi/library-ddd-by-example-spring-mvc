package br.com.rafaelshayashi.catalogue.controller;

import br.com.rafaelshayashi.catalogue.controller.request.BookInstanceRequest;
import br.com.rafaelshayashi.catalogue.controller.response.BookInstanceResponse;
import br.com.rafaelshayashi.catalogue.service.BookInstanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/books/instances")
public class BookInstanceController {


    private final BookInstanceService service;

    public BookInstanceController(BookInstanceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BookInstanceResponse> create(@RequestBody BookInstanceRequest request,
                                                       UriComponentsBuilder builder) {
        BookInstanceResponse response = BookInstanceResponse.of(service.create(request));
        URI uri = builder.path("/books/instances/{uuid}").buildAndExpand(response.getUuid()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
