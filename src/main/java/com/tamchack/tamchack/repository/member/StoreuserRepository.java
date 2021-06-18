package com.tamchack.tamchack.repository.member;

import com.tamchack.tamchack.domain.member.Storeuser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreuserRepository extends JpaRepository<Storeuser, String> {

    Optional<Storeuser> findByIdAndPassword(String id, String password);

    Page<Storeuser> findAllBy(Pageable pageable);

}
