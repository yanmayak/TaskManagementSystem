package ru.yanmayak.taskmanagementsystem.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.yanmayak.taskmanagementsystem.dto.auth.JwtAuthResponse;
import ru.yanmayak.taskmanagementsystem.dto.auth.SignInRequest;
import ru.yanmayak.taskmanagementsystem.dto.auth.SignUpRequest;
import ru.yanmayak.taskmanagementsystem.service.jwt.AuthService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    void testSignUp() {
        SignUpRequest signUpRequest = new SignUpRequest();
        JwtAuthResponse expectedResponse = new JwtAuthResponse();

        when(authService.signUp(signUpRequest)).thenReturn(expectedResponse);

        JwtAuthResponse response = authController.signUp(signUpRequest);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testSignIn() {
        SignInRequest signInRequest = new SignInRequest();
        JwtAuthResponse expectedResponse = new JwtAuthResponse();

        when(authService.signIn(signInRequest)).thenReturn(expectedResponse);

        JwtAuthResponse response = authController.signIn(signInRequest);

        assertEquals(expectedResponse, response);
    }
}
