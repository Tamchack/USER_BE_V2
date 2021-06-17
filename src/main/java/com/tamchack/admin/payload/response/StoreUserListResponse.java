package com.tamchack.admin.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StoreUserListResponse {

    private int totalPages;

    List<StoreUserResponse> storeUserResponses;

}
