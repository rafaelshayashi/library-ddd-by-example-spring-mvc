package br.com.rafaelshayashi.catalogue.service;

import br.com.rafaelshayashi.catalogue.controller.request.BookRequest;
import br.com.rafaelshayashi.catalogue.model.Book;

import java.util.Optional;
import java.util.UUID;

public interface BookService {

    Book create(BookRequest request);

    Optional<Book> find(UUID bookUuid);
}
