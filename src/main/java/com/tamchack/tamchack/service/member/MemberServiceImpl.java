package com.tamchack.tamchack.service.member;

import com.tamchack.tamchack.domain.book.Book;
import com.tamchack.tamchack.domain.store.Bookmark;
import com.tamchack.tamchack.dto.request.member.ReviseInformationRequest;
import com.tamchack.tamchack.dto.response.book.StockResponse;
import com.tamchack.tamchack.dto.response.store.StoreResponse;
import com.tamchack.tamchack.exception.UserNotFoundException;
import com.tamchack.tamchack.repository.book.BookRepository;
import com.tamchack.tamchack.repository.book.StockRepository;
import com.tamchack.tamchack.repository.member.StoreuserRepository;
import com.tamchack.tamchack.repository.member.UserRepository;
import com.tamchack.tamchack.repository.store.BookmarkRepository;
import com.tamchack.tamchack.repository.store.StoreRepository;
import com.tamchack.tamchack.security.token.JWTProvider;
import com.tamchack.tamchack.domain.member.Storeuser;
import com.tamchack.tamchack.domain.member.User;
import com.tamchack.tamchack.domain.store.Store;
import com.tamchack.tamchack.exception.UserAlreadyExistsException;
import com.tamchack.tamchack.dto.request.member.StoreuserSignUpRequest;
import com.tamchack.tamchack.dto.request.member.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final UserRepository userRepository;
    private final StoreuserRepository storeuserRepository;
    private final StoreRepository storeRepository;
    private final BookmarkRepository bookmarkRepository;
    private final BookRepository bookRepository;
    private final StockRepository stockRepository;
    private final JWTProvider jwtProvider;

    @Override
    public void userSignUp(UserSignUpRequest userSignUpRequest, StoreuserSignUpRequest storeuserSignUpRequest) {

        userRepository.findById(userSignUpRequest.getId())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException();
                });

        storeuserRepository.findById(storeuserSignUpRequest.getId())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException();
                });

        userRepository.save(
                User.builder()
                        .id(userSignUpRequest.getId())
                        .password(userSignUpRequest.getPassword())
                        .name(userSignUpRequest.getName())
                        .build()
        );

    }

    @Override
    public void storeuserSignUp(StoreuserSignUpRequest storeuserSignUpRequest, UserSignUpRequest userSignUpRequest) {

        storeuserRepository.findById(storeuserSignUpRequest.getId())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException();
                });

        userRepository.findById(userSignUpRequest.getId())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException();
                });

        storeuserRepository.save(
                Storeuser.builder()
                            .id(storeuserSignUpRequest.getId())
                            .password(storeuserSignUpRequest.getPassword())
                            .build()
        );

        storeRepository.save(
                Store.builder()
                        .id(storeuserSignUpRequest.getStoreId())
                        .name(storeuserSignUpRequest.getStoreName())
                        .address(storeuserSignUpRequest.getStoreAddress())
                        .number(storeuserSignUpRequest.getStoreNumber())
                        .openingHours(storeuserSignUpRequest.getOpeningHours())
                        .lat(storeuserSignUpRequest.getLat())
                        .lng(storeuserSignUpRequest.getLng())
                        .build()
        );

    }

    @Override
    public void updateUserInformation(ReviseInformationRequest reviseInformationRequest, String token) {

        String password = reviseInformationRequest.getPassword();

        User user = userRepository.findById(jwtProvider.parseToken(token))
                .orElseThrow(UserNotFoundException::new);

        userRepository.save(user.update(password));
    }

    @Override
    public void updateStoreuserInformation(ReviseInformationRequest reviseInformationRequest, String token) {

        String password = reviseInformationRequest.getPassword();
        String number = reviseInformationRequest.getStoreNumber();
        String openingHours = reviseInformationRequest.getOpeningHours();
        String address = reviseInformationRequest.getAddress();
        
        Storeuser storeuser = storeuserRepository.findById(jwtProvider.parseToken(token))
                .orElseThrow(UserNotFoundException::new);

        Store store = storeuser.getStore();

        storeuserRepository.save(storeuser.update(password));
        storeRepository.save(store.update(number, openingHours, address));

    }

    @Override
    public List<StockResponse> getStockList(Integer storeId) {

        List<Book> books = bookRepository.findAll();

        List<StockResponse> stockResponses = new ArrayList<>();

        for(Book book : books){
            boolean stock = stockRepository.existsByStoreIdAndBook(storeId, book);
            stockResponses.add(
                    StockResponse.builder()
                            .bookName(book.getName())
                            .author(book.getAuthor())
                            .publisher(book.getPublisher())
                            .stock(stock)
                            .build()
            );
        }

        return stockResponses;
    }

    @Override
    public List<StoreResponse> getBookmarkList(String token) {

        String userId = jwtProvider.parseToken(token);

        List<Bookmark> bookmarks = bookmarkRepository.findAllByUserId(userId);

        List<StoreResponse> storeResponses = new ArrayList<>();

        for(Bookmark bookmark : bookmarks){
            Store store = bookmark.getStore();
            storeResponses.add(
                    StoreResponse.builder()
                            .storeId(store.getId())
                            .name(store.getName())
                            .openingHours(store.getOpeningHours())
                            .number(store.getNumber())
                            .build()
            );
        }

        return storeResponses;
    }
}