package cn.nju.edu.movie.dao.maoyan;

import cn.nju.edu.movie.entity.FilmIntro;
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
class MaoYanFilmIntro {

    long OverAllId;

    String Name;

    String Categories;

    String PicUrl;

    String Len;

    String Score;

    FilmIntro toFilmIntro() {
        FilmIntro intro = new FilmIntro();
        intro.setId(OverAllId);
        intro.setLength(Len);
        intro.setName(Name);
        intro.setPicUrl(PicUrl);

        // 设置类别
        String[] categories = Categories.split(",");
        List<String> categoryList = new ArrayList<>(categories.length);
        for (String category: categories) {
            if (category.length() > 0)
                categoryList.add(category);
        }
        intro.setCategories(categoryList);

        // TODO: 设置分数
        intro.setScore(10.0);

        return intro;
    }
}
