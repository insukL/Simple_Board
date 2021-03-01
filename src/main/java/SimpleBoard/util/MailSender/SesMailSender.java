package SimpleBoard.util.MailSender;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SesMailSender {
    @Autowired
    private AmazonSimpleEmailServiceAsync amazonSimpleEmailServiceAsync;

    public void sendMail(String from, String to, String subject, String htmlBody){
        SendEmailRequest request = new SendEmailRequest()
                .withDestination(
                        new Destination().withToAddresses(to)
                )
                .withMessage(new Message()
                    .withBody(new Body()
                        .withHtml(new Content()
                            .withCharset("UTF-8").withData(htmlBody))
                    )
                    .withSubject(new Content()
                        .withCharset("UTF-8").withData(subject))
                )
                .withSource(from);
        if (amazonSimpleEmailServiceAsync == null) System.out.println("is null");
        amazonSimpleEmailServiceAsync.sendEmailAsync(request);
        System.out.println("Email sent!");
    }
}
