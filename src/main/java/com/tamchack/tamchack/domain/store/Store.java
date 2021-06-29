package com.tamchack.tamchack.domain.store;

import com.tamchack.tamchack.domain.member.Storeuser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.tokens.Token;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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

    private String openingHours;

    private double lat;

    private double lng;

    private boolean declaration;

    @OneToOne
    private Storeuser storeuser;

    public Store update(String number, String openingHours, String address) {
        this.number = number;
        this.address = address;
        this.openingHours = openingHours;
        return this;
    }

    public Store setDeclaration(boolean declaration) {
        this.declaration = declaration;
        return this;
    }

}
