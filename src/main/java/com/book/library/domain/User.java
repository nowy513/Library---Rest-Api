package com.book.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @NotNull
    @Column(name = "USER_ID")
    private Long id;

    @NotNull
    @Column(name = "USER_NAME")
    private String name;

    @NotNull
    @Column(name = "USER_SURNAME")
    private String surname;

    @NotNull
    @Column(name = "USER_CREATION_DATE")
    private LocalDate creationDate;

    @OneToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @OneToOne
    @JoinColumn(name = "RENTAL_ID")
    private Rental rental;

}
