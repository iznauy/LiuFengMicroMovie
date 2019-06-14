package cn.nju.edu.movie.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 13/05/2019.
 * Description:
 *
 * @author iznauy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private String content;

    private String time;

    private String userName;

    private Double score;

    private Source source;

    private String tag;

    public Comment(String content, String time, String userName, Double score, Source source) {
        this.content = content;
        this.time = time;
        this.userName = userName;
        this.score = score;
        this.source = source;
    }
}
