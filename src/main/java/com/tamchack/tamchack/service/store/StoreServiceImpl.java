package com.tamchack.tamchack.service.store;

import com.tamchack.tamchack.domain.member.User;
import com.tamchack.tamchack.domain.store.Bookmark;
import com.tamchack.tamchack.domain.store.Store;
import com.tamchack.tamchack.dto.request.store.BookmarkRequest;
import com.tamchack.tamchack.dto.request.store.ReportStoreRequest;
import com.tamchack.tamchack.dto.response.address.ApplicationListResponse;
import com.tamchack.tamchack.dto.response.store.StoreResponse;
import com.tamchack.tamchack.exception.StoreNotFoundException;
import com.tamchack.tamchack.exception.UserNotFoundException;
import com.tamchack.tamchack.repository.member.StoreuserRepository;
import com.tamchack.tamchack.repository.member.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public StoreResponse getStore(Integer storeId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(StoreNotFoundException::new);

        return StoreResponse.builder()
                .name(store.getName())
                .address(store.getAddress())
                .number(store.getNumber())
                .timezone(store.getTimezone())
                .build();

    }

    @Override
    public void bookmarkStore(BookmarkRequest bookmarkRequest) {

        Store store = storeRepository.findById(bookmarkRequest.getStoreId())
                .orElseThrow(StoreNotFoundException::new);

        User user = userRepository.findById(bookmarkRequest.getUserId())
                .orElseThrow(UserNotFoundException::new);

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
    public void reportStore(ReportStoreRequest reportStoreRequest) {

        int storeId = reportStoreRequest.getStoreId();

        Store store = storeRepository.findById(storeId)
                .orElseThrow(StoreNotFoundException::new);

        if(!store.isReport()) {
            storeRepository.save(
                    store.setReport(true)
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
                            .timezone(store.getTimezone())
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
