package com.book.library.mapper;

import com.book.library.domain.Book;
import com.book.library.domain.BookDto;
import com.book.library.repository.CopiesOfBooksRepository;
import com.book.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<BookDto> mapToBookStreamDto(Stream<Book> bookList) {
        return bookList.sorted()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }
}
