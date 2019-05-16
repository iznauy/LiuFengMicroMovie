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

        public CinemaDetailVO(String position, String price, String url) {
            this.position = position;
            this.price = price;
            this.url = url;
        }

        public String getPosition() {
            return position;
        }

        public String getPrice() {
            return price;
        }

        public String getUrl() {
            return url;
        }
    }

}
