package com.tamchack.admin.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserListResponse {

    private final int totalPages;
    private final List<UserResponse> userResponses;

}
