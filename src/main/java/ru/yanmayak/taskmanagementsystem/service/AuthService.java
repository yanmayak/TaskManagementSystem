package ru.yanmayak.taskmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.yanmayak.taskmanagementsystem.config.entity.User;
import ru.yanmayak.taskmanagementsystem.dto.auth.JwtAuthResponse;
import ru.yanmayak.taskmanagementsystem.dto.auth.SignInRequest;
import ru.yanmayak.taskmanagementsystem.dto.auth.SignUpRequest;
import ru.yanmayak.taskmanagementsystem.model.Role;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthResponse signUp(SignUpRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthResponse(jwt);
    }

    public JwtAuthResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(),
                signInRequest.getPassword()
        ));
        var user = userService
                .userDetailsService()
                .loadUserByUsername(signInRequest.getEmail());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthResponse(jwt);
    }


}
