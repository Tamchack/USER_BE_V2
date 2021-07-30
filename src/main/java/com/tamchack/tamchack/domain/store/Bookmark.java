package com.tamchack.tamchack.domain.store;

import com.tamchack.tamchack.domain.member.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "tbl_bookmark")
@Getter
@Builder
@IdClass(BookmarkKey.class)
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

}
