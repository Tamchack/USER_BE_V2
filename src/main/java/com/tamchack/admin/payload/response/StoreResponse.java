package com.tamchack.admin.payload.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {

    private int id;

    private String name;

    private String address;

}
