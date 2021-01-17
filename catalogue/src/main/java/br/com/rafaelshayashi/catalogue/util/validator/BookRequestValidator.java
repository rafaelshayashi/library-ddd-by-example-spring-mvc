package br.com.rafaelshayashi.catalogue.util.validator;

import br.com.rafaelshayashi.catalogue.controller.request.BookRequest;
import br.com.rafaelshayashi.catalogue.model.Book;
import br.com.rafaelshayashi.catalogue.repository.BookRepository;
import br.com.rafaelshayashi.catalogue.util.exception.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class BookRequestValidator implements Validator {

    private final BookRepository repository;

    @Autowired
    public BookRequestValidator(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return BookRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookRequest bookRequest = (BookRequest) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "bookRequest.name.empty", "name must be valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "bookRequest.value.empty", "value must be valid");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbn", "bookRequest.isbn.empty", "isbn must be valid");

        Optional<Book> bookOptional = repository.findByIsbn(bookRequest.getIsbn());
        if(bookOptional.isPresent()){
            throw new ResourceAlreadyExistsException();
        }
    }
}
