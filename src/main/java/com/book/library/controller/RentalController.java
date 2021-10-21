package com.book.library.controller;

import com.book.library.domain.Rental;
import com.book.library.domain.RentalDto;
import com.book.library.domain.User;
import com.book.library.domain.UserDto;
import com.book.library.mapper.RentalMapper;
import com.book.library.mapper.UserMapper;
import com.book.library.service.RentalService;
import com.book.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/rental")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    private final RentalMapper rentalMapper;


    @GetMapping("/allRentals")
    public List<RentalDto> getAllRentals(){
        return rentalMapper.mapToRentalList(rentalService.getAllRentals());
    }

    @GetMapping("/{rentalId}")
    public RentalDto findRentalById(@PathVariable Long rentalId) throws Exception{
        return rentalMapper.mapToRentalDto(rentalService.getRent(rentalId).orElseThrow(Exception::new));
    }

    @GetMapping("/rental/{userId}")
    public List<RentalDto> getAllRentalUser(@PathVariable Long userId){
        return rentalMapper.mapToRentalStream(rentalService.getUserByRentalId(userId));
    }

    @PostMapping("/rental")
    public Rental createRental(@RequestBody RentalDto rentalDto){
        return rentalService.saveRental(rentalMapper.mapToRental(rentalDto));
    }

    @DeleteMapping("/{rentalId}")
    public void deleteRentalById(@PathVariable Long rentalId){
        rentalService.deleteRental(rentalId);
    }

}
