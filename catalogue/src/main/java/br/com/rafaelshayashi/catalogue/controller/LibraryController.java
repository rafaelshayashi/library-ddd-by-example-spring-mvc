package br.com.rafaelshayashi.catalogue.controller;

import br.com.rafaelshayashi.catalogue.controller.request.LibraryRequest;
import br.com.rafaelshayashi.catalogue.controller.response.LibraryResponse;
import br.com.rafaelshayashi.catalogue.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/libraries")
public class LibraryController {

    private final LibraryService service;

    @Autowired
    public LibraryController(LibraryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LibraryResponse> create(@RequestBody LibraryRequest request, UriComponentsBuilder builder) {
        LibraryResponse response = LibraryResponse.of(service.create(request));
        URI uri = builder.path("/libraries/{uuid}").buildAndExpand(response.getUuid()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
