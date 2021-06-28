package com.tamchack.tamchack.repository.member;

import com.tamchack.tamchack.domain.member.Storeuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreuserRepository extends JpaRepository<Storeuser, String> {

    Optional<Storeuser> findByIdAndPassword(String id, String password);

}
