package com.book.library.controller;

import com.book.library.domain.*;
import com.book.library.mapper.BookMapper;
import com.book.library.mapper.RentalMapper;
import com.book.library.mapper.UserMapper;
import com.book.library.service.BookService;
import com.book.library.service.RentalService;
import com.book.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;


    @GetMapping("/allUsers")
    public List<UserDto> getAllUser(){
        return userMapper.mapToUserList(userService.getAllUsers());
    }


    @GetMapping("/{userId}")
    public UserDto findUserById(@PathVariable Long userId) throws Exception{
        return userMapper.mapToUserDto(userService.getUserById(userId).orElseThrow(Exception::new));
    }

    @GetMapping("/{userName}/{userSurname}")
    public List<UserDto> findUserByNameSurname(@PathVariable String userName,
                                         @PathVariable String userSurname){
        return userMapper.mapToUserStream(userService.getUserByNameAndSurname(userName, userSurname));
    }

    @PostMapping("/user")
    public User createUser(@RequestBody UserDto userDto){
        return userService.saveUser(userMapper.mapToUser(userDto));
    }

    @DeleteMapping("/{userId}")
    public void deleteRental(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

}
