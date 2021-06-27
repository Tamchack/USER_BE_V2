package com.tamchack.tamchack.repository.book;

import com.tamchack.tamchack.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeclarationBookRepository extends JpaRepository<Book, Integer> {

    boolean existsByBookAndUserId(Book bookId, String userId);

    void deleteByUserId(String userId);

}
