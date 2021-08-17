package com.tamchack.tamchack.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tbl_admin")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    private String id;

    private String password;

}
