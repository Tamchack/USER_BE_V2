package com.tamchack.tamchack.controller.book;

import com.tamchack.tamchack.dto.request.book.BookRequest;
import com.tamchack.tamchack.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public void inputBook(@RequestParam String name,
                          @RequestParam String author,
                          @RequestParam String publisher,
                          @RequestParam MultipartFile image){
        bookService.inputBook()
    }

    @GetMapping("/search")
    public void searchBook(@RequestParam String title,
                           @PageableDefault Pageable pageable){
        bookService.searchBook(title, pageable);
    }

}
