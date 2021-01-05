package SimpleBoard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class User {
    protected long id;
    protected String account;
    protected String password;
    protected String nick_name;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss", timezone = "Asia/Seoul")
    protected Timestamp created_at;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss", timezone = "Asia/Seoul")
    protected Timestamp updated_at;

}
