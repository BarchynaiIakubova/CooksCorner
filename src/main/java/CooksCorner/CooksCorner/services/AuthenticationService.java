package CooksCorner.CooksCorner.services;

import CooksCorner.CooksCorner.dto.requests.AuthenticationRequest;
import CooksCorner.CooksCorner.dto.requests.RegisterRequest;
import CooksCorner.CooksCorner.dto.responses.AuthenticationResponse;
import CooksCorner.CooksCorner.enums.Role;
import CooksCorner.CooksCorner.enums.TokenType;
import CooksCorner.CooksCorner.models.Token;
import CooksCorner.CooksCorner.models.User;
import CooksCorner.CooksCorner.repositories.TokenRepository;
import CooksCorner.CooksCorner.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final TokenRepository tokenRepository;

    public AuthenticationResponse register(RegisterRequest registerRequest) {

        var user = User.builder()
                .email(registerRequest.email())
                .fullName(registerRequest.fullName())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(Role.USER)
                .build();

        User savedUser = userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.email(),
                        authenticationRequest.password()));

        var user = userRepository.findByEmail(authenticationRequest.email())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        revokeAllUserToken(user);

        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();

        tokenRepository.save(token);
    }

    private void revokeAllUserToken(User user) {

        var validUserToken = tokenRepository.findAllValidTokenByUser(user.getId());

        if (validUserToken.isEmpty())
            return;

        validUserToken.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserToken);
    }
}
