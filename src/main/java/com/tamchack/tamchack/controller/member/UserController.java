package com.tamchack.tamchack.controller.member;

import com.tamchack.tamchack.domain.member.User;
import com.tamchack.tamchack.dto.request.member.UpdateInformationRequest;
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
    public void userSignUp(@RequestBody UserSignUpRequest userSignUpRequest) {

        memberService.userSignUp(userSignUpRequest);

    }

    @PutMapping("/update")
    public void updateUserInformation(@RequestBody UpdateInformationRequest updateInformationRequest,
                                      @AuthenticationPrincipal User user) {

        memberService.updateUserInformation(updateInformationRequest, user);

    }

    @GetMapping("/{userId}")
    public List<StoreResponse> getBookmarkList(@AuthenticationPrincipal User user) {

       return memberService.getBookmarkList(user);

    }

}
