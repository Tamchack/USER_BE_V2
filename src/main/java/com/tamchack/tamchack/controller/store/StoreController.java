package com.tamchack.tamchack.controller.store;

import com.tamchack.tamchack.domain.store.Store;
import com.tamchack.tamchack.dto.request.store.BookmarkRequest;
import com.tamchack.tamchack.dto.request.store.DeclarationStoreRequest;
import com.tamchack.tamchack.dto.response.address.ApplicationListResponse;
import com.tamchack.tamchack.dto.response.store.StoreResponse;
import com.tamchack.tamchack.service.book.BookService;
import com.tamchack.tamchack.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final BookService bookService;

    @PutMapping("/{storeId")
    public void bookmarkStore(@RequestBody BookmarkRequest bookmarkRequest) {

        storeService.bookmarkStore(bookmarkRequest);

    }

    @DeleteMapping("/{storeId}")
    public void DeclarationStore(@RequestBody DeclarationStoreRequest declarationStoreRequest) {

        storeService.declarationStore(declarationStoreRequest);

    }

    @GetMapping("/{storeId}")
    public StoreResponse getStore(@PathVariable Integer storeId){

        return storeService.getStore(storeId);

    }

    @GetMapping("/search")
    public ApplicationListResponse searchStore(@RequestParam String query, @PageableDefault Pageable page) {

        return storeService.searchStore(query, page);

    }

    public List<StoreResponse> searchStorePlace(@RequestParam double lat,
                                                @RequestParam double lng) {

        return storeService.searchStorePlace(lat, lng);

    }

    @GetMapping("/{storeId}/search")
    public ApplicationListResponse searchBookInStore(@RequestBody Integer storeId,
                                                     @RequestBody String query,
                                                     @PageableDefault Pageable page) {

        return bookService.searchBookInStore(storeId, query, page);

    }

}
