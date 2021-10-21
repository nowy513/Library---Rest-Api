package com.book.library.controller;

import com.book.library.domain.*;
import com.book.library.mapper.RentalMapper;
import com.book.library.service.RentalService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(RentalController.class)
public class RentalConrtrollerTest {

    @MockBean
    RentalService rentalService;

    @MockBean
    RentalMapper rentalMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldFetchEmptyList() throws Exception {

//        Given
        List<RentalDto> rentalDtoList = new ArrayList<>();

        when(rentalMapper.mapToRentalList(rentalService.getAllRentals())).thenReturn(rentalDtoList);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/rental/allRentals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldGetAllRentals() throws Exception{

//        Given
        RentalDto rentalDto = new RentalDto(
                1L, LocalDate.of(2021, 5, 10), LocalDate.of(2021, 6, 10), 2L, 3L
        );
        List<RentalDto> rentalDtos = new ArrayList<>();
        rentalDtos.add(rentalDto);

        when(rentalMapper.mapToRentalList(any())).thenReturn(rentalDtos);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/rental/allRentals")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldGetRentalById() throws Exception{

        //        Given
        Rental rental = new Rental(
                1L, LocalDate.of(2021, 5, 10), LocalDate.of(2021, 6, 10), new CopiesOfBooks(), new User()
        );

        RentalDto rentalDto = new RentalDto(
                1L, LocalDate.of(2021, 5, 10), LocalDate.of(2021, 6, 10), 2L, 3L
        );

        when(rentalService.getRent(1L)).thenReturn(Optional.of(rental));
        when(rentalMapper.mapToRentalDto(any())).thenReturn(rentalDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/rental/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfLoan", Matchers.is(LocalDate.of(2021, 5, 10).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfReturn", Matchers.is(LocalDate.of(2021, 6, 10).toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.copiesOfBooksId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    public void shouldCreateRental() throws Exception{

//        Given
        RentalDto rentalDto = new RentalDto(
                1L, LocalDate.of(2021, 5, 10), LocalDate.of(2021, 6, 10), new CopiesOfBooks(), new User()
        );

        when(rentalService.saveRental(any(Rental.class))).thenReturn(new Rental(
                1L, LocalDate.of(2020, 5, 10), LocalDate.of(2021, 6, 10), new CopiesOfBooks(), new User()
        ));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(rentalDto);

//        When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .post("/rental/rental")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldDeleteRental() throws Exception{

//        Given
        Rental rental = new Rental(
                1L, LocalDate.of(2021, 5, 10), LocalDate.of(2021, 6, 10), new CopiesOfBooks(), new User()
        );

        when(rentalService.saveRental(rental)).thenReturn(rental);

//        When & Then
        mockMvc.perform(MockMvcRequestBuilders
        .delete("/rental/1"))
        .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
