package com.tamchack.tamchack.service.book;

import com.tamchack.tamchack.domain.book.Book;
import com.tamchack.tamchack.domain.book.Stock;
import com.tamchack.tamchack.domain.member.Storeuser;
import com.tamchack.tamchack.domain.member.User;
import com.tamchack.tamchack.dto.request.book.BookRequest;
import com.tamchack.tamchack.dto.request.book.DeclarationBookRequest;
import com.tamchack.tamchack.dto.request.book.StockRequest;
import com.tamchack.tamchack.dto.response.address.ApplicationListResponse;
import com.tamchack.tamchack.dto.response.book.BookResponse;
import com.tamchack.tamchack.exception.BookAlreadyExistsException;
import com.tamchack.tamchack.exception.BookNotFoundException;
import com.tamchack.tamchack.exception.UserNotFoundException;
import com.tamchack.tamchack.repository.book.BookRepository;
import com.tamchack.tamchack.repository.book.DeclarationBookRepository;
import com.tamchack.tamchack.repository.book.ImageRepository;
import com.tamchack.tamchack.repository.book.StockRepository;
import com.tamchack.tamchack.repository.member.StoreuserRepository;
import com.tamchack.tamchack.repository.member.UserRepository;
import com.tamchack.tamchack.security.token.JWTProvider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final StockRepository stockRepository;
    private final UserRepository userRepository;
    private final StoreuserRepository storeuserRepository;
    private final DeclarationBookRepository declarationBookRepository;
    private final ImageRepository imageRepository;
    private final JWTProvider jwtProvider;

    @Value("${book.image.path}")
    private String imagePath;

    @SneakyThrows
    @Override //책 등록
    public void inputBook(BookRequest bookRequest, String name, String token) {

        User user = userRepository.findById(jwtProvider.parseToken(token))
                .orElseThrow(UserNotFoundException::new);

        Storeuser storeuser = storeuserRepository.findById(jwtProvider.parseToken(token))
                .orElseThrow(UserNotFoundException::new);

        Book book = bookRepository.findAllByName(name)
                .orElseThrow(BookAlreadyExistsException::new);

        String fileName = UUID.randomUUID().toString();

        File file = new File(imagePath, fileName);

        bookRepository.save(
                Book.builder()
                        .name(bookRequest.getName())
                        .author(bookRequest.getAuthor())
                        .publisher(bookRequest.getPublisher())
                        .contents(bookRequest.getContents())
                        .imageName(fileName)
                        .build()
        );

        bookRequest.getImage().transferTo(file);

    }

    @Override //책 상세보기
    public BookResponse getBook(Integer bookId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);

        return BookResponse.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .contents(book.getContents())
                .imageName(book.getImageName())
                .build();

    }

    @Override //서점 책 재고
    public void bookStock(StockRequest stockRequest) {

        Book book = stockRequest.getBookId();
        int storeId = stockRequest.getStoreId();

        boolean isStocked = stockRepository.existsByStoreIdAndBook(storeId, book);

        if(isStocked) {
            stockRepository.deleteByStoreIdAndBook(storeId, book);
        } else {
            stockRepository.save(
                    Stock.builder()
                            .book(book)
                            .storeId(storeId)
                            .build()
            );
        }
    }

    @Override //책 신고
    public void DeclarationBook(DeclarationBookRequest declarationBookRequest) {

        Book bookId = declarationBookRequest.getBookId();
        String userId = declarationBookRequest.getUserId();

        boolean isDeclaration = declarationBookRepository.existsByBookAndUserId(bookId, userId);

        if(isDeclaration) {
            declarationBookRepository.deleteByUserId(userId);
        } else {
            declarationBookRepository.save(
                    Book.builder()
                            .userId(userId)
                            .build()
            );
        }

    }

    @Override //책 검색(메인)
    public ApplicationListResponse searchBook(String query, Pageable page) {
        return getApplications(bookRepository
                .findAllByNameContains(query, page));
    }

    @Override //서점 정보 내 책 검색
    public ApplicationListResponse searchBookInStore(Integer storeId, String query, Pageable page) {
        return getApplications(bookRepository
                .findAllByStoreIdAndNameContains(storeId, query, page));
    }

    private ApplicationListResponse getApplications(Page<Book> bookPage) {

        List<BookResponse> bookResponses = new ArrayList<>();

        for(Book book : bookPage){
            bookResponses.add(
                    BookResponse.builder()
                            .name(book.getName())
                            .author(book.getAuthor())
                            .publisher(book.getPublisher())
                            .build()
            );
        }

        return ApplicationListResponse.builder()
                .totalElements((int)bookPage.getTotalElements())
                .totalPages(bookPage.getTotalPages())
                .applicationResponses(bookResponses)
                .build();
    }

}