package com.tamchack.tamchack.dto.request.book;

import com.tamchack.tamchack.domain.book.Book;
import com.tamchack.tamchack.domain.store.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockRequest {

    private Integer storeId;

    private Integer bookId;

}