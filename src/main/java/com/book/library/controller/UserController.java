package com.book.library.controller;

import com.book.library.domain.*;
import com.book.library.mapper.BookMapper;
import com.book.library.mapper.RentalMapper;
import com.book.library.mapper.UserMapper;
import com.book.library.service.BookService;
import com.book.library.service.RentalService;
import com.book.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final  UserService userService;

    private final UserMapper userMapper;

    private final BookService bookService;

    private final BookMapper bookMapper;

    private final RentalMapper rentalMapper;

    private final RentalService rentalService;


    @GetMapping("/allUser")
    public List<UserDto> getAllUser(){
        return userMapper.mapToUserList(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public Stream<BookDto> findUsersBooks(@PathVariable Long userId) {
        return bookMapper.mapToBookListDto(bookService.getAllBooks()).stream()
                .filter(user -> user.getId().equals(userId));

    }

    @GetMapping("/{userId}")
    public UserDto findUserById(@PathVariable Long userId) throws Exception{
        return userMapper.mapToUserDto(userService.getUserById(userId).orElseThrow(Exception::new));
    }

    @GetMapping("/{userName}/{userSurname}")
    public Stream<UserDto> findUserByNameSurname(@PathVariable String userName,
                                         @PathVariable String userSurname){
        return userMapper.mapToUserList(userService.getAllUsers()).stream()
                .filter(userDto -> userDto.getName().equals(userName))
                .filter(userDto -> userDto.getSurname().equals(userSurname));
    }

    @GetMapping("/{userId}")
    public Stream<RentalDto> getAllRentalUser(@PathVariable Long userId){
        return rentalMapper.mapToRentalDtoList(rentalService.getAllRentals()).stream()
                .filter(rentalDto -> rentalDto.getUserId().equals(userId));
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody UserDto userDto){
        return userService.saveUser(userMapper.mapToUser(userDto));
    }

    @PutMapping("/updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto){
        return userMapper.mapToUserDto(userService.saveUser(userMapper.mapToUser(userDto)));
    }



    @DeleteMapping("/{userId}")
    public void deleteRental(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

}
