package com.tamchack.admin.service.user;

import com.tamchack.admin.payload.response.StoreUserListResponse;
import com.tamchack.admin.payload.response.StoreUserResponse;
import com.tamchack.tamchack.domain.member.Storeuser;
import com.tamchack.tamchack.repository.member.StoreuserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StoreUserServiceImpl implements StoreUserService {

    private final StoreuserRepository storeuserRepository;

    @Override
    public StoreUserListResponse getListUser(Pageable pageable) {
        return null;
    }

    private StoreUserResponse buildStore(Storeuser storeuser) {
        return StoreUserResponse.builder()
                .id(storeuser.getId())
                .password(storeuser.getPassword())
                .name(storeuser.getStore().getName())
                .build();
    }
}
