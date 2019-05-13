package cn.nju.edu.movie.entity;


import cn.nju.edu.movie.common.Source;
import lombok.Data;

/**
 * Created on 13/05/2019.
 * Description:
 *
 * @author iznauy
 */
@Data
public class Cinema {

    private String name;

    private String position;

    private String price;

    private String url;

    private Source source;

}
