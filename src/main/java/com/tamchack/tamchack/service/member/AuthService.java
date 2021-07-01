package com.tamchack.tamchack.service.member;

import com.tamchack.tamchack.dto.request.member.SignInRequest;
import com.tamchack.tamchack.dto.response.member.TokenResponse;

public interface AuthService {

    TokenResponse signIn(SignInRequest signInRequest);

}