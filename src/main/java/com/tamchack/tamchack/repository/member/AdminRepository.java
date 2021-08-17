package com.tamchack.tamchack.repository.member;

import com.tamchack.tamchack.domain.member.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, String> {

    Optional<Admin> findAllByIdAndPassword(String id, String password);

}
