package com.book.library.controller;

import com.book.library.domain.CopiesOfBooks;
import com.book.library.domain.CopiesOfBooksDto;
import com.book.library.mapper.CopiesOfBookMapper;
import com.book.library.repository.CopiesOfBooksRepository;
import com.book.library.service.CopiesOfBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@CrossOrigin("*")
@RequestMapping("/copiesOfBooks")
@RequiredArgsConstructor
public class CopiesOfBooksController {

    private final CopiesOfBookService service;

    private final CopiesOfBookMapper mapper;

    @GetMapping("/availableCopy")
    public Stream<CopiesOfBooksDto> getAllAvailableCopys(){
        return mapper.mapToCopieList(service.getAllCopies()).stream()
                .filter(CopiesOfBooksDto::isStatus);
    }

    @GetMapping("/copiesNotAvailable")
    public Stream<CopiesOfBooksDto> getAllCopiesNotAvailable(){
        return mapper.mapToCopieList(service.getAllCopies()).stream()
                .filter(copy -> !copy.isStatus());
    }

}
