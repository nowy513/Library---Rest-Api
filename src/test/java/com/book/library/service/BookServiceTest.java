package com.book.library.service;

import com.book.library.domain.Book;
import com.book.library.domain.CopiesOfBooks;
import com.book.library.domain.User;
import com.book.library.repository.BookRepository;
import javafx.beans.binding.When;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;
    private Object Book;

    @Test
    public void testGetAllBooks(){

//        Given
        Book book = new Book(
                1L, "Test", "Author Test", LocalDate.of(2019, 4, 20), new CopiesOfBooks(), new User()
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
        Book book = new Book(
                1L, "Test", "Author Test", LocalDate.of(2019, 4, 20), new CopiesOfBooks(), new User()
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
        Book book = new Book(
                1L, "Test", "Author Test", LocalDate.of(2019, 4, 20), new CopiesOfBooks(), new User()
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
        Book book = new Book(
                1L, "Test", "Author Test", LocalDate.of(2019, 4, 20), new CopiesOfBooks(),new User()
        );

        Long id = book.getId();

        bookService.deleteBook(id);

//        When
        Optional<Book> deleteBook = bookService.getBookById(1L);

//        Then
        assertFalse(deleteBook.isPresent());

        }

    @Test
    public void testGetBooksByTitle(){

//        Given
        Book book = new Book(
                1L, "Test", "Author Test", LocalDate.of(2019, 4, 20), new CopiesOfBooks(), new User()
        );
        Book book2 = new Book(
                1L, "TestSecond", "Author Test", LocalDate.of(2019, 4, 20), new CopiesOfBooks(), new User()
        );

        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book2);

        when(bookRepository.findAll()).thenReturn(books);

//        When
        Stream<Book> bookList = bookService.getBookByTitle("Test");

//        Then
        assertEquals(1, bookList.count());
    }

    @Test
    public void testGetBooksByAutor(){

//        Given
        Book book = new Book(
                1L, "Test", "Author", LocalDate.of(2019, 4, 20), new CopiesOfBooks(), new User()
        );
        Book book2 = new Book(
                1L, "TestSecond", "AuthorSecond", LocalDate.of(2019, 4, 20), new CopiesOfBooks(), new User()
        );

        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book2);

        when(bookRepository.findAll()).thenReturn(books);

//        When
        Stream<Book> bookList = bookService.getBookByAuthor("Author");

//        Then
        assertEquals(1, bookList.count());
    }
}
