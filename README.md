# 1. Description

back-end mechanism for an library application, written in JAVA.

# 2.StartUP

The project can be run locally by executing the LibraryApplication class that runs Spring ApplicationContext. During this time, you can access via the local address on port 8080.

# 3. Endpoints description

The project consists of 4 controllers:

BookController:
@RequestMapping("/book")


@GetMapping("/allBooks") - displays all books

@GetMapping("/author/{author}") - finds the book by the author

@GetMapping("/title/{title}") - finds the book by the title

@GetMapping("/{bookId}") - finds the book by the book Id

@GetMapping("/book/{userId}") - finds user's books

@PostMapping("/book") - adds a book

@DeleteMapping("/{id}") - deletes the book by book id


CopiesofBooksController:
@RequestMapping("/copiesOfBooks")

@GetMapping("/availableCopy") - displays available books

@GetMapping("/copiesNotAvailable") - displays unavailable books

Rental Controller:
@RequestMapping("/rental")

@GetMapping("/allRentals") - displays all rentals

@GetMapping("/{rentalId}") - finds the rental by the rental Id

@GetMapping("/rental/{userId}") - finds all of the user's rentals

@PostMapping("/rental") - adds rental

@DeleteMapping("/{rentalId}") - deletes the rental by rental id

UserController:
@RequestMapping("/user")

@GetMapping("/allUsers") - displays all users

@GetMapping("/{userId}") - finds user by the user Id

@GetMapping("/{userName}/{userSurname}") - finds user by name and surname

@PostMapping("/user") - adds user

@DeleteMapping("/{userId}") - deletes the user by user id



