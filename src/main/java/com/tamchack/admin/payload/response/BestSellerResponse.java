package com.tamchack.admin.payload.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestSellerResponse {

    private Integer id;

    private String name;

    private String author;

    private String publisher;

}