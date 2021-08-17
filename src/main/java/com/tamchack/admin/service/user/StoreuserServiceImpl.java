package com.tamchack.admin.service.user;

import com.tamchack.admin.payload.response.StoreUserListResponse;
import com.tamchack.admin.payload.response.StoreUserResponse;
import com.tamchack.tamchack.domain.member.Storeuser;
import com.tamchack.tamchack.exception.UserNotFoundException;
import com.tamchack.tamchack.repository.member.StoreuserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreuserServiceImpl implements StoreuserService {

    private final StoreuserRepository storeuserRepository;

    @Override
    public StoreUserListResponse getListUser(Pageable pageable) {
        Page<Storeuser> stores = storeuserRepository.findAllBy(pageable);

        int totalPages = stores.getTotalPages();

        List<StoreUserResponse> mapStoreusers = stores.map(this::buildStoreuser)
                .stream().collect(Collectors.toList());

        return new StoreUserListResponse(totalPages, mapStoreusers);
    }

    private StoreUserResponse buildStoreuser(Storeuser storeuser) {
        return StoreUserResponse.builder()
                .id(storeuser.getId())
                .name(storeuser.getStore().getName())
                .build();
    }

    @Override
    public void deleteStoreuser(String storeuserId) {
        Storeuser storeuser = storeuserRepository.findById(storeuserId)
                .orElseThrow(UserNotFoundException::new);


        storeuserRepository.delete(storeuser);
    }
}
