package com.tamchack.admin.payload.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class StoreUserResponse {

    private final String id;
    private final String name;

}
