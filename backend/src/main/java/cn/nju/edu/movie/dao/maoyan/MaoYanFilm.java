package cn.nju.edu.movie.dao.maoyan;

import cn.nju.edu.movie.entity.Film;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 16/05/2019.
 * Description:
 *
 * @author iznauy
 */
@Data
class MaoYanFilm {

    long OverAllId;

    String Name;

    String EnName;

    String Categories;

    String Len;

    String ReleaseTime;

    String Score;

    String ScoreCount;

    boolean HasScored;

    String TicketOffice;

    String PicUrl;

    String Description;

    String Directors;

    Film toFilm() {
        Film film = new Film();

        film.setId(OverAllId);
        film.setName(Name);
        film.setEnName(EnName);
        film.setLength(Len);
        film.setReleaseTime(ReleaseTime);
        film.setScoreCount(ScoreCount);
        film.setHasReleased(HasScored);
        film.setTicketOffice(TicketOffice);
        film.setPicUrl(PicUrl);
        film.setDescription(Description);

        String[] categories = Categories.split(",");
        List<String> categoryList = new ArrayList<>(categories.length);
        for (String category: categories) {
            if (category.length() > 0)
                categoryList.add(category);
        }
        film.setCategories(categoryList);

        List<String> directors = new ArrayList<>();
        directors.add(Directors);
        film.setDirectors(directors);

        // TODO : 设置分数
        film.setScore(10.0);

        return film;
    }

}
