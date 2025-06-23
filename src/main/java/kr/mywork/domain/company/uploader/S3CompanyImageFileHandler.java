package kr.mywork.domain.company.uploader;

import java.net.URL;
import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

@Component
@RequiredArgsConstructor
public class S3CompanyImageFileHandler implements CompanyImageFileHandler {

	@Value("${post.upload.bucket-name}")
	private String bucketName;

	@Value("${company.upload.duration}")
	private Duration presignedDuration;

	private final S3Template s3Template;

	private final S3Client s3Client;

	@Override
	public URL createUploadUrl(final UUID companyId, final String fileName) {
		final String filePath = String.format("%s/%s", companyId, fileName);
		return s3Template.createSignedPutURL(bucketName, filePath, presignedDuration);
	}

	@Override
	public URL issueDownloadUrl(final String filePath, final Duration duration) {
		final String key = filePath.replaceFirst("^/", "");
		return s3Template.createSignedGetURL(bucketName, key, duration);
	}

	@Override
	public void deleteImage(final UUID companyId) {
		final String prefix = String.format("%s/", companyId);
		ListObjectsV2Request listRequest = ListObjectsV2Request.builder()
			.bucket(bucketName)
			.prefix(prefix)
			.build();

		ListObjectsV2Response listResponse = s3Client.listObjectsV2(listRequest);
		for (S3Object s3Object : listResponse.contents()) {
			s3Client.deleteObject(builder -> builder.bucket(bucketName).key(s3Object.key()));
		}
	}
}
