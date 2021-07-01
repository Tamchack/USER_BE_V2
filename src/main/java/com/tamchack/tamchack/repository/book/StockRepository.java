package com.tamchack.tamchack.repository.book;

import com.tamchack.tamchack.domain.book.Book;
import com.tamchack.tamchack.domain.book.Stock;
import com.tamchack.tamchack.domain.book.StockKey;
import com.tamchack.tamchack.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockKey> {

    boolean existsByStoreAndBook(Store store, Book book);

    void deleteByStoreAndBook(Store store, Book book);

}
