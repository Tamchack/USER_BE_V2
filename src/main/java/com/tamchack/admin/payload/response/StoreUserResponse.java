package com.tamchack.admin.payload.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreUserResponse {

    private String id;

    private String password;

    private String name;

}
