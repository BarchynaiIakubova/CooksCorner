package CooksCorner.CooksCorner.controllers;


import CooksCorner.CooksCorner.dto.requests.UserRequest;
import CooksCorner.CooksCorner.dto.responses.Response;
import CooksCorner.CooksCorner.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@PreAuthorize("hasAuthority('USER')")
@Tag(name = "User API", description = "User endpoints")
public class UserController {

    private final UserService userService;

    @PutMapping
    @Operation(summary = "managing profile", description = "This method fills profile")
    Response fillProfile(@RequestBody UserRequest userRequest) {

        System.out.println("userRequest = " + userRequest);
        return userService.fillProfile(userRequest);
    }


}
