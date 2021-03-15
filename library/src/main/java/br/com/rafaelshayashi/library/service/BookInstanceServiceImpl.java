package br.com.rafaelshayashi.library.service;

import br.com.rafaelshayashi.library.client.CatalogueBook;
import br.com.rafaelshayashi.library.client.response.BookResponse;
import br.com.rafaelshayashi.library.controller.request.BookInstanceRequest;
import br.com.rafaelshayashi.library.exception.ResourceNotExistsException;
import br.com.rafaelshayashi.library.model.BookInstance;
import br.com.rafaelshayashi.library.model.LibraryBranch;
import br.com.rafaelshayashi.library.repository.BookInstanceRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookInstanceServiceImpl implements BookInstanceService {

    private final BookInstanceRepository repository;
    private final CatalogueBook bookService;
    private final LibraryService libraryService;

    public BookInstanceServiceImpl(BookInstanceRepository repository, CatalogueBook bookService, LibraryService libraryService) {
        this.repository = repository;
        this.bookService = bookService;
        this.libraryService = libraryService;
    }

    @Override
    public BookInstance create(BookInstanceRequest request) {
        BookResponse bookResponse = bookService
                .detailsBook(UUID.fromString(request.getBookUuid()))
                .orElseThrow(() -> new ResourceNotExistsException(request.getBookUuid()));

        LibraryBranch libraryBranch = libraryService
                .find(UUID.fromString(request.getLibraryUuid()))
                .orElseThrow(() -> new ResourceNotExistsException(request.getLibraryUuid()));

        return repository.save(request.toModel(UUID.fromString(bookResponse.getUuid()), libraryBranch));
    }
}
