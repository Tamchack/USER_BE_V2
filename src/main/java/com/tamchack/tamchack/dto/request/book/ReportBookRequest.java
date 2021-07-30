package com.tamchack.tamchack.dto.request.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportBookRequest {

    private Integer bookId;

    private String userId;

    private String token;
}
