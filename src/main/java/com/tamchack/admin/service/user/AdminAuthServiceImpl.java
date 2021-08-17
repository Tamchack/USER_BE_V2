package com.tamchack.admin.service.user;

import com.tamchack.admin.payload.request.AdminSignInRequest;
import com.tamchack.admin.payload.response.AdminTokenResponse;
import com.tamchack.tamchack.exception.UserNotFoundException;
import com.tamchack.tamchack.repository.member.AdminRepository;
import com.tamchack.tamchack.security.token.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminAuthServiceImpl implements AdminAuthService {

    private final AdminRepository adminRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Value("${auth.jwt.exp.refresh}")
    private Long refreshExp;

    @Value("${auth.jwt.prefix}")
    private String tokenType;


    @Override
    public AdminTokenResponse adminSignIn(AdminSignInRequest adminSignInRequest) {

        String id = adminSignInRequest.getId();
        String password = adminSignInRequest.getPassword();

        return adminRepository.findById(id)
                .filter(admin -> passwordEncoder.matches(password, admin.getPassword()))
                .map(admin -> createTokenResponse(admin.getId(), "admin"))
                .orElseThrow(UserNotFoundException::new);
    }

    private AdminTokenResponse createTokenResponse(String id, String userType) {

        String accessToken = jwtProvider.getAccessToken(id, userType);
        String refreshToken = jwtProvider.getRefreshToken(id, userType);

        return new AdminTokenResponse(accessToken, refreshToken);
    }
}
