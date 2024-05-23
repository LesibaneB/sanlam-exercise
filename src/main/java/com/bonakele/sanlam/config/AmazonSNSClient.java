package com.bonakele.sanlam.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class AmazonSNSClient {

    private AmazonSNS snsClient;

    @Value("${amazon.properties.accessKey}")
    private String accessKey;

    @Value("${amazon.properties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazonSnsClient() {
        this.snsClient =
                AmazonSNSClientBuilder.standard()
                        .withCredentials(getAwsCredentialProvider())
                        .withRegion(Region.getRegion(Regions.AP_NORTHEAST_1).getName())
                        .build();
    }

    private AWSCredentialsProvider getAwsCredentialProvider() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(this.accessKey, this.secretKey);
        return new AWSStaticCredentialsProvider(awsCredentials);
    }
}
