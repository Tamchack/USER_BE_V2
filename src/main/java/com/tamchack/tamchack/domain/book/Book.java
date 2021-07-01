package com.tamchack.tamchack.domain.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tbl_book")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private int id;

    private String name;

    private String author;

    private String publisher;

    private String contents;

    private String imageName;

    private boolean declaration;

    public Book setDeclaration(boolean declaration) {
        this.declaration = declaration;
        return this;
    }

}
