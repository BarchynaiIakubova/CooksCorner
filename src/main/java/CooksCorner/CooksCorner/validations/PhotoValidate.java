package CooksCorner.CooksCorner.validations;

import CooksCorner.CooksCorner.exceptions.NotFoundException;
import CooksCorner.CooksCorner.models.Photo;
import CooksCorner.CooksCorner.repositories.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhotoValidate {

    private final PhotoRepository photoRepository;

    @Value("${cloud.aws.bucket.path}")
    private String path;

    public Photo findById(Long imageId) {

        return photoRepository.findById(imageId).orElseThrow(
                () -> new NotFoundException("Image not found")
        );
    }

    public Photo findPhotoByLink(String link) {

        return photoRepository.findByLink(link.substring(path.length())).orElseThrow(
                () -> new NotFoundException("Photo not found")
        );
    }

}

