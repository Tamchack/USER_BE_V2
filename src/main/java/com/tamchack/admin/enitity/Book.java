package com.tamchack.admin.enitity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private int id;

    private String name;

    private String author;

    private String publisher;

}