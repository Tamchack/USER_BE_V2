package com.tamchack.tamchack.repository.store;

import com.tamchack.tamchack.domain.store.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

    Page<Store> findAllByName(String storeName, Pageable page);

    Store findByNumber(String number);

    Store findByOpeningHours(String openingHours);

    @Query(value = "SELECT * (6371*acos(cos(radians(?1))*cos(radians(lat))*cos(radians(lng)" +
                    "-radians(?2))+sin(radians(?1))*sin(radians(lat)))) " +
                    "AS distance FROM tbl_store HAVING distance <= 1", nativeQuery = true)
    List<Store> findByLocation(double lat, double lng);

}
