package com.book.library.mapper;

import com.book.library.domain.User;
import com.book.library.domain.UserDto;
import com.book.library.repository.BookRepository;
import com.book.library.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserMapper {


    private final BookRepository bookRepository;

    private final RentalRepository rentalRepository;

    public User mapToUser(final UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getCreationDate(),
                bookRepository.getOne(userDto.getBookId()),
                rentalRepository.getOne(userDto.getRentalId())
        );
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getCreationDate(),
                user.getBook(),
                user.getRental()

        );
    }

    public List<UserDto> mapToUserList(final List<User> users){
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> mapToUserStream(final Stream<User> userStream){
        return userStream.sorted()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
