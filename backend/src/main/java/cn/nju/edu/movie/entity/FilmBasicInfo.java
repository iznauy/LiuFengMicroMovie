package cn.nju.edu.movie.entity;

import lombok.Data;

import java.util.List;

/**
 * Created on 2019-06-15.
 * Description:
 *
 * @author iznauy
 */
@Data
public class FilmBasicInfo {

    private String name;

    private List<String> directors;

    private List<String> categories;

}
