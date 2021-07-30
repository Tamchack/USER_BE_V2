package com.tamchack.admin.controller.user;

import com.tamchack.admin.payload.response.UserListResponse;
import com.tamchack.admin.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public UserListResponse getUsers(@RequestParam Pageable pageable) {
        return userService.getListUser(pageable);
    }

    @DeleteMapping("{/userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

}
