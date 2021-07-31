package com.tamchack.admin.payload.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class UserResponse {

    private final String id;
    private final String name;

}
