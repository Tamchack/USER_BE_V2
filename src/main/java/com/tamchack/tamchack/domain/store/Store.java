package com.tamchack.tamchack.domain.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "tbl_store")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    private int id;

    private String name;

    private String address;

    private String number;

    private String timezone;

    private double lat;

    private double lng;

    private boolean report;

    public Store update(String number, String timezone, String address) {
        this.number = number;
        this.address = address;
        this.timezone = timezone;
        return this;
    }

    public Store setReport (boolean report) {
        this.report = report;
        return this;
    }

}
