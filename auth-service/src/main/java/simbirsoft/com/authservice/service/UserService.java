package simbirsoft.com.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import simbirsoft.com.authservice.domain.Role;
import simbirsoft.com.authservice.domain.User;
import simbirsoft.com.authservice.dto.UserSignInRequest;
import simbirsoft.com.authservice.dto.UserSignInResponse;
import simbirsoft.com.authservice.dto.UserSignUpRequest;
import simbirsoft.com.authservice.exception.UserException;
import simbirsoft.com.authservice.repository.RoleRepository;
import simbirsoft.com.authservice.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public User signUp(UserSignUpRequest signUpRequest) {
        Role role = roleRepository.findByName(signUpRequest.getRole())
                .orElseThrow(() -> new UserException("Role: " + signUpRequest.getRole() + " not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .hashPassword(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles(roles)
                .build();

        user = userRepository.save(user);

        return user;
    }

    public UserSignInResponse signIn(UserSignInRequest signInRequest) {
        User user = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new UserException("The user with email: " + signInRequest.getEmail() + " not found"));

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getHashPassword())) {
            throw new UserException("The passwords incorrect");
        }

        String token = jwtService.generateToken(user);
        UserSignInResponse signInResponse = new UserSignInResponse(token);

        return signInResponse;
    }
}
