package com.tamchack.tamchack.domain.book;

import com.tamchack.tamchack.domain.store.Store;
import lombok.Builder;

import java.io.Serializable;

@Builder
public class StockKey implements Serializable {

    private Store store;

    private Book book;

}
