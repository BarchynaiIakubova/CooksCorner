package CooksCorner.CooksCorner.services;

import CooksCorner.CooksCorner.dto.requests.UserRequest;
import CooksCorner.CooksCorner.dto.responses.Response;
import CooksCorner.CooksCorner.models.Photo;
import CooksCorner.CooksCorner.models.User;
import CooksCorner.CooksCorner.repositories.PhotoRepository;
import CooksCorner.CooksCorner.repositories.UserRepository;
import CooksCorner.CooksCorner.validations.PhotoValidate;
import CooksCorner.CooksCorner.validations.UserValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserValidate userValidate;

    private final PhotoRepository photoRepository;

    private final PhotoValidate photoValidate;

    private final S3Service s3Service;

    @Value("${cloud.aws.bucket.path}")
    private String path;

    @Transactional
    public Response fillProfile(UserRequest userRequest) {

        System.out.println(userRequest.photoLink());

        User user = userValidate.getByAuthentication();

        Photo existingPhoto = photoRepository.findPhotoByUserId(user.getId());

        Photo newPhoto = photoValidate.findPhotoByLink(userRequest.photoLink());

        if (existingPhoto != null) {

            s3Service.deletePath(path + existingPhoto.getLink());
            newPhoto.setUser(user);

        } newPhoto.setUser(user);

        user.setFullName(userRequest.fullName());
        user.setBio(userRequest.bio());
        userRepository.save(user);

        return new Response("The profile is updated");
    }
}
