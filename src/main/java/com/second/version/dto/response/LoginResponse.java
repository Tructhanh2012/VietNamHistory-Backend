package com.second.version.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    public MemberLoginResponse user;
    public String jwtToken;
    public String refreshToken;
}
