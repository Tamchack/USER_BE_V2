package com.tamchack.tamchack.repository;

import com.tamchack.tamchack.domain.book.Book;
import com.tamchack.tamchack.domain.book.Stock;
import com.tamchack.tamchack.domain.book.StockKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockKey> {

    boolean existsByStoreIdAndBook(Integer storeId, Book book);

    void deleteByStoreIdAndBook(Integer storeId, Book book);

}
