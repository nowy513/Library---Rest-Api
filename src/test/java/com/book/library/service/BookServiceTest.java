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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    public void testGetBook(){

        //        Given
        CopiesOfBooks copiesOfBooks = new CopiesOfBooks();

        User user = new User();

        Book book = new Book(
                1L, "Test", "Author Test", LocalDate.of(2019, 4, 20), copiesOfBooks, user
        );

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
//        When
        Optional<Book> getBook = bookService.getBookById(1L);

//        Then
        assertEquals(Optional.of(book), getBook);
    }

    @Test
    public void testSaveBook(){

        //        Given
        CopiesOfBooks copiesOfBooks = new CopiesOfBooks();

        User user = new User();

        Book book = new Book(
                1L, "Test", "Author Test", LocalDate.of(2019, 4, 20), copiesOfBooks, user
        );

        when(bookRepository.save(book)).thenReturn(book);

//        When
        Book saveBook = bookService.saveBook(book);

//        Then
        assertEquals("Test",saveBook.getTitle());
    }

    @Test
    public void testDeleteBook(){

        //        Given
        CopiesOfBooks copiesOfBooks = new CopiesOfBooks();

        User user = new User();

        Book book = new Book(
                1L, "Test", "Author Test", LocalDate.of(2019, 4, 20), copiesOfBooks, user
        );

        Long id = book.getId();

        bookService.deleteBook(id);

//        When
        Optional<Book> deleteBook = bookService.getBookById(1L);

//        Then
        assertFalse(deleteBook.isPresent());

        }
}
