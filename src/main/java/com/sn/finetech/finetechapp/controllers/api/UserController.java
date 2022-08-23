package com.sn.finetech.finetechapp.controllers.api;

import com.sn.finetech.finetechapp.config.jwt.JwtUtil;
import com.sn.finetech.finetechapp.model.User;
import com.sn.finetech.finetechapp.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registration(@Valid @RequestBody User userRequest) throws ValidationException {
        User user = userService.registration(userRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody User userRequest) {
        try{
            User user = userService.login(userRequest);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtUtil.generateAccesToken(user))
                    .header("refresh_token", jwtUtil.refreshAccesToken(user))
                    .body(user);
        } catch (BadCredentialsException e){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
