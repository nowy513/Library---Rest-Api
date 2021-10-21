package com.book.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private LocalDate creationDate;
    private Long bookId;
    private Long rentalId;

    public UserDto(Long id, String name, String surname, LocalDate creationDate, Book book, Rental rental) {
    }
}
