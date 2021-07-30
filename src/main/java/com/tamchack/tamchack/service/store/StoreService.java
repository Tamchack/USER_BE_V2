package com.tamchack.tamchack.service.store;

import com.tamchack.tamchack.dto.request.store.BookmarkRequest;
import com.tamchack.tamchack.dto.request.store.ReportStoreRequest;
import com.tamchack.tamchack.dto.response.address.ApplicationListResponse;
import com.tamchack.tamchack.dto.response.store.StoreResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreService {

    void bookmarkStore(BookmarkRequest bookmarkRequest);

    void reportStore(ReportStoreRequest reportStoreRequest);

    ApplicationListResponse searchStore(String query, Pageable page);

    List<StoreResponse> searchStorePlace(double lat, double lng);

    StoreResponse getStore(Integer storeId);

}
