package com.tamchack.tamchack.service.member;

import com.tamchack.tamchack.dto.request.member.ReviseInformationRequest;
import com.tamchack.tamchack.dto.request.member.StoreuserSignUpRequest;
import com.tamchack.tamchack.dto.request.member.UserSignUpRequest;
import com.tamchack.tamchack.dto.response.book.StockResponse;
import com.tamchack.tamchack.dto.response.store.StoreResponse;

import java.util.List;

public interface MemberService {

    void userSignUp(UserSignUpRequest userSignUpRequest, StoreuserSignUpRequest storeuserSignUpRequest);

    void storeuserSignUp(StoreuserSignUpRequest storeuserSignUpRequest, UserSignUpRequest userSignUpRequest);

    void updateUserInformation(ReviseInformationRequest reviseInformationRequest, String token);

    void updateStoreuserInformation(ReviseInformationRequest reviseInformationRequest, String token);

    List<StockResponse> getStockList(Integer storeId);

    List<StoreResponse> getBookmarkList(String token);

}
