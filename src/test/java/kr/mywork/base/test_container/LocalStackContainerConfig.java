package kr.mywork.base.test_container;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
@Testcontainers
public class LocalStackContainerConfig {

	@Value("${post.upload.bucket-name}")
	private static String bucketName;

	@Container
	static final LocalStackContainer LOCALSTACK =
			new LocalStackContainer(DockerImageName.parse("localstack/localstack:0.14.3"))
					.withServices(LocalStackContainer.Service.S3);

	static {
		LOCALSTACK.start();
		createS3Bucket(bucketName);
	}

	private static void createS3Bucket(String bucketName) {
		try {
			LOCALSTACK.execInContainer(
					"awslocal",
					"s3api",
					"create-bucket",
					"--bucket",
					bucketName
			);
		} catch (Exception e) {
			if (e.getMessage() == null || !e.getMessage().contains("BucketAlreadyOwnedByYou")) {
				System.err.println("S3 버킷 생성 중 오류: " + e.getMessage());
			}
		}
	}

	@DynamicPropertySource
	static void registerProperties(DynamicPropertyRegistry registry) {
		registry.add("post.upload.endpoint", () -> LOCALSTACK.getEndpoint().toString());
	}
}
