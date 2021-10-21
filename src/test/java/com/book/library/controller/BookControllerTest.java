package com.book.library.controller;

import com.book.library.domain.Book;
import com.book.library.domain.BookDto;
import com.book.library.domain.CopiesOfBooks;
import com.book.library.domain.User;
import com.book.library.mapper.BookMapper;
import com.book.library.mapper.UserMapper;
import com.book.library.service.BookService;
import com.book.library.service.UserService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockBean
    BookService bookService;

    @MockBean
    BookMapper bookMapper;

    @MockBean
    UserService userService;

    @MockBean
    UserMapper userMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldFetchEmptyList() throws Exception {

//        Given
        List<BookDto> bookDtoList = new ArrayList<>();

        when(bookMapper.mapToBookListDto(bookService.getAllBooks())).thenReturn(bookDtoList);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/book/allBooks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldGetAllBooks() throws Exception{

        //        Given
        BookDto bookDto = new BookDto(
                1L, "TestDto", "AuthorDto", LocalDate.of(2019, 4, 20), 2L, 3L);

        List<BookDto> bookDtoList = new ArrayList<>();
        bookDtoList.add(bookDto);


        when(bookMapper.mapToBookListDto(any())).thenReturn(bookDtoList);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/book/allBooks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }


    @Test
    public void shouldGetBookById() throws Exception{

        //        Given
        Book book = new Book(
                1L, "Test", "Author Test", LocalDate.of(2019, 4, 20), new CopiesOfBooks(), new User());

        BookDto bookDto = new BookDto(
                1L, "TestDto", "AuthorDto", LocalDate.of(2019, 4, 20), 2L, 3L);

        when(bookService.getBookById(1L)).thenReturn(Optional.of(book));
        when(bookMapper.mapToBookDto(any())).thenReturn(bookDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/book/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", Matchers.is("AuthorDto")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publicationDate", Matchers.is(LocalDate.of(2019, 4, 20).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.copiesOfBook_numerOfCopies", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldCreateBook() throws Exception {

//        Given
        BookDto bookDto = new BookDto(
                1L, "TestDto", "AuthorDto", LocalDate.of(2019, 4, 20), new CopiesOfBooks(), new User());

        when(bookService.saveBook(any(Book.class))).thenReturn(new Book(
                1L, "Test", "Author Test", LocalDate.of(2019, 4, 20), new CopiesOfBooks(), new User()));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .post("/book/book")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldDeleteBook() throws Exception{

//        Given
        Book book = new Book(
                1L, "Test", "Author Test", LocalDate.of(2019, 4, 20), new CopiesOfBooks(), new User());

        when(bookService.saveBook(book)).thenReturn(book);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .delete("/book/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldGetBookByTitle() throws Exception{

        //        Given
        Book book = new Book(
                1L, "Test", "Author Test", LocalDate.of(2019, 4, 20), new CopiesOfBooks(), new User());

        BookDto bookDto = new BookDto(
                1L, "TestSecond", "Author Test", LocalDate.of(2019, 4, 20), 2L, 4L);

        List<BookDto> dtoList = new ArrayList<>();
        dtoList.add(bookDto);


        when(bookService.getBookByTitle("Test")).thenReturn(Stream.of(book));
        when(bookMapper.mapToBookStreamDto(any())).thenReturn(dtoList);


//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/book/title/Test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldGetBookByAuthor() throws Exception{

        //        Given
        Book book = new Book(
                1L, "Test", "Author_Test", LocalDate.of(2019, 4, 20), new CopiesOfBooks(), new User());
       BookDto bookDto = new BookDto(
                1L, "Test", "Author_Test", LocalDate.of(2019, 4, 20), 2L, 3L);

        List<BookDto> dtoList = new ArrayList<>();
        dtoList.add(bookDto);


        when(bookService.getBookByAuthor("Author_Test")).thenReturn(Stream.of(book));
        when(bookMapper.mapToBookStreamDto(any())).thenReturn(dtoList);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/book/author/Author_Test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

}
