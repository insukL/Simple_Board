package SimpleBoard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Board {
    protected long id;
    protected String title;
    protected String content;
    protected long author_id;
    protected long views;
    protected boolean deleted;
    //nickname, recommend, comment는 게시글 읽기에서 전달
    protected String nickname;
    protected long recommend_num;
    protected long comment_num;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    protected Date created_at;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    protected Date updated_at;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() { return content; }

    public void setContent(String content) {
        this.content = content;
    }

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public long getViews() { return views; }

    public void setViews(long views) { this.views = views; }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public long getRecommend_num() { return recommend_num; }

    public void setRecommend_num(long recommend_num) { this.recommend_num = recommend_num; }

    public long getComment_num() { return comment_num; }

    public void setComment_num(long comment_num) { this.comment_num = comment_num; }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
