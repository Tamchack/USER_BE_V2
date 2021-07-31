package com.tamchack.admin.service.store;

import com.tamchack.admin.payload.response.StoreListResponse;
import com.tamchack.admin.payload.response.StoreResponse;
import com.tamchack.tamchack.domain.store.Store;
import com.tamchack.tamchack.exception.StoreNotFoundException;
import com.tamchack.tamchack.repository.store.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;


    @Override
    public StoreListResponse getListStore(Pageable pageable) {
        Page<Store> stores = storeRepository.findAllBy(pageable);

        int totalPages = stores.getTotalPages();

        List<StoreResponse> mapStore = stores.map(this::buildStore)
                .stream().collect(Collectors.toList());

        return new StoreListResponse(totalPages, mapStore);
    }

    private StoreResponse buildStore(Store store) {
        return StoreResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .report(store.isDeclaration())
                .build();
    }

    @Override
    @Transactional
    public void deleteStore(int storeId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(StoreNotFoundException::new);

        storeRepository.delete(store);

    }
}
