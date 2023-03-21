package app.door2door.jobtracker.service;

import app.door2door.jobtracker.dto.LoginRequest;
import app.door2door.jobtracker.dto.AuthenticationResponse;
import app.door2door.jobtracker.dto.UserCreateRequest;
import app.door2door.jobtracker.entity.Role;
import app.door2door.jobtracker.entity.User;
import app.door2door.jobtracker.exceptions.EmailNotFoundException;
import app.door2door.jobtracker.exceptions.EmailTakenException;
import app.door2door.jobtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

//    public AuthenticationResponse signup(UserCreateRequest request) {
//        if (userRepository.existsByEmail(request.getEmail())) {
//            throw new EmailTakenException("Email already taken");
//        }
//        User user = User.builder()
//                .name(request.getName())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.ADMIN)
//                .build();
//        userRepository.save(user);
//        String token = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(token)
//                .build();
//    }

    public AuthenticationResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EmailNotFoundException("Email not found"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

}