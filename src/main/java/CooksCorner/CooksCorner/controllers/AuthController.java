package CooksCorner.CooksCorner.controllers;

import CooksCorner.CooksCorner.dto.requests.AuthenticationRequest;
import CooksCorner.CooksCorner.dto.requests.RegisterRequest;
import CooksCorner.CooksCorner.dto.responses.AuthenticationResponse;
import CooksCorner.CooksCorner.dto.responses.Response;
import CooksCorner.CooksCorner.models.User;
import CooksCorner.CooksCorner.services.AuthenticationService;
import CooksCorner.CooksCorner.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth API", description = "Authentication and authorization endpoints")

public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary = "Register an user", description = "This method registers new users")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {

        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @Operation(summary = "Authenticate an user", description = "This method authenticates the user")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {

        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
