package kr.mywork.domain.company.uploader;

import java.net.URL;
import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class S3CompanyImageFileHandler implements CompanyImageFileHandler {

	@Value("${post.upload.bucket-name}")
	private String bucketName;

	@Value("${company.upload.duration}")
	private Duration presignedDuration;

	private final S3Template s3Template;

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
}
