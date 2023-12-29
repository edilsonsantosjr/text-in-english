package com.api.textenglish.textenglish.controllers;

import com.api.textenglish.textenglish.dtos.AuthenticationDto;
import com.api.textenglish.textenglish.dtos.LoginResponseDto;
import com.api.textenglish.textenglish.dtos.RegisterDto;
import com.api.textenglish.textenglish.models.User;
import com.api.textenglish.textenglish.repositories.UserRepository;
import com.api.textenglish.textenglish.security.TokenService;
import jakarta.validation.Valid;
import org.aspectj.weaver.patterns.ITokenSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto authenticationDto){
        var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.login(), authenticationDto.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto registerDto){
        if(this.repository.findByLogin(registerDto.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        User newUser = new User(registerDto.login(), encryptedPassword, registerDto.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
