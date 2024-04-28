package com.example.springbootwithmongodb.jwt;


import com.example.springbootwithmongodb.model.LoginUserDto;
import com.example.springbootwithmongodb.model.RegisterUserDto;
import com.example.springbootwithmongodb.model.User;
import com.example.springbootwithmongodb.repository.UserRepository;
import com.example.springbootwithmongodb.service.SequenceGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public User signup(RegisterUserDto input) {
        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setId(sequenceGeneratorService.getSequenceNumber(User.SEQUENCE_NAME));
        return  userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                       input.getPassword()
                )
        );
        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }
}