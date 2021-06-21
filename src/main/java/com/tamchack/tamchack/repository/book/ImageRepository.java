package com.tamchack.tamchack.repository.book;

import com.tamchack.tamchack.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ImageRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByBookIdOrderById(Integer bookId);

}
