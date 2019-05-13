package cn.nju.edu.movie.vo;

import lombok.Data;

import java.util.List;

/**
 * Created on 13/05/2019.
 * Description:
 *
 * @author iznauy
 */
@Data
public class FilmIntroVO {

    private int id;

    private String name;

    private List<String> categories;

    private String picUrl;

    private String length;

}
