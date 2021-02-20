package br.com.rafaelshayashi.catalogue.service;

import br.com.rafaelshayashi.catalogue.controller.request.BookInstanceRequest;
import br.com.rafaelshayashi.catalogue.model.BookInstance;
import br.com.rafaelshayashi.catalogue.repository.BookInstanceRepository;
import org.springframework.stereotype.Service;

@Service
public class BookInstanceServiceImpl implements BookInstanceService {

    private final BookInstanceRepository repository;
    private final BookService bookService;
    private final LibraryService libraryService;

    public BookInstanceServiceImpl(BookInstanceRepository repository, BookService bookService, LibraryService libraryService) {
        this.repository = repository;
        this.bookService = bookService;
        this.libraryService = libraryService;
    }

    @Override
    public BookInstance create(BookInstanceRequest request) {
        return repository.save(request.toModel(
                bookService.find(request.getBookUuid()).get(),
                libraryService.find(request.getLibraryUuid()).get()));
    }
}
