package com.tamchack.tamchack.repository.book;

import com.tamchack.tamchack.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclarationBookRepository extends JpaRepository<Book, Integer> {

    boolean existsByBookAndUserId(Book bookId, String userId);

    void deleteByUserId(String userId);

}
