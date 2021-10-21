package com.book.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "COPIES_OF_BOOKS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopiesOfBooks {

    @Id
    @NotNull
    @Column(name = "COPIES_OF_BOOKS_ID")
    private Long id;

    @NotNull
    @Column(name = "COPIES_OF_BOOKS_STATUS")
    private boolean status;

    @Column(name = "NUMERB_OF_COPIES")
    private int numberOfCopies;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

}
