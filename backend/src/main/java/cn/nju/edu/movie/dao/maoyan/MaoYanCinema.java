package cn.nju.edu.movie.dao.maoyan;

import cn.nju.edu.movie.common.Source;
import cn.nju.edu.movie.entity.Cinema;
import lombok.Data;

/**
 * Created on 16/05/2019.
 * Description:
 *
 * @author iznauy
 */
@Data
class MaoYanCinema {

    String Name;

    String Position;

    String Price;

    String Url;

    public Cinema toCinema() {
        Cinema cinema = new Cinema();
        cinema.setName(Name);
        cinema.setPosition(Position);
        cinema.setPrice(Price);
        cinema.setUrl(Url);
        cinema.setSource(Source.MAO_YAN);
        return cinema;
    }

}
