package com.book.library.mapper;

import com.book.library.domain.Book;
import com.book.library.domain.CopiesOfBooks;
import com.book.library.domain.CopiesOfBooksDto;
import com.book.library.domain.User;
import com.book.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CopiesOfBookMapper {

//    @Autowired
//    private UserRepository userRepository;

//    public CopiesOfBooks mapToCopieDto(final CopiesOfBooksDto copieDto){
//        return new CopiesOfBooks(
//                copieDto.getId(),
//                copieDto.isStatus(),
//                copieDto.getNumberOfCopies(),
//                userRepository.getOne(copieDto.getUserId())
//        );
//    }

    public CopiesOfBooksDto mapToCopie(final CopiesOfBooks copie){
        return new CopiesOfBooksDto(
                        copie.getId(),
                        copie.isStatus(),
                copie.getUser().getId(),
                copie.getNumberOfCopies()
                );
    }

    public List<CopiesOfBooksDto> mapToCopieList(final List<CopiesOfBooks> copiesDto){
        return copiesDto.stream()
                .map(this::mapToCopie)
                .collect(Collectors.toList());
    }

    public List<CopiesOfBooksDto> mapToCopieStream(Stream<CopiesOfBooks> availableCopy) {
        return availableCopy.sorted()
                .map(this::mapToCopie)
                .collect(Collectors.toList());
    }
}
