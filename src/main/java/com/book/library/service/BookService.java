package com.book.library.service;

import com.book.library.domain.Book;
import com.book.library.repository.BookRepository;
import com.book.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookService {


    private final BookRepository repository;

    private final UserRepository userRepository;

    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    public Optional<Book> getBookById(final Long id){
        return repository.findById(id);
    }

    public Book saveBook(final Book book){
        return repository.save(book);
    }

    public void deleteBook(final Long id){
        repository.deleteById(id);
    }

    public Stream<Book> getBookByAuthor(final String author){
        return repository.findAll().stream()
                .filter(book -> book.getAuthor().equals(author));
    }

    public Stream<Book> getBookByTitle(final String title) {
        return repository.findAll().stream()
                .filter(book -> book.getTitle().equals(title));
    }

    public Stream<Book> getAllBooksByUserId(final Long id){
        return  repository.findAll().stream().filter(book -> book.getUser().getId().equals(id));
    }
}
