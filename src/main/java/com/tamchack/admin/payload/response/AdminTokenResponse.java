package com.tamchack.admin.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class AdminTokenResponse {

    private final String accessToken;
    private final String refreshToken;

}
