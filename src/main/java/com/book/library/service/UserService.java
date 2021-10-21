package com.book.library.service;

import com.book.library.domain.Book;
import com.book.library.domain.Rental;
import com.book.library.domain.User;
import com.book.library.repository.BookRepository;
import com.book.library.repository.RentalRepository;
import com.book.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final BookRepository bookRepository;

    private final RentalRepository rentalRepository;

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public Optional<User> getUserById(final Long id){
        return repository.findById(id);
    }

    public User saveUser(final User user){
        return repository.save(user);
    }

    public void deleteUser(Long id){
        repository.deleteById(id);
    }

    public Stream<User> getUserByNameAndSurname(final String name, final  String surname){
        return repository.findAll().stream().filter(user -> user.getName().equals(name))
                .filter(user -> user.getSurname().equals(surname));
    }

}
