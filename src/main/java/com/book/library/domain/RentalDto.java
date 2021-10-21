package com.book.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {

    private Long id;
    private LocalDate dateOfLoan;
    private LocalDate dateOfReturn;
    private Long copiesOfBooksId;
    private Long userId;

    public RentalDto(long id, LocalDate of, LocalDate of1, CopiesOfBooks copiesOfBooks, User user) {
    }
}
