package com.tamchack.admin.enitity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreUser {

    @Id
    private String id;

    private String password;

    private String name;

    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;

}