package br.com.rafaelshayashi.catalogue.service;

import br.com.rafaelshayashi.catalogue.controller.request.LibraryRequest;
import br.com.rafaelshayashi.catalogue.model.Library;
import br.com.rafaelshayashi.catalogue.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository repository;

    @Autowired
    public LibraryServiceImpl(LibraryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Library create(LibraryRequest request) {
        return repository.save(request.toModel());
    }

    @Override
    public Optional<Library> find(UUID bookUuid) {
        return repository.findByUuid(bookUuid);
    }

    @Override
    public Page<Library> list(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
