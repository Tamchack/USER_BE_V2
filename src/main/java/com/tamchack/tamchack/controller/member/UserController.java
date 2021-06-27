package com.tamchack.tamchack.controller.member;

import com.tamchack.tamchack.domain.member.User;
import com.tamchack.tamchack.dto.request.member.ReviseInformationRequest;
import com.tamchack.tamchack.dto.request.member.StoreuserSignUpRequest;
import com.tamchack.tamchack.dto.request.member.UserSignUpRequest;
import com.tamchack.tamchack.dto.response.store.StoreResponse;
import com.tamchack.tamchack.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final MemberService memberService;

    @PostMapping
    public void userSignUp(@RequestBody UserSignUpRequest userSignUpRequest,
                           @RequestBody StoreuserSignUpRequest storeuserSignUpRequest) {

        memberService.userSignUp(userSignUpRequest, storeuserSignUpRequest);

    }

    @PostMapping
    public void updateUserInformation(@RequestBody ReviseInformationRequest reviseInformationRequest,
                                      @AuthenticationPrincipal User user) {

        memberService.updateUserInformation(reviseInformationRequest, user);

    }

    @GetMapping
    public List<StoreResponse> getBookmarkList(@RequestBody String token) {

       return memberService.getBookmarkList(token);

    }



}
