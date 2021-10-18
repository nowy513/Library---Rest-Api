package com.book.library.service;

import com.book.library.domain.Book;
import com.book.library.domain.Rental;
import com.book.library.domain.User;
import com.book.library.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void testGetAllUsers(){

//        Given
        Book book = new Book();

        Rental rental = new Rental();

        User user = new User(
                1L, "John", "Smith", LocalDate.of(2021,5, 20),
                book, rental
        );

        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepository.findAll()).thenReturn(users);

//        When
        List<User> userList = userService.getAllUsers();

//        Then
        assertEquals(1, userList.size());
    }

    @Test
    public void getUser(){

        //        Given
        Book book = new Book();

        Rental rental = new Rental();

        User user = new User(
                1L, "John", "Smith", LocalDate.of(2021,5, 20),
                book, rental
        );

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

//        When
        Optional<User> getUser = userService.getUserById(1L);

//        Then
        assertEquals(Optional.of(user), getUser);
    }

    @Test
    public void saveUser(){

        //        Given
        Book book = new Book();

        Rental rental = new Rental();

        User user = new User(
                1L, "John", "Smith", LocalDate.of(2021,5, 20),
                book, rental
        );

        when(userRepository.save(user)).thenReturn(user);

//        When
        User saveUser = userService.saveUser(user);

//        Then
        assertEquals("John",saveUser.getName());
    }

    @Test
    public void deleteUser(){

        //        Given
        Book book = new Book();

        Rental rental = new Rental();

        User user = new User(
                1L, "John", "Smith", LocalDate.of(2021,5, 20),
                book, rental
        );

        Long id = user.getId();
        userService.deleteUser(id);

//        When
        Optional<User> deleteUser = userService.getUserById(1L);

//        Then
        assertFalse(deleteUser.isPresent());
    }
}
