package cn.nju.edu.movie.common;

import lombok.Data;

/**
 * Created on 13/05/2019.
 * Description:
 *
 * @author iznauy
 */
@Data
public class Comment {

    private String content;

    private String time;

    private String userName;

    private Double score;

    private Source source;

}
