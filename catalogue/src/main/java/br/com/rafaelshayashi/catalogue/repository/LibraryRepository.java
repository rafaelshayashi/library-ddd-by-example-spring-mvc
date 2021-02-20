package br.com.rafaelshayashi.catalogue.repository;

import br.com.rafaelshayashi.catalogue.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    Optional<Library> findByUuid(UUID bookUuid);
}
