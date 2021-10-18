package com.book.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String title;
    private String author;
    private LocalDate publicationDate;
    private Long copiesOfBook_numerOfCopies;
    private Long userId;


    public BookDto(Long id, String title, String author, LocalDate publicationDate, CopiesOfBooks copiesOfBooks, User user) {
    }
}
