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

    private final UserMapper userMapper;

    private final UserService userService;

    @GetMapping("/allBooks")
    public List<BookDto> getAllBooks(){
        return bookMapper.mapToBookListDto(bookService.getAllBooks());
    }

    @GetMapping("/{author}")
    public Stream<BookDto> findBooksByAuthor(@PathVariable String author){
        return bookMapper.mapToBookListDto(bookService.getAllBooks()).stream()
                .filter(bookDto -> bookDto.getAuthor().equals(author));
    }

    @GetMapping("/{title}")
    public Stream<BookDto> findBooksByTitle(@PathVariable String title){
        return bookMapper.mapToBookListDto(bookService.getAllBooks()).stream()
                .filter(bookDto -> bookDto.getTitle().equals(title));
    }

    @GetMapping("/{id}")
    public BookDto findBookById(@PathVariable Long id) throws Exception {
        return bookMapper.mapToBookDto(bookService.getBookById(id).orElseThrow(Exception::new));
    }

    @GetMapping("/{publicationDate}")
    public Stream<BookDto> findBooksByPublicationDate(@PathVariable LocalDate date){
        return bookMapper.mapToBookListDto(bookService.getAllBooks()).stream()
                .filter(bookDto -> bookDto.getPublicationDate().equals(date));
    }

    @GetMapping("/{bookId}")
    public Stream<UserDto> findUserBook(@PathVariable Long BookId ){
        return userMapper.mapToUserList(userService.getAllUsers()).stream()
                .filter(user -> user.getBookId().equals(BookId));
    }

    @PostMapping("/createBook")
    public Book creatBook(@RequestBody BookDto bookDto){
        return bookService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        bookService.deleteBook(id);
    }

}
