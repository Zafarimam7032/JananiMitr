package com.janani.config;

import io.awspring.cloud.s3.S3Template;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

	

	@Bean
	public S3Client s3Client() {
		AwsBasicCredentials awsCreds = AwsBasicCredentials.create(System.getenv("AWS_ACCESS_KEY"),
				System.getenv("AWS_SECRET_KEY"));

		return S3Client.builder().region(Region.of(System.getenv("AWS_REGION")))
				.credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build();

	}
}