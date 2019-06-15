package cn.nju.edu.movie.dao;

import cn.nju.edu.movie.entity.FilmBasicInfo;
import cn.nju.edu.movie.entity.RelatedFilm;

import java.util.List;

/**
 * Created on 2019-06-15.
 * Description:
 *
 * @author iznauy
 */
public interface RecommendDao {

    List<RelatedFilm> getRelatedFilms(FilmBasicInfo basicInfo);

}
