package com.tamchack.tamchack.dto.response.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {

    private Integer bookId;

    private String name;

    private String author;

    private String publisher;

    private String contents;

    private MultipartFile imageName;

}
