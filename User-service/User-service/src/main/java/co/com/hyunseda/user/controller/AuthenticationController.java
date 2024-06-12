package co.com.hyunseda.user.controller;

import co.com.hyunseda.user.access.UserRepository;

import co.com.hyunseda.user.controller.dto.AuthCreateUserRequest;
import co.com.hyunseda.user.controller.dto.AuthLoginRequest;
import co.com.hyunseda.user.controller.dto.AuthResponse;
import co.com.hyunseda.user.domain.service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
//@PreAuthorize("denyAll()")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("create-user")
    public ResponseEntity<AuthResponse> createUser(@RequestBody@Valid AuthCreateUserRequest authCreateUser)
    {
        return  new ResponseEntity<>(this.userDetailsService.createUser(authCreateUser), HttpStatus.CREATED);
    }

    @PostMapping("sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody@Valid AuthCreateUserRequest authCreateUser)
    {
        return  new ResponseEntity<>(this.userDetailsService.createUser(authCreateUser), HttpStatus.CREATED);
    }
    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody@Valid AuthLoginRequest userRequest)
    {
        return  new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")

}