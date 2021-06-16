package com.tamchack.admin.enitity.repository;

import com.tamchack.admin.enitity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<Store, Integer> {

    Page<Store> findAllBy(Pageable pageable);

}