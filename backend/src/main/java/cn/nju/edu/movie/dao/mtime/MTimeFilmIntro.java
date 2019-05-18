package cn.nju.edu.movie.dao.mtime;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2019-05-18
 * Description:
 */
@Data
public class MTimeFilmIntro {

    @SerializedName("global_id")
    Long id;

    String name;

    @SerializedName("en_name")
    String enName;

    List<String> categories;

    String length;

    @SerializedName("release_date")
    String releaseDate;

    Double score;

    @SerializedName("score_count")
    Integer scoreCount;

    @SerializedName("has_scored")
    Boolean hasScored;
}
