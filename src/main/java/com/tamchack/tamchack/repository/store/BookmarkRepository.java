package com.tamchack.tamchack.repository.store;

import com.tamchack.tamchack.domain.member.User;
import com.tamchack.tamchack.domain.store.Bookmark;
import com.tamchack.tamchack.domain.store.BookmarkKey;
import com.tamchack.tamchack.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkKey> {

    boolean existsByStoreAndUser(Store store, User user);

    void deleteByStoreAndUser(Store store, User user);

    List<Bookmark> findAllByUser(User user);

}
