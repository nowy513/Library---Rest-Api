package com.book.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RentalDto {

    private Long id;
    private LocalDate dateOfLoan;
    private LocalDate dateOfReturn;
    private Long copiesOfBooksId;
    private Long userId;



    public RentalDto(Long id, LocalDate dateOfLoan, LocalDate dateOfReturn, CopiesOfBooks copiesOfBooks, Long id1) {
    }
}
