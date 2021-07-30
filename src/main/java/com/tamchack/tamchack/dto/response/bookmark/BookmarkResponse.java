package com.tamchack.tamchack.dto.response.bookmark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookmarkResponse {

    private String name;

    private String timezone;

    private String number;

}
