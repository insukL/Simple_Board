package SimpleBoard.repository;

import SimpleBoard.domain.Recommend;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendMapper {
    @Insert("insert into recommend(author_id, article_id) values(#{author_id}, #{article_id})")
    public boolean createRecommend(Recommend recommend);

    @Delete("delete from recommend where author_id = #{author_id} and article_id = #{article_id}")
    public boolean deleteRecommend(Recommend recommend);

    @Select("select count(*) from recommend where author_id = #{author_id} and article_id = #{article_id}")
    public int findRecommend(Recommend recommend);
}
