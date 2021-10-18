package com.book.library.repository;

import com.book.library.domain.CopiesOfBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopiesOfBooksRepository extends JpaRepository<CopiesOfBooks, Long> {
}
