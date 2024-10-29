package simbirsoft.com.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simbirsoft.com.authservice.domain.User;
import simbirsoft.com.authservice.dto.UserSignInRequest;
import simbirsoft.com.authservice.dto.UserSignInResponse;
import simbirsoft.com.authservice.dto.UserSignUpRequest;
import simbirsoft.com.authservice.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody UserSignUpRequest signUpRequest) {
        return ResponseEntity.ok(userService.signUp(signUpRequest));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserSignInResponse> signIn(@RequestBody UserSignInRequest signInRequest) {
        return ResponseEntity.ok(userService.signIn(signInRequest));
    }

}
