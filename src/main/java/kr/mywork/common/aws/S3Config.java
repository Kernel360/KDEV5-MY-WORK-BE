package kr.mywork.common.aws;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProviderChain;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Profile("default")
@Configuration
public class S3Config {

	@Value("${post.upload.endpoint}")
	private String awsEndpoint;

	@Value("${spring.cloud.aws.s3.region}")
	private String regionName;

	@Value("${spring.cloud.aws.credentials.access-key}")
	private String accessKey;

	@Value("${spring.cloud.aws.credentials.secret-key}")
	private String privateKey;

	@Bean
	public AwsCredentialsProvider awsCredentialsProvider() {
		return AwsCredentialsProviderChain.builder()
			.reuseLastProviderEnabled(true)
			.credentialsProviders(List.of(
				DefaultCredentialsProvider.create(),
				StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, privateKey))))
			.build();
	}

	@Bean
	public S3Presigner s3Presigner() {
		return S3Presigner.builder()
			.credentialsProvider(awsCredentialsProvider())
			.region(Region.of(regionName))
			.endpointOverride(URI.create(awsEndpoint))
			.serviceConfiguration(S3Configuration.builder()
				.pathStyleAccessEnabled(true)
				.build())
			.build();
	}

	@Bean
	public S3Client s3Client() {
		return S3Client.builder()
			.credentialsProvider(awsCredentialsProvider())
			.region(Region.of(regionName))
			.endpointOverride(URI.create(awsEndpoint))
			.serviceConfiguration(S3Configuration.builder()
				.pathStyleAccessEnabled(true)
				.build())
			.build();
	}

}
