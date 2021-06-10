package com.tamchack.tamchack.repository;

import com.tamchack.tamchack.domain.store.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

    Page<Store> findAllByName(String storeName, Pageable page);

    Store findByNumber(String number);

    Store findByOpeningHours(String openingHours);

}
