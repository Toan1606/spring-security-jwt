package com.eric.security.controller;

import com.eric.security.dto.LoginDTO;
import com.eric.security.service.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping
public class HomeController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @GetMapping
    public String home() {
        return "<h1>Hello Spring Security</h1>";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        String repeatPassword = loginDTO.getRepeatPassword();

        if (password != repeatPassword) {
            return ResponseEntity.badRequest().body("Repeat password is not the same password");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        authenticationManager.authenticate(authentication);

        String token = jwtUtil.generate(username);

        return ResponseEntity.ok().body(token);
    }
}
