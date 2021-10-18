package com.book.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "BOOK")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @NotNull
    @Column(name = "BOOK_ID")
    private Long id;

    @NotNull
    @Column(name = "BOOK_TITLE")
    private String title;

    @NotNull
    @Column(name = "BOOK_AUTHOR")
    private String author;

    @NotNull
    @Column(name = "BOOK_PUBLICATION_DATE")
    private LocalDate publicationDate;

    @OneToOne
    @JoinColumn(name = "NUMERB_OF_COPIES")
    private CopiesOfBooks copiesOfBooks;

    @OneToOne
    @JoinColumn(name = "BOOK_ID")
    private User user;
}
