package com.tamchack.tamchack.domain.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tbl_store")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    private int id;

    private String userId;

    private String storeuserId;

    private String name;

    private String address;

    private String number;

    private String openingHours;

    private double lat;

    private double lng;

    public Store update(String number, String openingHours, String address) {
        this.number = number;
        this.address = address;
        this.openingHours = openingHours;
        return this;
    }

}
