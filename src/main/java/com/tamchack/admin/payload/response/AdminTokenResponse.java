package com.tamchack.admin.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminTokenResponse {

    private final String accessToken;
    private final String refreshToken;

}
