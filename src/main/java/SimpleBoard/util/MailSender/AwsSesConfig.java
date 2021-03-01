package SimpleBoard.util.MailSender;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsyncClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSesConfig {
    @Value("${AWS_ACCESS_KEY_ID}")
    private String AwsAccessKeyId;

    @Value("${AWS_SECRET_KEY}")
    private String AwsSecretKey;

    @Bean
    public AmazonSimpleEmailServiceAsync amazonSimpleEmailServiceAsync(){
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(AwsAccessKeyId, AwsSecretKey);

        return AmazonSimpleEmailServiceAsyncClient.asyncBuilder()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(Regions.US_WEST_2)
                .build();
    }
}
