package com.tamchack.admin.controller.user;

import com.tamchack.admin.payload.response.UserListResponse;
import com.tamchack.admin.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserListResponse getUsers(Pageable pageable) {

        return userService.getListUser(pageable);

    }
}
