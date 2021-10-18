package com.book.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CopiesOfBooksDto {

    private Long id;
    private boolean status;
    private Long userId;
    private int numberOfCopies;

}
