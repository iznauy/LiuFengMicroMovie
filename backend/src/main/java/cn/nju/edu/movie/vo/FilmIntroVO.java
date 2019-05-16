package cn.nju.edu.movie.vo;

import cn.nju.edu.movie.common.Source;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created on 13/05/2019.
 * Description:
 *
 * @author iznauy
 */
@Data
public class FilmIntroVO {

    private long id;

    private String name;

    private List<String> categories;

    private String picUrl;

    private String length;

    private Map<Source, Double> scores;

}
