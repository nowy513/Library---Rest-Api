package com.book.library.service;

import com.book.library.domain.*;
import com.book.library.repository.RentalRepository;
import com.book.library.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RentalServiceTest {

    @InjectMocks
    RentalService rentalService;

    @Mock
    RentalRepository rentalRepository;

    @Mock
    UserRepository userRepository;



    @Test
    public void testGetAllRentals(){

//        Given
        Rental rental = new Rental(
                1L, LocalDate.of(2020,05,15),
                LocalDate.of(2020,05,23), new CopiesOfBooks(), new User()
        );

        List<Rental> rentals = new ArrayList<>();
        rentals.add(rental);

        when(rentalRepository.findAll()).thenReturn(rentals);

//        When
        List<Rental> rentalList = rentalService.getAllRentals();

//        Then
        assertEquals(1, rentalList.size());
    }

    @Test
    public void testGetRental(){

//        Given
        Rental rental = new Rental(
                1L, LocalDate.of(2020,05,15),
                LocalDate.of(2020,05,23), new CopiesOfBooks(), new User()
        );


        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));

//        When
        Optional<Rental> getRental = rentalService.getRent(1L);

//        Then
        assertEquals(Optional.of(rental), getRental);
    }

    @Test
    public void testSaveRental(){

//        Given
        Rental rental = new Rental(
                1L, LocalDate.of(2020,05,15),
                LocalDate.of(2020,05,23), new CopiesOfBooks(), new User()
        );

        when(rentalRepository.save(rental)).thenReturn(rental);

//        When
        Rental saveRental = rentalService.saveRental(rental);

//        Then
        assertEquals(1L, saveRental.getId());
    }

    @Test
    public void testDeleteBook(){

//        Given
        Rental rental = new Rental(
                1L, LocalDate.of(2020,05,15),
                LocalDate.of(2020,05,23), new CopiesOfBooks(), new User()
        );

        Long id = rental.getId();
        rentalService.deleteRental(id);

//        When
        Optional<Rental> deleteRental = rentalService.getRent(1L);

//        Then
        assertFalse(deleteRental.isPresent());
    }

}
