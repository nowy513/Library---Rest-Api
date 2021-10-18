package com.book.library.service;

import com.book.library.domain.Book;
import com.book.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {


    private final BookRepository repository;


    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    public Optional<Book> getBookById(Long id){
        return repository.findById(id);
    }

    public Book saveBook(final Book book){
        return repository.save(book);
    }

    public void deleteBook(final Long id){
        repository.deleteById(id);
    }

}
