package com.pichincha.evaluacion.controller;

import com.pichincha.evaluacion.service.dto.LoginRequest;
import com.pichincha.evaluacion.service.dto.LoginResponse;
import com.pichincha.evaluacion.service.dto.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/evaluacion")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        var password = new StringBuilder(loginRequest.getUser()).reverse().toString();
        if (loginRequest.getPassword().equals(password)) {
            var jwtToken = userService.getJwtToken(loginRequest.getUser());
            return new ResponseEntity<>(LoginResponse.builder().jwt(jwtToken).build(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

}
