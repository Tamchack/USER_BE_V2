package com.tamchack.tamchack.service.book;

import com.tamchack.tamchack.domain.book.Book;
import com.tamchack.tamchack.dto.request.book.BookRequest;
import com.tamchack.tamchack.dto.request.book.ReportBookRequest;
import com.tamchack.tamchack.dto.request.book.StockRequest;
import com.tamchack.tamchack.dto.response.address.ApplicationListResponse;
import com.tamchack.tamchack.dto.response.book.BookResponse;
import org.springframework.data.domain.Pageable;

public interface BookService {

    void inputBook(BookRequest bookRequest, Book bookName);

    BookResponse getBook(Integer bookId);

    void bookStock(StockRequest stockRequest);

    void ReportBook(ReportBookRequest reportBookRequest);

    ApplicationListResponse searchBook(String query, Pageable page);

    ApplicationListResponse searchBookInStore(Integer storeId, String query, Pageable page);
}
