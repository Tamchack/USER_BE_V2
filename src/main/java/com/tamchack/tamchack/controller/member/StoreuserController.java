package com.tamchack.tamchack.controller.member;

import com.tamchack.tamchack.domain.member.Storeuser;
import com.tamchack.tamchack.domain.store.Store;
import com.tamchack.tamchack.dto.request.member.UpdateInformationRequest;
import com.tamchack.tamchack.dto.request.member.StoreuserSignUpRequest;
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
    public void storeuserSignUp(@RequestBody StoreuserSignUpRequest storeuserSignUpRequest) {

        memberService.storeuserSignUp(storeuserSignUpRequest);
    }

    @PostMapping("/update")
    public void updateStoreuserInformation(@RequestBody UpdateInformationRequest updateInformationRequest,
                                           @AuthenticationPrincipal Storeuser storeuser) {

        memberService.updateStoreuserInformation(updateInformationRequest, storeuser);

    }

    @PutMapping("/stock")
    public List<StockResponse> getStockList(@RequestBody Store store) {

        return memberService.getStockList(store);

    }

}
