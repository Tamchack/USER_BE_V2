package com.tamchack.tamchack.dto.request.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreuserSignUpRequest {

    private String id;

    private int storeId;

    private String password;

    private String name;

    private String address;

    private String openingHours;

    private String number;

    private double lat;

    private double lng;

}
