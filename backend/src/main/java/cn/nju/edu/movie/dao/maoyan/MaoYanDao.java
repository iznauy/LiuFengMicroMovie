package cn.nju.edu.movie.dao.maoyan;

import cn.nju.edu.movie.common.Comment;
import cn.nju.edu.movie.dao.FilmDao;
import cn.nju.edu.movie.entity.Cinema;
import cn.nju.edu.movie.entity.Film;
import cn.nju.edu.movie.entity.FilmIntro;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 16/05/2019.
 * Description:
 *
 * @author iznauy
 */
@Component
public class MaoYanDao implements FilmDao {

    private static final String HOST = "http://localhost:8000";

    private static final String FILM_LIST = HOST + "/list";

    private static final String FILM= HOST + "/film?id=";

    private static final String COMMENT = HOST + "/comment?id=";

    private static final String CINEMA = HOST + "/cinema?id=";

    private HttpClient client = HttpClients.createDefault();

    private static Gson gson = new Gson();

    private String get(String url) {
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = client.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Comment> getCommentsByFilmId(long id) {
        MaoYanComment[] comments = gson.fromJson(get(COMMENT + "1"), MaoYanComment[].class);
        List<Comment> commentList = new ArrayList<>(comments.length);
        for (MaoYanComment comment: comments)
            commentList.add(comment.toComment());
        return commentList;
    }

    @Override
    public List<Cinema> getAvailableCinemasByFilmId(long id) {
        MaoYanCinema[] cinemas = gson.fromJson(get(CINEMA + id), MaoYanCinema[].class);
        List<Cinema> cinemaList = new ArrayList<>(cinemas.length);
        for (MaoYanCinema maoYanCinema: cinemas)
            cinemaList.add(maoYanCinema.toCinema());
        return cinemaList;
    }

    @Override
    public Film getFilmById(long id) {
        return null;
    }

    @Override
    public List<FilmIntro> getAllFilm() {
        return null;
    }

    public static void main(String[] args) {
        MaoYanDao maoYanDao = new MaoYanDao();
        MaoYanComment[] comments = gson.fromJson(maoYanDao.get(COMMENT + "1"), MaoYanComment[].class);
        for (MaoYanComment comment: comments) {
            System.out.println(comment);
        }
    }

}
