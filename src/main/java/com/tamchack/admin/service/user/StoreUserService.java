package com.tamchack.admin.service.user;

import com.tamchack.admin.payload.response.StoreUserListResponse;
import org.springframework.data.domain.Pageable;

public interface StoreUserService {

    StoreUserListResponse getListUser(Pageable pageable);

}
