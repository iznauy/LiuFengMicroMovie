package cn.nju.edu.movie.dao.maoyan;

import cn.nju.edu.movie.common.Comment;
import cn.nju.edu.movie.common.Source;
import lombok.Data;

/**
 * Created on 16/05/2019.
 * Description:
 *
 * @author iznauy
 */
@Data
class MaoYanComment {

    String Content;

    String Time;

    String UserName;

    Double Score;

    public Comment toComment() {
        Comment comment = new Comment();
        comment.setSource(Source.MAO_YAN);
        comment.setContent(Content);
        comment.setTime(Time);
        comment.setScore(2 * Score);
        comment.setUserName(UserName);
        return comment;
    }

}
