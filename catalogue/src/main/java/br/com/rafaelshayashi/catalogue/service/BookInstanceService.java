package br.com.rafaelshayashi.catalogue.service;

import br.com.rafaelshayashi.catalogue.controller.request.BookInstanceRequest;
import br.com.rafaelshayashi.catalogue.model.BookInstance;

public interface BookInstanceService {

    BookInstance create(BookInstanceRequest request);
}
