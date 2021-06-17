package com.tamchack.admin.payload.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class UserListResponse {

    private int totalPages;

    List<UserResponse> userResponses;

}
