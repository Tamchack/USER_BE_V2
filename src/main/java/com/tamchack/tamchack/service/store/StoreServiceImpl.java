package com.tamchack.tamchack.service.store;

import com.tamchack.tamchack.domain.member.Storeuser;
import com.tamchack.tamchack.domain.store.Bookmark;
import com.tamchack.tamchack.domain.store.Store;
import com.tamchack.tamchack.dto.request.store.BookmarkRequest;
import com.tamchack.tamchack.dto.request.store.DeclarationStoreRequest;
import com.tamchack.tamchack.dto.request.member.ReviseInformationRequest;
import com.tamchack.tamchack.dto.response.address.ApplicationListResponse;
import com.tamchack.tamchack.dto.response.store.StoreResponse;
import com.tamchack.tamchack.exception.StoreNotFoundException;
import com.tamchack.tamchack.exception.UserNotFoundException;
import com.tamchack.tamchack.repository.store.BookmarkRepository;
import com.tamchack.tamchack.repository.store.DeclarationStoreRepository;
import com.tamchack.tamchack.repository.store.StoreRepository;
import com.tamchack.tamchack.repository.member.StoreuserRepository;
import com.tamchack.tamchack.security.token.JWTProvider;
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
    private final DeclarationStoreRepository declarationStoreRepository;
    private final JWTProvider jwtProvider;

    @Override //서점 즐겨찾기
    public void bookmarkStore(BookmarkRequest bookmarkRequest) {

        Store storeId = bookmarkRequest.getStoreId();
        String userId = bookmarkRequest.getUserId();

        boolean isBookmarked = bookmarkRepository.existsByStoreAndUserId(storeId, userId);

        if(isBookmarked) {
            bookmarkRepository.deleteByStoreAndUserId(storeId, userId);
        } else {
            bookmarkRepository.save(
                    Bookmark.builder()
                            .store(storeId)
                            .userId(userId)
                            .build()
            );
        }
    }

    @Override //서점 신고하기
    public void DeclarationStore(DeclarationStoreRequest declarationStoreRequest) {

        Store storeId = declarationStoreRequest.getStoreId();
        String userId = declarationStoreRequest.getUserId();

        boolean isDeclaration = declarationStoreRepository.existsByStoreAndUserId(storeId, userId);

        if(isDeclaration) {
            declarationStoreRepository.deleteByUserId(userId);
        } else {
            declarationStoreRepository.save(
                    Store.builder()
                            .userId(userId)
                            .build()
            );
        }

    }

    @Override //서점 검색
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

    @Override //서점 정보 보기
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

}
