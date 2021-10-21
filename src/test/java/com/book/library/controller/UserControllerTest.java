package com.book.library.controller;

import com.book.library.domain.Book;
import com.book.library.domain.Rental;
import com.book.library.domain.User;
import com.book.library.domain.UserDto;
import com.book.library.mapper.UserMapper;
import com.book.library.service.UserService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    UserService userService;

    @MockBean
    UserMapper userMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldFetchEmptyList() throws Exception{

//        Given
        List<UserDto> userDtos = new ArrayList<>();

        when(userMapper.mapToUserList(userService.getAllUsers())).thenReturn(userDtos);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/user/allUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldGetAllUser() throws Exception{

//        Given

        UserDto userDto = new UserDto(
               1L, "John", "Smith", LocalDate.of(2020, 2, 5), 2L, 3L
        );

        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto);

        when(userMapper.mapToUserList(userService.getAllUsers())).thenReturn(userDtos);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/user/allUsers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldGetUserById() throws Exception{

//       Given
        User user = new User(
                1L, "John", "Smith", LocalDate.of(2020, 2, 5), new Book(), new Rental()
        );
        UserDto userDto = new UserDto(
                1L, "John", "Smith", LocalDate.of(2020, 2, 5), 2L, 3L
        );

        when(userService.getUserById(1L)).thenReturn(Optional.of(user));
        when(userMapper.mapToUserDto(any())).thenReturn(userDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("Smith")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creationDate", Matchers.is(LocalDate.of(2020, 2, 5).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentalId", Matchers.is(3)));
    }

    @Test
    public void shouldCreateUser() throws Exception{

//        Given
        UserDto userDto = new UserDto(
                1L, "John", "Smith", LocalDate.of(2020, 2, 5), new Book(), new Rental()
        );

        when(userService.saveUser(any(User.class))).thenReturn(new User(
                1L, "John", "Smith", LocalDate.of(2020, 2, 5), new Book(), new Rental()
        ));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldDeleteUser() throws Exception{

//        Given
        User user = new User(
                1L, "John", "Smith", LocalDate.of(2020, 2, 5), new Book(), new Rental()
        );

        when(userService.saveUser(user)).thenReturn(user);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .delete("/user/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldGetUserByNameAndSurname() throws Exception {

//        Given
        User user = new User(
                1L, "John", "Smith", LocalDate.of(2020, 2, 5), new Book(), new Rental()
        );

        UserDto userDto = new UserDto(
                1L, "John", "Smith", LocalDate.of(2020, 2, 5), 2L, 3L
        );

        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto);

        when(userService.getUserByNameAndSurname("John", "Smith")).thenReturn(Stream.of(user));
        when(userMapper.mapToUserStream(any())).thenReturn(userDtos);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/user/John/Smith")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }


}
