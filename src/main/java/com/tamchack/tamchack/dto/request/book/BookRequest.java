package com.tamchack.tamchack.dto.request.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    private String name;

    private String author;

    private String publisher;

    private String content;

    private MultipartFile image;

}
