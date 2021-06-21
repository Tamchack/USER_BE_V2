package com.tamchack.tamchack.repository.book;

import com.tamchack.tamchack.domain.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository <Book, Integer> {

    Page<Book> findAllByNameContains(String bookName, Pageable page);

    Page<Book> findAllByStoreIdAndNameContains(Integer storeId, String bookName, Pageable page);

    Optional<Book> findAllByName(String name);

}