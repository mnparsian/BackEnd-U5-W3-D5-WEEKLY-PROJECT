package com.example.demo.Service;



import com.example.demo.DTO.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            Object principal = authentication.getPrincipal();
            System.out.println("🔍 مقدار Principal: " + principal.getClass().getName());
            System.out.println("🔍 مقدار Principal.toString(): " + principal.toString());
            String username;

            if (principal instanceof org.springframework.security.core.userdetails.User) {
                username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
            } else {
                username = principal.toString();
            }


            System.out.println("🔥 mannnnnnnnnnnnnnnnnnnnnnnnnnnnn ");
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
            if (username == null || username.isEmpty()) {
                throw new RuntimeException("نام کاربری معتبر نیست و مقدار آن نال یا خالی است!");
            }
            String jwt = jwtUtils.generateToken(username);
            System.out.println("🔑 مقدار JWT: " + jwt);
            System.out.println("🔥 در حال احراز هویت: " + loginRequest.getUsername());
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("نام کاربری یا رمز عبور اشتباه است!");
        }
    }
}
