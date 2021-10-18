package com.book.library.service;

import com.book.library.domain.Book;
import com.book.library.domain.CopiesOfBooks;
import com.book.library.domain.User;
import com.book.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Test
    public void testGetAllBooks(){

//        Given
        CopiesOfBooks copiesOfBooks = new CopiesOfBooks();

        User user = new User();

        Book book = new Book(
                1L, "Test", "Author Test", LocalDate.of(2019, 4, 20), copiesOfBooks, user
        );

        List<Book> books = new ArrayList<>();
        books.add(book);

        when(bookRepository.findAll()).thenReturn(books);

//        When
        List<Book> bookList = bookService.getAllBooks();

//        Then
        assertEquals(1, bookList.size());
    }
}
