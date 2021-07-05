package com.tamchack.tamchack.service.member;

import com.tamchack.tamchack.security.token.JwtProvider;
import com.tamchack.tamchack.exception.UserNotFoundException;
import com.tamchack.tamchack.dto.request.member.SignInRequest;
import com.tamchack.tamchack.dto.response.member.TokenResponse;
import com.tamchack.tamchack.repository.member.StoreuserRepository;
import com.tamchack.tamchack.repository.member.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final StoreuserRepository storeuserRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponse signIn(SignInRequest signInRequest) {

        String id = signInRequest.getId();
        String password = signInRequest.getPassword();

        if(userRepository.findById(id).isPresent()) {
            return userRepository.findById(id)
                    .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                    .map(user -> createTokenResponse(user.getId(), "user"))
                    .orElseThrow(UserNotFoundException::new);
        } else {
            return storeuserRepository.findByIdAndPassword(id, password)
                    .map(storeuser -> createTokenResponse(storeuser.getId(), "storeUser"))
                    .orElseThrow(UserNotFoundException::new);
        }

    }

    private TokenResponse createTokenResponse(String id, String userType) {

        String accessToken = jwtProvider.getAccessToken(id, userType);
        String refreshToken = jwtProvider.getRefreshToken(id, userType);

        return new TokenResponse(accessToken, refreshToken);
    }

}