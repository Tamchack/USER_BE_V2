package com.tamchack.admin.enitity.repository;

import com.tamchack.admin.enitity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    Page<User> findAllBy(Pageable pageable);

}
