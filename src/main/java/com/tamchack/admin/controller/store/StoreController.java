package com.tamchack.admin.controller.store;

import com.tamchack.admin.payload.response.StoreListResponse;
import com.tamchack.admin.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/admin/store")
@RestController
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/list")
    public StoreListResponse getStores(@RequestParam Pageable pageable) {
        return storeService.getListStore(pageable);
    }

    @DeleteMapping("{/bookId}")
    public void deleteStore(@PathVariable int bookId) {
        storeService.deleteStore(bookId);
    }

}
