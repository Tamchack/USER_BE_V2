package com.tamchack.tamchack.repository.member;

import com.tamchack.tamchack.domain.member.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, String> {

    Optional<User> findByIdAndPassword(String id, String password);

    Page<User> findAllBy(Pageable pageable);

}
