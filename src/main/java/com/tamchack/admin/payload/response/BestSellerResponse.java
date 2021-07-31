package com.tamchack.admin.payload.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class BestSellerResponse {

    private final int id;
    private final String name;
    private final String author;
    private final String publisher;

}
