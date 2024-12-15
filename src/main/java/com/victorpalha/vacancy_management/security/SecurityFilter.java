package com.victorpalha.vacancy_management.security;

import com.victorpalha.vacancy_management.providers.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    final private JWTProvider jwtProvider;
    public SecurityFilter(JWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        SecurityContextHolder.getContext().setAuthentication(null);
        String header = request.getHeader("Authorization");

        if (header != null) {
            try {
                final Map<String, Object> jwtInformation = this.jwtProvider.validateToken(header);
                final String sub = (String) jwtInformation.get("sub");
                final String role = (String) jwtInformation.get("role");

                if(!this.roleValidation(request, role)) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }

                request.setAttribute("entity_id", sub);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sub
                        , null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+role)));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (Exception error) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean roleValidation(HttpServletRequest request, String role){
        String routerPermission = "/".concat(role.toLowerCase());
        return request.getRequestURI().startsWith(routerPermission);
    }
}