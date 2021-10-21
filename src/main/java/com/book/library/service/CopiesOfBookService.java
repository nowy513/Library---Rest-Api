package com.book.library.service;

import com.book.library.domain.CopiesOfBooks;
import com.book.library.repository.CopiesOfBooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CopiesOfBookService {

    private final CopiesOfBooksRepository repository;

    public List<CopiesOfBooks> getAllCopies(){
        return repository.findAll();
    }

    public Stream<CopiesOfBooks> getAvailableCopy(){
        return repository.findAll().stream().filter(CopiesOfBooks::isStatus);
    }

    public Stream<CopiesOfBooks> getNotAvailableCopy(){
        return repository.findAll().stream().filter(book -> !book.isStatus());
    }
}
