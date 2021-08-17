package com.tamchack.admin.controller.bestseller;

import com.tamchack.admin.payload.response.BestSellerListResponse;
import com.tamchack.admin.service.bestseller.BestSellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/bestseller")
@RequiredArgsConstructor
public class BestSellerController {

    private final BestSellerService bestSellerService;

    @GetMapping("/list")
    public BestSellerListResponse getBooks(@RequestParam Pageable pageable) {
        return bestSellerService.getListBestSeller(pageable);
    }

    @GetMapping("/management")
    public BestSellerListResponse getBestSeller() {
        return bestSellerService.choiceBestSeller();
    }

}
