package io.medicorum.comprehension.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.comprehendmedical.AWSComprehendMedical;
import com.amazonaws.services.comprehendmedical.AWSComprehendMedicalClient;
import org.springframework.stereotype.Component;

@Component
public class AwsComprehendMedicalClient {
    private static final String AWS_ACCESS_KEY = "AKIAUKOZKSTLWTSYJJRG";
    private static final String AWS_SECRET_KEY = "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";
    private static final String AWS_REGION = "us-east-2";
    private static final AWSCredentials AWS_CREDS = new BasicAWSCredentials(AWS_ACCESS_KEY,AWS_SECRET_KEY);
    private static final AWSCredentialsProvider AWS_CREDS_PROVIDER = new AWSStaticCredentialsProvider(AWS_CREDS);
    public static final AWSComprehendMedical getClient(){
        return AWSComprehendMedicalClient.builder()
                .withCredentials(AWS_CREDS_PROVIDER)
                .withRegion(AWS_REGION)
                .build();}

}
