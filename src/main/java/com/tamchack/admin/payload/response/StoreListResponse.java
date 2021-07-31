package com.tamchack.admin.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StoreListResponse {

    private final int totalPages;
    private final List<StoreResponse> storeResponses;

}
