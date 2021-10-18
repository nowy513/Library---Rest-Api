package com.book.library.service;

import com.book.library.domain.Rental;
import com.book.library.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService {


    private final RentalRepository repository;

    public List<Rental> getAllRentals(){
        return repository.findAll();
    }

    public Optional<Rental> getRent(Long id){
        return repository.findById(id);
    }

    public Rental saveRental(final Rental rental){
        return repository.save(rental);
    }

    public void deleteRental(Long id){
        repository.deleteById(id);
    }
}
