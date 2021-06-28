package com.tamchack.tamchack.controller.book;

import com.tamchack.tamchack.domain.book.Book;
import com.tamchack.tamchack.dto.request.book.BookRequest;
import com.tamchack.tamchack.dto.request.book.DeclarationBookRequest;
import com.tamchack.tamchack.dto.request.book.StockRequest;
import com.tamchack.tamchack.dto.response.address.ApplicationListResponse;
import com.tamchack.tamchack.dto.response.book.BookResponse;
import com.tamchack.tamchack.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public void inputBook(@RequestBody BookRequest bookRequest,
                          @RequestBody Book bookName) {

        bookService.inputBook(bookRequest, bookName);

    }

    @GetMapping("{/bookId}")
    public BookResponse getBook(@RequestBody Integer bookId) {

       return bookService.getBook(bookId);

    }

    @PutMapping
    public void bookStock(@RequestBody StockRequest stockRequest) {

        bookService.bookStock(stockRequest);

    }

    @PutMapping("/{bookId}")
    public void DeclarationBook(@RequestBody DeclarationBookRequest declarationBookRequest) {

        bookService.DeclarationBook(declarationBookRequest);

    }

    @GetMapping("/search")
    public ApplicationListResponse searchBook(@RequestBody String query,
                                              @PageableDefault Pageable page) {

        return bookService.searchBook(query, page);

    }

}