package com.tamchack.admin.service.bestseller;

import com.tamchack.admin.payload.response.BestSellerListResponse;
import org.springframework.data.domain.Pageable;

public interface BestSellerService {

    BestSellerListResponse getListBestSeller(Pageable pageable);

    BestSellerListResponse choiceBestSeller();

}
