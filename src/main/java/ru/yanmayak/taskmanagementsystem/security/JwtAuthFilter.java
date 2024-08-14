package ru.yanmayak.taskmanagementsystem.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.yanmayak.taskmanagementsystem.service.JwtService;
import ru.yanmayak.taskmanagementsystem.service.UserService;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    public static final String BEARER_PREFIX = "Bearer";
    public static final String HEADER_NAME = "Authorization";
    private final JwtService jwtService;
    private final UserService userService;




    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWithIgnoreCase(authHeader, BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
        }

        var jwt = authHeader.substring(BEARER_PREFIX.length());
        var username = jwtService.extractUsername(jwt);

        if(!StringUtils.isEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService
                    .userDetailsService()
                    .loadUserByUsername(username);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(auth);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);
    }
}
