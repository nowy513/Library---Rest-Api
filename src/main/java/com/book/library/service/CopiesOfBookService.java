package com.book.library.service;

import com.book.library.domain.CopiesOfBooks;
import com.book.library.repository.CopiesOfBooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CopiesOfBookService {

    private final CopiesOfBooksRepository repository;

    public List<CopiesOfBooks> getAllCopies(){
        return repository.findAll();
    }
}
