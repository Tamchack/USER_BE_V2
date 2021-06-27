package com.tamchack.tamchack.controller.member;

import com.tamchack.tamchack.domain.member.Storeuser;
import com.tamchack.tamchack.dto.request.member.ReviseInformationRequest;
import com.tamchack.tamchack.dto.request.member.StoreuserSignUpRequest;
import com.tamchack.tamchack.dto.request.member.UserSignUpRequest;
import com.tamchack.tamchack.dto.response.book.StockResponse;
import com.tamchack.tamchack.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storeuser")
@RequiredArgsConstructor
public class StoreuserController {

    private final MemberService memberService;

    @PostMapping
    public void storeuserSignUp(@RequestBody UserSignUpRequest userSignUpRequest,
                                @RequestBody StoreuserSignUpRequest storeuserSignUpRequest) {

        memberService.storeuserSignUp(userSignUpRequest, storeuserSignUpRequest);
    }

    @PostMapping("/update")
    public void updateStoreuserInformation(@RequestBody ReviseInformationRequest reviseInformationRequest,
                                           @AuthenticationPrincipal Storeuser storeuser) {

        memberService.updateStoreuserInformation(reviseInformationRequest, storeuser);

    }

    @PutMapping("/stock")
    public List<StockResponse> getStockList(@RequestBody Integer storeId) {

        return memberService.getStockList(storeId);

    }

}
