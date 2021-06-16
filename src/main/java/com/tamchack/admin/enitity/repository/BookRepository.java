package com.tamchack.admin.enitity.repository;

import com.tamchack.admin.enitity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    Page<Book> findAllBy(Pageable pageable);

}
