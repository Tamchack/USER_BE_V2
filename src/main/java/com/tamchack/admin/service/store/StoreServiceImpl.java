package com.tamchack.admin.service.store;

import com.tamchack.admin.payload.response.StoreListResponse;
import com.tamchack.admin.payload.response.StoreResponse;
import com.tamchack.tamchack.domain.store.Store;
import com.tamchack.tamchack.repository.store.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public StoreListResponse getListStore(Pageable pageable) {
        Page<Store> stores = storeRepository.findAllBy(pageable);

        return new StoreListResponse(stores.getTotalPages(),
                stores.map(this::buildStore)
                        .stream().collect(Collectors.toList()));
    }

    private StoreResponse buildStore(Store store) {
        return StoreResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .build();
    }
}
