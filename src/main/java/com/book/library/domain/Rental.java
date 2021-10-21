package com.book.library.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "RENTAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    @NotNull
    @Column(name = "RENTAL_ID")
    private Long id;

    @NotNull
    @Column(name = "RENTAL_DATE_OF_LOAN")
    private LocalDate dateOfLoan;

    @NotNull
    @Column(name = "RENTAL_DATE_OF_RETURN")
    private LocalDate dateOfReturn;

    @OneToOne
    @JoinColumn(name = "COPIES_OF_BOOKS_ID")
    private CopiesOfBooks copiesOfBooks;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
