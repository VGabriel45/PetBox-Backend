package springApplication.awsS3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class S3StorageService {
    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public void uploadFile(final MultipartFile multipartFile, String id) {
        try {
            final File file = convertMultiPartFileToFile(multipartFile);
            uploadFileToS3Bucket(file, id);
            final boolean deleted = file.delete();
        } catch (final AmazonServiceException ex) {
            System.out.println("");
        }
    }

    public byte[] downloadFile(final String key) {
        try {
            final GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
            S3Object object = s3Client.getObject(getObjectRequest);
            return IOUtils.toByteArray(object.getObjectContent());
        } catch (AmazonServiceException | IOException e) {
//            LOGGER.error("Failed to download object with key {}: {}", key, e.getMessage());
            return new byte[0];
        }
    }


    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }

    private void uploadFileToS3Bucket(final File file, final String id) {
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, id, file);
        s3Client.putObject(putObjectRequest);
    }
}
