package com.tamchack.admin.controller.bestseller;

import com.tamchack.admin.payload.response.BestSellerListResponse;
import com.tamchack.admin.service.bestseller.BestSellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BestSellerController {

    private final BestSellerService bestSellerService;

    @GetMapping("/best/list")
    public BestSellerListResponse getBooks(Pageable pageable) {

        return bestSellerService.getListBestSeller(pageable);

    }
}
