package cn.nju.edu.movie.entity;

import lombok.Data;

import java.util.List;

/**
 * Created on 16/05/2019.
 * Description:
 *
 * @author iznauy
 */
@Data
public class FilmIntro {

    private long id;

    private String name;

    private List<String> categories;

    private String picUrl;

    private String length;

    private Double score;


}
