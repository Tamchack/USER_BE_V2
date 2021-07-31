package com.tamchack.admin.service.store;

import com.tamchack.admin.payload.response.StoreListResponse;
import org.springframework.data.domain.Pageable;

public interface StoreService {

    StoreListResponse getListStore(Pageable pageable);

    void deleteStore(int storeId);

}
