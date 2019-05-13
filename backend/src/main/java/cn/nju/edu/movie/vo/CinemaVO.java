package cn.nju.edu.movie.vo;

import cn.nju.edu.movie.common.Source;
import lombok.Data;

import java.util.Map;

/**
 * Created on 13/05/2019.
 * Description:
 *
 * @author iznauy
 */
@Data
public class CinemaVO {

    private String name;

    private Map<Source, CinemaDetailVO> details;

    private static class CinemaDetailVO {

        private String position;

        private String price;

        private String url;

    }

}
