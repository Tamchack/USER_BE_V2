package com.tamchack.admin.payload.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class StoreResponse {

    private final int id;
    private final String name;
    private final String address;
    private final boolean report;

}
