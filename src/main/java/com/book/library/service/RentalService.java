package com.book.library.service;

import com.book.library.domain.Rental;
import com.book.library.repository.RentalRepository;
import com.book.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RentalService {


    private final RentalRepository repository;

    public List<Rental> getAllRentals(){
        return repository.findAll();
    }

    public Optional<Rental> getRent(final Long id){
        return repository.findById(id);
    }

    public Rental saveRental(final Rental rental){
        return repository.save(rental);
    }

    public void deleteRental(Long id){
        repository.deleteById(id);
    }

    public Stream<Rental> getUserByRentalId(final Long id){
        return repository.findAll().stream().filter(rental -> rental.getUser().getId().equals(id));
    }
}
