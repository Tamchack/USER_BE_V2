package com.tamchack.admin.controller.user;

import com.tamchack.admin.payload.response.StoreListResponse;
import com.tamchack.admin.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreUserController {

    private final StoreService storeService;

    @GetMapping
    public StoreListResponse getStoreUsers(Pageable pageable) {

        return storeService.getListStore(pageable);

    }
}
