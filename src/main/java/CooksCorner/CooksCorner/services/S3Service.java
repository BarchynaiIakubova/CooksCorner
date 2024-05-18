package CooksCorner.CooksCorner.services;

import CooksCorner.CooksCorner.dto.responses.PhotoResponse;
import CooksCorner.CooksCorner.exceptions.BadRequestException;
import CooksCorner.CooksCorner.models.Photo;
import CooksCorner.CooksCorner.repositories.PhotoRepository;
import CooksCorner.CooksCorner.validations.PhotoValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    private final PhotoRepository photoRepository;

    private final PhotoValidate photoValidate;

    @Value("${cloud.aws.bucket.name}")
    private String bucketName;

    @Value("${cloud.aws.bucket.path}")
    private String path;


    public PhotoResponse upload(MultipartFile file) {

        String key = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            photoRepository.save(new Photo(key));

            return new PhotoResponse(path + key);

        } catch (IOException | S3Exception e) {

            e.printStackTrace();
            throw new BadRequestException("Failed to load the photo. Please try again latter");
        }

    }

    public void deletePath(String fileLink) {

        try {
            String key = fileLink.substring(path.length());

            s3Client.deleteObject(file -> file
                    .bucket(bucketName)
                    .key(key)
                    .build());
            photoRepository.delete(photoValidate.findByImageByLink(key));

        } catch (S3Exception e) {

            throw new IllegalStateException(e.awsErrorDetails().errorMessage());

        } catch (Exception e) {

            throw new IllegalStateException(e.getMessage());
        }

    }

}
