package com.tamchack.admin.controller.user;

import com.tamchack.admin.payload.response.StoreListResponse;
import com.tamchack.admin.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/storeuser")
@RequiredArgsConstructor
public class StoreUserController {

    private final StoreService storeService;

    @GetMapping("/list")
    public StoreListResponse getStoreUsers(@RequestParam Pageable pageable) {
        return storeService.getListStore(pageable);
    }
    @DeleteMapping("{/storeuserId}")
    public void deleteStoreuser(@PathVariable int storeuserId) {
        storeService.deleteStore(storeuserId);
    }

}
