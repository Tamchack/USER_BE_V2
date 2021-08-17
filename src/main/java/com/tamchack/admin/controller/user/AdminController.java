package com.tamchack.admin.controller.user;

import com.tamchack.admin.payload.request.AdminSignInRequest;
import com.tamchack.admin.payload.response.AdminTokenResponse;
import com.tamchack.admin.service.user.AdminAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminAuthService adminAuthService;

    @PostMapping("/auth")
    public AdminTokenResponse aminSignIn(@RequestBody AdminSignInRequest adminSignInRequest) {
        return adminAuthService.adminSignIn(adminSignInRequest);
    }

}
