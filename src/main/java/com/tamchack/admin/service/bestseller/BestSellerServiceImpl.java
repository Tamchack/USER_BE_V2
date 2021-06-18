package com.tamchack.admin.service.bestseller;

import com.tamchack.admin.payload.response.BestSellerListResponse;
import com.tamchack.admin.payload.response.BestSellerResponse;
import com.tamchack.tamchack.domain.book.Book;
import com.tamchack.tamchack.repository.book.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BestSellerServiceImpl implements BestSellerService {

    private final BookRepository bookRepository;

    @Override
    public BestSellerListResponse getListBestSeller(Pageable pageable) {
        Page<Book> books = bookRepository.findAllBy(pageable);

        return new BestSellerListResponse(books.getTotalPages(),
                books.map(this::buildBestSeller)
                        .stream().collect(Collectors.toList()));
    }

    private BestSellerResponse buildBestSeller(Book book) {
        return BestSellerResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .build();
    }
}
