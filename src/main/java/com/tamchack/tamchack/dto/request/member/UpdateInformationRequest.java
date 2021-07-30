package com.tamchack.tamchack.dto.request.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInformationRequest {

    private String id;

    private String password;

    private String address;

    private String storeNumber;

    private String timezone;

}
