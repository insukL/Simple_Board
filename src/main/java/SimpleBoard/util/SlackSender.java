package SimpleBoard.util;

import SimpleBoard.domain.SlackNoti.SlackAttachment;
import SimpleBoard.domain.SlackNoti.SlackParameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

public class SlackSender {
    @Value("${slack.test.url}")
    private String testUrl;

    private RestTemplate restTemplate = new RestTemplate();

    public SlackSender(){
        restTemplate.getMessageConverters().add(0,
                new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }

    public void noticeSignUp(String account){
        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setText(String.format("%s님이 회원가입했습니다.", account));
        slackAttachment.setColor("#8600CE");

        SlackParameter slackParameter = new SlackParameter();
        slackParameter.setText("회원 관련 알람");
        slackParameter.getAttachments().add(slackAttachment);

        restTemplate.postForObject(testUrl, slackParameter, String.class);
    }

    public void noticeWithdrawal(String account){
        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setText(String.format("%s님이 탈퇴하셨습니다.", account));
        slackAttachment.setColor("#a69536");

        SlackParameter slackParameter = new SlackParameter();
        slackParameter.setText("회원 관련 알람");
        slackParameter.getAttachments().add(slackAttachment);

        restTemplate.postForObject(testUrl, slackParameter, String.class);
    }

    public void noticeError(String data){
        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setText(data);
        slackAttachment.setColor("danger");

        SlackParameter slackParameter = new SlackParameter();
        slackParameter.setText("Error 알람");
        slackParameter.getAttachments().add(slackAttachment);

        restTemplate.postForObject(testUrl, slackParameter, String.class);
    }
}
