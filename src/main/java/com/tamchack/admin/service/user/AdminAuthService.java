package com.tamchack.admin.service.user;

import com.tamchack.admin.payload.request.AdminSignInRequest;
import com.tamchack.admin.payload.response.AdminTokenResponse;

public interface AdminAuthService {

    AdminTokenResponse adminSignIn(AdminSignInRequest adminSignInRequest);

}
