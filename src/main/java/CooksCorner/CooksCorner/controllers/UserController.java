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

    @PostMapping("/{userId}")
    @Operation(summary = "follower and following", description = "This method allows to follow a user and to be followed")
    Integer followUser(@PathVariable Long userId) {

        return userService.followUser(userId);
    }
}
