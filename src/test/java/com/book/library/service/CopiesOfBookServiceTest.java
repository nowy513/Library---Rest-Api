package com.book.library.service;

import com.book.library.domain.CopiesOfBooks;
import com.book.library.domain.User;
import com.book.library.repository.CopiesOfBooksRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CopiesOfBookServiceTest {

    @InjectMocks
    CopiesOfBookService copiesOfBookService;

    @Mock
    CopiesOfBooksRepository copiesOfBooksRepository;

    @Test
    public void testGetAllCopies(){

//        Given
        CopiesOfBooks copie = new CopiesOfBooks(
                1L, true, 4, new User()
        );

        List<CopiesOfBooks> copies = new ArrayList<>();
        copies.add(copie);

        when(copiesOfBooksRepository.findAll()).thenReturn(copies);

//        When
        List<CopiesOfBooks> copieList = copiesOfBookService.getAllCopies();

//        Then
        assertEquals(1, copieList.size());
    }

    @Test
    public void getAvailableCopies(){

//        Given
        CopiesOfBooks copie = new CopiesOfBooks(
                1L, true, 4, new User()
        );

        CopiesOfBooks copie2 = new CopiesOfBooks(
                1L, false, 4, new User()
        );

        List<CopiesOfBooks> copies = new ArrayList<>();
        copies.add(copie);
        copies.add(copie2);

        when(copiesOfBooksRepository.findAll()).thenReturn(copies);

//        When
        Stream<CopiesOfBooks> copieList = copiesOfBookService.getAvailableCopy();

//        Then
        assertEquals(1, copieList.count());
    }

    @Test
    public void getNotAvailableCopies() {

//        Given
        CopiesOfBooks copie = new CopiesOfBooks(
                1L, true, 4, new User()
        );

        CopiesOfBooks copie2 = new CopiesOfBooks(
                1L, false, 4, new User()
        );

        List<CopiesOfBooks> copies = new ArrayList<>();
        copies.add(copie);
        copies.add(copie2);

        when(copiesOfBooksRepository.findAll()).thenReturn(copies);

//        When
        Stream<CopiesOfBooks> copieList = copiesOfBookService.getNotAvailableCopy();

//        Then
        assertEquals(1, copieList.count());

    }

}
