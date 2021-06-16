package com.tamchack.tamchack.repository.store;

import com.tamchack.tamchack.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeclarationStoreRepository extends JpaRepository<Store, Integer> {

    boolean existsByStoreAndUserId(Store storeId, String userId);

    void deleteByUserId(String userId);

}
