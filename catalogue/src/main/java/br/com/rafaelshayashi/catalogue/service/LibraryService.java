package br.com.rafaelshayashi.catalogue.service;

import br.com.rafaelshayashi.catalogue.controller.request.LibraryRequest;
import br.com.rafaelshayashi.catalogue.model.Library;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface LibraryService {

    Library create(LibraryRequest request);

    Optional<Library> find(UUID bookUuid);

    Page<Library> list(Pageable pageable);
}
