package com.tamchack.admin.payload.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class StoreUserListResponse {

    private int totalPages;

    List<StoreUserResponse> storeUserResponses;

}
