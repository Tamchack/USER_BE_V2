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

    private boolean report;

    private boolean bestseller;

    public Book setReport(boolean report) {
        this.report = report;
        return this;
    }

}
