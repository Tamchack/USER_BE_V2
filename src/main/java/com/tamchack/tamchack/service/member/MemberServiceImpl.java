package com.tamchack.tamchack.service.member;

import com.tamchack.tamchack.domain.book.Book;
import com.tamchack.tamchack.domain.store.Bookmark;
import com.tamchack.tamchack.dto.request.member.UpdateInformationRequest;
import com.tamchack.tamchack.dto.response.book.StockResponse;
import com.tamchack.tamchack.dto.response.store.StoreResponse;
import com.tamchack.tamchack.repository.book.BookRepository;
import com.tamchack.tamchack.repository.book.StockRepository;
import com.tamchack.tamchack.repository.member.StoreuserRepository;
import com.tamchack.tamchack.repository.member.UserRepository;
import com.tamchack.tamchack.repository.store.BookmarkRepository;
import com.tamchack.tamchack.repository.store.StoreRepository;
import com.tamchack.tamchack.security.token.JwtProvider;
import com.tamchack.tamchack.domain.member.Storeuser;
import com.tamchack.tamchack.domain.member.User;
import com.tamchack.tamchack.domain.store.Store;
import com.tamchack.tamchack.exception.UserAlreadyExistsException;
import com.tamchack.tamchack.dto.request.member.StoreuserSignUpRequest;
import com.tamchack.tamchack.dto.request.member.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

    @Override
    public void userSignUp(UserSignUpRequest userSignUpRequest) {

        userRepository.findById(userSignUpRequest.getId())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException();
                });

        storeuserRepository.findById(userSignUpRequest.getId())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException();
                });

        userRepository.save(
                User.builder()
                        .id(userSignUpRequest.getId())
                        .password(passwordEncoder.encode(userSignUpRequest.getPassword()))
                        .name(userSignUpRequest.getName())
                        .build()
        );

    }

    @Override
    public void storeuserSignUp(StoreuserSignUpRequest storeuserSignUpRequest) {

        userRepository.findById(storeuserSignUpRequest.getId())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException();
                });

        storeuserRepository.findById(storeuserSignUpRequest.getId())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException();
                });

        Store store = storeRepository.save(
                Store.builder()
                        .name(storeuserSignUpRequest.getName())
                        .address(storeuserSignUpRequest.getAddress())
                        .number(storeuserSignUpRequest.getNumber())
                        .timezone(storeuserSignUpRequest.getTimezone())
                        .lat(storeuserSignUpRequest.getLat())
                        .lng(storeuserSignUpRequest.getLng())
                        .build()
        );

        storeuserRepository.save(
                Storeuser.builder()
                        .id(storeuserSignUpRequest.getId())
                        .password(passwordEncoder.encode(storeuserSignUpRequest.getPassword()))
                        .store(store)
                        .build()
        );

    }

    @Override
    public void updateUserInformation(UpdateInformationRequest updateInformationRequest, User user) {

        String password = updateInformationRequest.getPassword();

        userRepository.save(user.update(password));
    }

    @Override
    public void updateStoreuserInformation(UpdateInformationRequest updateInformationRequest, Storeuser storeuser) {

        String password = updateInformationRequest.getPassword();

        String number = updateInformationRequest.getStoreNumber();
        String timezone = updateInformationRequest.getTimezone();
        String address = updateInformationRequest.getAddress();

        Store store = storeuser.getStore();

        storeuserRepository.save(storeuser.update(password));
        storeRepository.save(store.update(number, timezone, address));

    }

    @Override
    public List<StockResponse> getStockList(Store store) {

        List<Book> books = bookRepository.findAll();

        List<StockResponse> stockResponses = new ArrayList<>();

        for(Book book : books){
            boolean stock = stockRepository.existsByStoreAndBook(store, book);
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
    public List<StoreResponse> getBookmarkList(User user) {

        List<Bookmark> bookmarks = bookmarkRepository.findAllByUser(user);

        List<StoreResponse> storeResponses = new ArrayList<>();

        for(Bookmark bookmark : bookmarks){
            Store store = bookmark.getStore();
            storeResponses.add(
                    StoreResponse.builder()
                            .storeId(store.getId())
                            .name(store.getName())
                            .timezone(store.getTimezone())
                            .number(store.getNumber())
                            .build()
            );
        }

        return storeResponses;
    }
}