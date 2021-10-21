package com.book.library.domain;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "BOOK")
@Getter
@Setter
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
