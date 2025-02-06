package com.janani.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {


	@Value("${aws.accessKey}")
	private String awsAccessKey;

	@Value("${aws.secretKey}")
	private String awsSecretKey;

	@Value("${aws.s3.region}")
	private String s3Region;

	@Bean
	public S3Client s3Client() {
		AwsBasicCredentials awsCreds = AwsBasicCredentials.create(awsAccessKey,
				awsSecretKey);

		return S3Client.builder().region(Region.of(s3Region))
				.credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build();

	}
}