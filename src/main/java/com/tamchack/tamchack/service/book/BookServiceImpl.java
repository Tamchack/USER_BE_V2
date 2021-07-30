package com.tamchack.tamchack.service.book;

import com.tamchack.tamchack.domain.book.Book;
import com.tamchack.tamchack.domain.book.Stock;
import com.tamchack.tamchack.domain.store.Store;
import com.tamchack.tamchack.dto.request.book.BookRequest;
import com.tamchack.tamchack.dto.request.book.ReportBookRequest;
import com.tamchack.tamchack.dto.request.book.StockRequest;
import com.tamchack.tamchack.dto.response.address.ApplicationListResponse;
import com.tamchack.tamchack.dto.response.book.BookResponse;
import com.tamchack.tamchack.exception.BookAlreadyExistsException;
import com.tamchack.tamchack.exception.BookNotFoundException;
import com.tamchack.tamchack.exception.StoreNotFoundException;
import com.tamchack.tamchack.repository.book.BookRepository;
import com.tamchack.tamchack.repository.book.StockRepository;
import com.tamchack.tamchack.repository.store.StoreRepository;
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
    private final StoreRepository storeRepository;

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
                        .contents(bookRequest.getContent())
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

        Store store = storeRepository.findById(stockRequest.getStoreId())
                .orElseThrow(StoreNotFoundException::new);

        Book book = bookRepository.findById(stockRequest.getBookId())
                .orElseThrow(BookNotFoundException::new);

        boolean isStocked = stockRepository.existsByStoreAndBook(store, book);

        if(isStocked) {
            stockRepository.deleteByStoreAndBook(store, book);
        } else {
            stockRepository.save(
                    Stock.builder()
                            .book(book)
                            .store(store)
                            .build()
            );
        }
    }

    @Override
    public void ReportBook(ReportBookRequest reportBookRequest) {

        int bookId = reportBookRequest.getBookId();

        Book book = bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);

        if(!book.isReport()) {
            bookRepository.save(
                    book.setReport(true)
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
                .findAllByStoreIdAndNameContains(storeId, "%" + query + "%", page));

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