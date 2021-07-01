package com.tamchack.tamchack.service.member;

import com.tamchack.tamchack.domain.member.Storeuser;
import com.tamchack.tamchack.domain.member.User;
import com.tamchack.tamchack.domain.store.Store;
import com.tamchack.tamchack.dto.request.member.ReviseInformationRequest;
import com.tamchack.tamchack.dto.request.member.StoreuserSignUpRequest;
import com.tamchack.tamchack.dto.request.member.UserSignUpRequest;
import com.tamchack.tamchack.dto.response.book.StockResponse;
import com.tamchack.tamchack.dto.response.store.StoreResponse;

import java.util.List;

public interface MemberService {

    void userSignUp(UserSignUpRequest userSignUpRequest);

    void storeuserSignUp(StoreuserSignUpRequest storeuserSignUpRequest);

    void updateUserInformation(ReviseInformationRequest reviseInformationRequest, User user);

    void updateStoreuserInformation(ReviseInformationRequest reviseInformationRequest, Storeuser storeuser);

    List<StockResponse> getStockList(Store store);

    List<StoreResponse> getBookmarkList(User user);

}
