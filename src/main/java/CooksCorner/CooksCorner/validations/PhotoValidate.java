package CooksCorner.CooksCorner.validations;

import CooksCorner.CooksCorner.exceptions.NotFoundException;
import CooksCorner.CooksCorner.models.Photo;
import CooksCorner.CooksCorner.repositories.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhotoValidate {

    private final PhotoRepository photoRepository;

    public Photo findById(Long imageId) {

        return photoRepository.findById(imageId).orElseThrow(
                () -> new NotFoundException("Image not found")
        );
    }

    public Photo findByImageByLink(String link) {

        return photoRepository.findByLink(link).orElseThrow(
                () -> new NotFoundException("Image not found")
        );
    }
}

