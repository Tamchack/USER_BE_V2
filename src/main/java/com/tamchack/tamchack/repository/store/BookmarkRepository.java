package com.tamchack.tamchack.repository;

import com.tamchack.tamchack.domain.store.Bookmark;
import com.tamchack.tamchack.domain.store.BookmarkKey;
import com.tamchack.tamchack.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkKey> {

    boolean existsByStoreAndUserId(Store storeId, String userId);

    void deleteByStoreAndUserId(Store storeId, String userId);

    List<Bookmark> findAllByUserId(String userId);

}
