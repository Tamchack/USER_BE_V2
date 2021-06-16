package com.tamchack.admin.enitity.repository;

import com.tamchack.admin.enitity.StoreUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreUserRepository extends CrudRepository<StoreUser, Integer> {

    Page<StoreUser> findAllBy(Pageable pageable);

}