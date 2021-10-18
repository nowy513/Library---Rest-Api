package com.book.library.mapper;

import com.book.library.domain.Book;
import com.book.library.domain.BookDto;
import com.book.library.repository.BookRepository;
import com.book.library.repository.CopiesOfBooksRepository;
import com.book.library.repository.UserRepository;
import com.book.library.service.CopiesOfBookService;
import com.book.library.service.RentalService;
import com.book.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookMapper {

    private final CopiesOfBooksRepository copiesOfBooksRepository;

    private final UserRepository userRepository;

    public Book mapToBook(BookDto bookDto){
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPublicationDate(),
                copiesOfBooksRepository.getOne(bookDto.getCopiesOfBook_numerOfCopies()),
                userRepository.getOne(bookDto.getUserId())

        );
    }

    public BookDto mapToBookDto(final Book book){
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationDate(),
                book.getCopiesOfBooks(),
                book.getUser()
        );
    }

    public List<BookDto> mapToBookListDto(final List<Book> bookList){
        return bookList.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }

}
