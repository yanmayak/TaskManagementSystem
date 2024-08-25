package ru.yanmayak.taskmanagementsystem.service.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yanmayak.taskmanagementsystem.entity.User;
import ru.yanmayak.taskmanagementsystem.dto.auth.JwtAuthResponse;
import ru.yanmayak.taskmanagementsystem.dto.auth.SignInRequest;
import ru.yanmayak.taskmanagementsystem.dto.auth.SignUpRequest;
import ru.yanmayak.taskmanagementsystem.entity.Role;
import ru.yanmayak.taskmanagementsystem.exception.ResourceNotFoundException;
import ru.yanmayak.taskmanagementsystem.repository.UserRepository;
import ru.yanmayak.taskmanagementsystem.service.user.UserService;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public JwtAuthResponse signUp(SignUpRequest request) {
        return new JwtAuthResponse(
                jwtService.generateToken(
                        userService.create(
                                User.builder()
                                        .username(request.getUsername())
                                        .email(request.getEmail())
                                        .password(request.getPassword())
                                        .role(Role.ROLE_USER)
                                        .build()
                        )
                )
        );
    }

    @Transactional
    public JwtAuthResponse signIn(SignInRequest signInRequest) {
        return userRepository.findByEmail(signInRequest.getEmail())
                .map(user ->
                        authorize(
                                user.getUsername(),
                                signInRequest.getPassword()
                        )
                )
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private JwtAuthResponse authorize(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return new JwtAuthResponse(
                jwtService.generateToken(
                        userService.userDetailsService()
                                .loadUserByUsername(username)
                )
        );
    }
}
