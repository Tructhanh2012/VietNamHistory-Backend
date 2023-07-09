package com.second.version.auth;

import com.second.version.config.JwtService;
import com.second.version.dto.request.RegisterRequest;
import com.second.version.dto.response.LoginResponse;
import com.second.version.dto.response.MemberLoginResponse;
import com.second.version.token.Token;
import com.second.version.token.TokenRepository;
import com.second.version.token.TokenType;
import com.second.version.user.Role;
import com.second.version.user.UserEntity;
import com.second.version.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        String email = request.getEmail();
        Optional<UserEntity> userExist = repository.findByEmail(email);
        String jwtToken = "";
        String refreshToken = "";
        if (userExist.isEmpty()) {
            var user = UserEntity.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.MEMBER)
                    .build();

            var savedUser = repository.save(user);
            jwtToken = jwtService.generateToken(user);
            refreshToken = jwtService.generateRefreshToken(user);
            saveUserToken(savedUser, jwtToken);
        }

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
    public AuthenticationResponse createEditor(RegisterRequest request) {
        String email = request.getEmail();
        Optional<UserEntity> userExist = repository.findByEmail(email);
        String jwtToken = "";
        String refreshToken = "";
        if (userExist.isEmpty()) {
            var user = UserEntity.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.EDITOR)
                    .build();

            var savedUser = repository.save(user);
            jwtToken = jwtService.generateToken(user);
            refreshToken = jwtService.generateRefreshToken(user);
            saveUserToken(savedUser, jwtToken);
        }

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
    public LoginResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = tokenRepository.findAllValidTokensByUser(user.getId()).getToken();
        var refreshToken = jwtService.generateRefreshToken(user);
        AuthenticationResponse jwt = AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
        LoginResponse loginResponse = new LoginResponse();
        MemberLoginResponse response = new MemberLoginResponse(user.getId(), user.getName(), user.getRole().toString());
        loginResponse.setUser(response);
        loginResponse.setJwtToken(jwt.getAccessToken());
        loginResponse.setRefreshToken(jwt.getRefreshToken());
        return loginResponse;
    }


    public void saveUserToken(UserEntity user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

}
