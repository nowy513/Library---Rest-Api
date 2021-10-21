package com.book.library.mapper;

import com.book.library.domain.Rental;
import com.book.library.domain.RentalDto;
import com.book.library.repository.CopiesOfBooksRepository;
import com.book.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RentalMapper {

    private final UserRepository userRepository;

    private final CopiesOfBooksRepository copiesOfBooksRepository;

    public Rental mapToRental(final RentalDto rentalDto){
        return new Rental(
                rentalDto.getId(),
                rentalDto.getDateOfLoan(),
                rentalDto.getDateOfReturn(),
//                copiesOfBooksRepository.getById(rentalDto.getCopiesOfBooksId()),
//                userRepository.getById(rentalDto.getUserId())
                copiesOfBooksRepository.getOne(rentalDto.getCopiesOfBooksId()),
                userRepository.getOne(rentalDto.getUserId())
        );
    }

    public RentalDto mapToRentalDto(final Rental rental){
        return new RentalDto(
                rental.getId(),
                rental.getDateOfLoan(),
                rental.getDateOfReturn(),
                rental.getCopiesOfBooks().getId(),
                rental.getUser().getId()

        );
    }

    public List<RentalDto> mapToRentalList(final List<Rental> rentals){
        return rentals.stream()
                .map(this::mapToRentalDto)
                .collect(Collectors.toList());
    }

    public List<RentalDto> mapToRentalStream(final Stream<Rental> rentals){
        return rentals.sorted()
                .map(this::mapToRentalDto)
                .collect(Collectors.toList());
    }

}
