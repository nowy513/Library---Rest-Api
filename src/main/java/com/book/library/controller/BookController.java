package com.book.library.controller;

import com.book.library.domain.Book;
import com.book.library.domain.BookDto;
import com.book.library.domain.UserDto;
import com.book.library.mapper.BookMapper;
import com.book.library.mapper.UserMapper;
import com.book.library.repository.BookRepository;
import com.book.library.service.BookService;
import com.book.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@RestController
@CrossOrigin("*")
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;


    @GetMapping("/allBooks")
    public List<BookDto> getAllBooks(){
        return bookMapper.mapToBookListDto(bookService.getAllBooks());
    }

    @GetMapping("/author/{author}")
    public List<BookDto> findBooksByAuthor(@PathVariable String author){
        return bookMapper.mapToBookStreamDto(bookService.getBookByAuthor(author));
    }

    @GetMapping("/title/{title}")
    public List<BookDto> findBooksByTitle(@PathVariable String title){
        return bookMapper.mapToBookStreamDto(bookService.getBookByTitle(title));
    }

    @GetMapping("/{bookId}")
    public BookDto findBookById(@PathVariable Long bookId) throws Exception {
        return bookMapper.mapToBookDto(bookService.getBookById(bookId).orElseThrow(Exception::new));
    }

        @GetMapping("/book/{userId}")
    public List<BookDto> findUsersBooks(@PathVariable Long userId) {
        return bookMapper.mapToBookStreamDto(bookService.getAllBooksByUserId(userId));
    }

    @PostMapping("/book")
    public Book creatBook(@RequestBody BookDto bookDto){
        return bookService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        bookService.deleteBook(id);
    }



}
