package com.tamchack.tamchack.repository.book;

import com.tamchack.tamchack.domain.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository <Book, Integer> {

    Page<Book> findAllByNameContains(String bookName, Pageable page);

    @Query(value = "select * from tbl_book book " +
                    "join tbl_stock stock on book.id=stock.book_id " +
                    "join tbl_store store on stock.store_id = ?1 " +
                    "where book.name like ?2", nativeQuery = true)
    Page<Book> findAllByStoreIdAndNameContains(Integer storeId, String bookName, Pageable page);

    Optional<Book> findAllByName(Book name);

}