package br.com.rafaelshayashi.catalogue.repository;

import br.com.rafaelshayashi.catalogue.model.BookInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInstanceRepository extends JpaRepository<BookInstance, Long> {
}
