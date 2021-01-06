package SimpleBoard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class User {
    protected long id;
    protected String account;
    protected String password;
    protected String nick_name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss", timezone = "Asia/Seoul")
    protected Timestamp created_at;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss", timezone = "Asia/Seoul")
    protected Timestamp updated_at;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getAccount() { return account; }

    public void setAccount(String account) { this.account = account; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getNick_name() { return nick_name; }

    public void setNick_name(String nick_name) { this.nick_name = nick_name; }

    public Timestamp getCreated_at() { return created_at; }

    public void setCreated_at(Timestamp created_at) { this.created_at = created_at; }

    public Timestamp getUpdated_at() { return updated_at; }

    public void setUpdated_at(Timestamp updated_at) { this.updated_at = updated_at; }
}
