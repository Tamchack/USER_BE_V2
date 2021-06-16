package com.tamchack.tamchack.controller.store;

import com.tamchack.tamchack.dto.response.store.StoreResponse;
import com.tamchack.tamchack.service.book.BookService;
import com.tamchack.tamchack.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final BookService bookService;

    @GetMapping("/search")
    public void searchStore(@RequestParam String name,
                            @PageableDefault Pageable pageable){
        storeService.searchStore(name, pageable);
    }

    @GetMapping("/{storeId}")
    public StoreResponse getStore(@PathVariable Integer storeId){
        return storeService.getStore(storeId);
    }
}
