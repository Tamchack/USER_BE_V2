package com.tamchack.admin.enitity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Store {

    @Id
    private int id;

    private String name;

    private String owner;

    @OneToOne
    @JoinColumn(name = "store_id")
    private StoreUser storeUser;

    @OneToMany(mappedBy = "store")
    private List<User> user;

}