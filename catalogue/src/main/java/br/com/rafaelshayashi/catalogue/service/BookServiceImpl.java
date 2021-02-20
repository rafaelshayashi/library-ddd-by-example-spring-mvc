package br.com.rafaelshayashi.catalogue.service;

import br.com.rafaelshayashi.catalogue.controller.request.BookRequest;
import br.com.rafaelshayashi.catalogue.model.Book;
import br.com.rafaelshayashi.catalogue.repository.BookRepository;
import br.com.rafaelshayashi.catalogue.util.exception.ResourceAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book create(BookRequest request) {
        if (repository.findByIsbn(request.getIsbn()).isPresent()) {
            throw new ResourceAlreadyExistsException();
        }
        return repository.save(request.toModel());
    }

    @Override
    public Optional<Book> find(UUID bookUuid) {
        return repository.findByUuid(bookUuid);
    }
}
