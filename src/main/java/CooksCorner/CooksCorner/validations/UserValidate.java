package CooksCorner.CooksCorner.validations;

import CooksCorner.CooksCorner.exceptions.NotFoundException;
import CooksCorner.CooksCorner.models.User;
import CooksCorner.CooksCorner.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidate {

    private final UserRepository userRepository;

//    public void existsUsername(String username) {
//
//        if (userRepository.existsByUsername(username)) {
//
//            throw new AlreadyExistsException("");
//        }
//    }

    public User findById(Long userId) {

        return userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("Мугалим табылган жок")
        );
    }

    public User getByAuthentication() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (User) userRepository.findIdByEmail(
                authentication.getName()).orElseThrow(() -> new NotFoundException("the user is not found"));
    }
}
