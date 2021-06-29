package com.tamchack.tamchack.service.book;

import com.tamchack.tamchack.domain.book.Book;
import com.tamchack.tamchack.domain.book.Stock;
import com.tamchack.tamchack.domain.store.Store;
import com.tamchack.tamchack.dto.request.book.BookRequest;
import com.tamchack.tamchack.dto.request.book.DeclarationBookRequest;
import com.tamchack.tamchack.dto.request.book.StockRequest;
import com.tamchack.tamchack.dto.response.address.ApplicationListResponse;
import com.tamchack.tamchack.dto.response.book.BookResponse;
import com.tamchack.tamchack.exception.BookAlreadyExistsException;
import com.tamchack.tamchack.exception.BookNotFoundException;
import com.tamchack.tamchack.exception.StoreNotFoundException;
import com.tamchack.tamchack.repository.book.BookRepository;
import com.tamchack.tamchack.repository.book.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final StockRepository stockRepository;

    @Value("${image.upload.dir}")
    private String imagePath;

    @SneakyThrows
    @Override
    public void inputBook(BookRequest bookRequest, Book bookName) {

        Book book = bookRepository.findAllByName(bookName)
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

    @Override
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

    @Override
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

    @Override
    public void DeclarationBook(DeclarationBookRequest declarationBookRequest) {

        int bookId = declarationBookRequest.getBookId();

        Book book = bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);

        if(!book.isDeclaration()) {
            bookRepository.save(
                    book.setDeclaration(true)
            );
        }
    }

    @Override
    public ApplicationListResponse searchBook(String query, Pageable page) {
        return getApplications(bookRepository
                .findAllByNameContains(query, page));
    }

    @Override
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