package com.tamchack.admin.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserListResponse {

    private int totalPages;

    List<UserResponse> userResponses;

}
