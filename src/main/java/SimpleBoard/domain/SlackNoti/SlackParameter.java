package SimpleBoard.domain.SlackNoti;

import java.util.ArrayList;
import java.util.List;

public class SlackParameter {
    String channel;
    String username;
    String text;
    List<SlackAttachment> attachments;

    public SlackParameter(){
        attachments = new ArrayList<SlackAttachment>();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<SlackAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<SlackAttachment> attachments) {
        this.attachments = attachments;
    }
}
