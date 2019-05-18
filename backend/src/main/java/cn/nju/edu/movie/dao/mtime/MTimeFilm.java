package cn.nju.edu.movie.dao.mtime;

import cn.nju.edu.movie.entity.Film;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2019-05-18
 * Description:
 */
@Data
public class MTimeFilm {

    @SerializedName("global_id")
    long OverAllId;

    @SerializedName("name")
    String Name;

    @SerializedName("en_name")
    String EnName;

    @SerializedName("categories")
    List<String> Categories;

    @SerializedName("length")
    String Len;

    @SerializedName("release_date")
    String ReleaseTime;

    @SerializedName("score")
    Double Score;

    @SerializedName("score_count")
    Integer ScoreCount;

    @SerializedName("has_scored")
    boolean HasScored;

    @SerializedName("ticket_office")
    String TicketOffice;

    @SerializedName("img")
    String PicUrl;

    @SerializedName("description")
    String Description;

    @SerializedName("directors")
    String Directors;

    Film toFilm() {
        Film film = new Film();

        film.setId(OverAllId);
        film.setName(Name);
        film.setEnName(EnName);
        film.setLength(Len);
        film.setReleaseTime(ReleaseTime);
        film.setScoreCount(ScoreCount + "");
        film.setHasReleased(HasScored);
        film.setTicketOffice(TicketOffice);
        film.setPicUrl(PicUrl);
        film.setDescription(Description);

//        String[] categories = Categories.split(",");
//        List<String> categoryList = new ArrayList<>(categories.length);
//        for (String category: categories) {
//            if (category.length() > 0)
//                categoryList.add(category);
//        }
        film.setCategories(Categories);

        List<String> directors = new ArrayList<>();
        directors.add(Directors);
        film.setDirectors(directors);

        // TODO : 设置分数
        film.setScore(Score);

        return film;
    }
}
