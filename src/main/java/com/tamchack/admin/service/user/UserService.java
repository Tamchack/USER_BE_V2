package com.tamchack.admin.service.user;

import com.tamchack.admin.payload.response.UserListResponse;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserListResponse getListUser(Pageable pageable);

}
