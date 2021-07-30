package com.tamchack.tamchack.domain.book;

import com.tamchack.tamchack.domain.store.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "tbl_stock")
@Getter
@Builder
@IdClass(StockKey.class)
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "store_id")
    private Store store;

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "book_id")
    private Book book;

}
