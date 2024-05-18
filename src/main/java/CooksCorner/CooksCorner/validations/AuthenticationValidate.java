//package CooksCorner.CooksCorner.validations;
//
//import CooksCorner.CooksCorner.models.User;
//import CooksCorner.CooksCorner.repositories.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class AuthenticationValidate {
//
//    private final UserRepository userRepository;
//
//    public void existsUsername(String username) {
//
//        if (userRepository.existsByUsername(username)) {
//
//            throw new AlreadyExistsException("Мугалимдин мындай " + username + " электрондук почтасы бош эмес. Сураныч башка электрондук почта жазыныз");
//        }
//    }
//
//    public User findById(Long userId) {
//
//        return userRepository.findById(userId).orElseThrow(
//                () -> new UsernameNotFoundException("Мугалим табылган жок")
//        );
//    }
//}
