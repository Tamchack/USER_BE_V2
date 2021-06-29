package com.tamchack.tamchack.domain.member;

import com.tamchack.tamchack.domain.store.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "tbl_storeuser")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Storeuser {

    @Id
    private String id;

    private String password;

    @JoinColumn(name = "store_id")
    @OneToOne(mappedBy = "storeuser")
    private Store store;

    public Storeuser update(String password) {
        this.password = password;
        return this;
    }

}
