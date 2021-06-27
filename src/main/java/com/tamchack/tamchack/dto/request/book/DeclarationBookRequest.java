package com.tamchack.tamchack.dto.request.book;

import com.tamchack.tamchack.domain.book.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeclarationBookRequest {

    private Book bookId;

    private String userId;

    private String token;
}
