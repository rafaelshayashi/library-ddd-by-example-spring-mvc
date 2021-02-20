package br.com.rafaelshayashi.catalogue.service;

import br.com.rafaelshayashi.catalogue.controller.request.LibraryRequest;
import br.com.rafaelshayashi.catalogue.model.Library;

import java.util.Optional;
import java.util.UUID;

public interface LibraryService {

    Library create(LibraryRequest request);

    Optional<Library> find(UUID bookUuid);
}
