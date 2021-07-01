package com.tamchack.tamchack.service.store;

import com.tamchack.tamchack.domain.member.User;
import com.tamchack.tamchack.domain.store.Bookmark;
import com.tamchack.tamchack.domain.store.Store;
import com.tamchack.tamchack.dto.request.store.BookmarkRequest;
import com.tamchack.tamchack.dto.request.store.DeclarationStoreRequest;
import com.tamchack.tamchack.dto.response.address.ApplicationListResponse;
import com.tamchack.tamchack.dto.response.store.StoreResponse;
import com.tamchack.tamchack.exception.StoreNotFoundException;
import com.tamchack.tamchack.repository.member.StoreuserRepository;
import com.tamchack.tamchack.repository.store.BookmarkRepository;
import com.tamchack.tamchack.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final BookmarkRepository bookmarkRepository;
    private final StoreuserRepository storeuserRepository;

    @Override
    public StoreResponse getStore(Integer storeId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(StoreNotFoundException::new);

        return StoreResponse.builder()
                .name(store.getName())
                .address(store.getAddress())
                .number(store.getNumber())
                .openingHours(store.getOpeningHours())
                .build();

    }

    @Override
    public void bookmarkStore(BookmarkRequest bookmarkRequest) {

        Store store = bookmarkRequest.getStoreId();
        User user = bookmarkRequest.getUserId();

        boolean isBookmarked = bookmarkRepository.existsByStoreAndUser(store, user);

        if(isBookmarked) {
            bookmarkRepository.deleteByStoreAndUser(store, user);
        } else {
            bookmarkRepository.save(
                    Bookmark.builder()
                            .store(store)
                            .user(user)
                            .build()
            );
        }
    }

    @Override
    public void declarationStore(DeclarationStoreRequest declarationStoreRequest) {

        int storeId = declarationStoreRequest.getStoreId();

        Store store = storeRepository.findById(storeId)
                .orElseThrow(StoreNotFoundException::new);

        if(!store.isDeclaration()) {
            storeRepository.save(
                    store.setDeclaration(true)
            );
        }

    }

    @Override
    public ApplicationListResponse searchStore(String query, Pageable page) {

        Page<Store> storePage = storeRepository
                .findAllByName(query, page);

        List<StoreResponse> storeResponses = new ArrayList<>();

        for(Store store : storePage){
            storeResponses.add(
                    StoreResponse.builder()
                            .name(store.getName())
                            .address(store.getAddress())
                            .number(store.getNumber())
                            .openingHours(store.getOpeningHours())
                            .build()
            );
        }

        return ApplicationListResponse.builder()
                .totalElements((int)storePage.getTotalElements())
                .totalPages(storePage.getTotalPages())
                .applicationResponses(storeResponses)
                .build();
    }

    @Override
    public List<StoreResponse> searchStorePlace(double lat, double lng) {

        List<Store> stores = storeRepository.findByLocation(lat, lng);

        List<StoreResponse> storeResponses = new ArrayList<>();

        for (Store store : stores) {
            storeResponses.add(
                    StoreResponse.builder()
                            .storeId(store.getId())
                            .name(store.getName())
                            .lat(store.getLat())
                            .lng(store.getLng())
                            .build()
            );
        }

        return storeResponses;

    }

}
