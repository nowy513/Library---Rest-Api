package com.book.library.controller;

import com.book.library.domain.CopiesOfBooksDto;
import com.book.library.mapper.CopiesOfBookMapper;
import com.book.library.service.CopiesOfBookService;
import lombok.RequiredArgsConstructor;
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
    public List<CopiesOfBooksDto> getAllAvailableCopys(){
        return mapper.mapToCopieStream(service.getAvailableCopy());
    }

    @GetMapping("/copiesNotAvailable")
    public List<CopiesOfBooksDto> getAllCopiesNotAvailable(){
        return mapper.mapToCopieStream(service.getNotAvailableCopy());
    }

}
