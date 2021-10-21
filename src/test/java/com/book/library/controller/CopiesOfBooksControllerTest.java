package com.book.library.controller;

import com.book.library.domain.CopiesOfBooks;
import com.book.library.domain.CopiesOfBooksDto;
import com.book.library.domain.User;
import com.book.library.mapper.CopiesOfBookMapper;
import com.book.library.repository.CopiesOfBooksRepository;
import com.book.library.service.CopiesOfBookService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(CopiesOfBooksController.class)
public class CopiesOfBooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CopiesOfBooksRepository repository;

    @MockBean
    private CopiesOfBookService service;

    @MockBean
    private CopiesOfBookMapper mapper;

    @Test
    public void shouldGetListAvailableCopys() throws Exception{

//        Given
        CopiesOfBooks copie = new CopiesOfBooks(
                1L, true, 3, new User());

        CopiesOfBooksDto copieDto = new CopiesOfBooksDto
                (1L, true, 1L, 3 );

        List<CopiesOfBooksDto> copies = new ArrayList<>();
        copies.add(copieDto);

        when(service.getAvailableCopy()).thenReturn(Stream.of(copie));
        when(mapper.mapToCopieStream(any())).thenReturn(copies);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/copiesOfBooks/copiesNotAvailable")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    public void shouldGetListNotAvailableCopys() throws Exception{

//        Given
        CopiesOfBooks copie = new CopiesOfBooks(
                1L, false, 3, new User());

        CopiesOfBooksDto copieDto = new CopiesOfBooksDto
                (1L, false, 1L, 3 );

        List<CopiesOfBooksDto> copies = new ArrayList<>();
        copies.add(copieDto);

        when(service.getNotAvailableCopy()).thenReturn(Stream.of(copie));
        when(mapper.mapToCopieStream(any())).thenReturn(copies);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/copiesOfBooks/availableCopy")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }
}
