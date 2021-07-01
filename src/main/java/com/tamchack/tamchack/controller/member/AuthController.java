package com.tamchack.tamchack.controller.member;

import com.tamchack.tamchack.dto.request.member.SignInRequest;
import com.tamchack.tamchack.dto.response.member.TokenResponse;
import com.tamchack.tamchack.service.member.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public TokenResponse signIn(@RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

}
