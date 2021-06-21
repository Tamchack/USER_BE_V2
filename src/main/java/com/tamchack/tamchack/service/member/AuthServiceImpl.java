package com.tamchack.tamchack.service.member;

import com.tamchack.tamchack.security.token.JWTProvider;
import com.tamchack.tamchack.domain.member.Storeuser;
import com.tamchack.tamchack.domain.member.User;
import com.tamchack.tamchack.exception.UserNotFoundException;
import com.tamchack.tamchack.dto.request.member.SignInRequest;
import com.tamchack.tamchack.dto.response.member.TokenResponse;
import com.tamchack.tamchack.repository.member.StoreuserRepository;
import com.tamchack.tamchack.repository.member.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final StoreuserRepository storeuserRepository;
    private final JWTProvider jwtProvider;

    @Override //유저 로그인
    public TokenResponse userSignIn(SignInRequest signInRequest) {

        String id = signInRequest.getId();
        String password = signInRequest.getPassword();

        return userRepository.findByIdAndPassword(id, password)
                .map(User::getId)
                .map(this::createTokenResponse)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override //서점 유저 로그인
    public TokenResponse storeuserSignIn(SignInRequest signInRequest) {

        String id = signInRequest.getId();
        String password = signInRequest.getPassword();

        return storeuserRepository.findByIdAndPassword(id, password)
                .map(Storeuser::getId)
                .map(this::createTokenResponse)
                .orElseThrow(UserNotFoundException::new);
    }

    private TokenResponse createTokenResponse(String id) {

        String accessToken = jwtProvider.getAccessToken(id);

        String refreshToken = jwtProvider.getRefreshToken(id);

        return new TokenResponse(accessToken, refreshToken);
    }

}
