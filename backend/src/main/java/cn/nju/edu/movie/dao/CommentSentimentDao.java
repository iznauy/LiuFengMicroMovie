package cn.nju.edu.movie.dao;


import java.util.List;

/**
 * Created on 2019-06-13.
 * Description:
 *
 * @author iznauy
 */
public interface CommentSentimentDao {

    List<String> getCommentTags(List<String> comments);

}
