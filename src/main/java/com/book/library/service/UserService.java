package com.book.library.service;

import com.book.library.domain.User;
import com.book.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public Optional<User> getUserById( Long id){
        return repository.findById(id);
    }

    public User saveUser(final User user){
        return repository.save(user);
    }

    public void deleteUser(Long id){
        repository.deleteById(id);
    }
}
