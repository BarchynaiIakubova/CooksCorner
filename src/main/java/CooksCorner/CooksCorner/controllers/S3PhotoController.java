package CooksCorner.CooksCorner.controllers;

import CooksCorner.CooksCorner.dto.responses.PhotoResponse;
import CooksCorner.CooksCorner.services.S3Service;
import CooksCorner.CooksCorner.validations.UserValidate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/photo")
@PreAuthorize("hasAuthority('USER')")
@Tag(name = "Photo API", description = "Photo endpoints to upload photos to AWS S3")
public class S3PhotoController {

    private final S3Service service;

    @Operation(summary = "upload photo to S3", description = "This method uploads a photo to s3")
    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PhotoResponse upload(@RequestPart(name = "file", required = false)MultipartFile file) {

        return service.upload(file);
    }

    @Operation(summary = "delete photo to S3", description = "This method deletes a photo to s3")
    @DeleteMapping
    public void delete(@RequestParam String fileLink) {

        service.deletePath(fileLink);
    }



}
