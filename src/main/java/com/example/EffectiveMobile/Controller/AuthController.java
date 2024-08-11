package com.example.EffectiveMobile.Controller;


import com.example.EffectiveMobile.Config.AuthenticationProviderImpl;
import com.example.EffectiveMobile.Config.JwtUtils;
import com.example.EffectiveMobile.DTO.auth.JwtResponse;
import com.example.EffectiveMobile.DTO.auth.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth Management", description = "API for authentication")
public class AuthController {

    @Autowired
    private AuthenticationProviderImpl authenticationProvider;

    @Autowired
    private JwtUtils tokenProvider;

    @PostMapping("/login")
    @Operation(summary = "Authentication", description = "Returns JWT token")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

}
