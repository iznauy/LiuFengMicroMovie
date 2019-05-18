package cn.nju.edu.movie.dao.mtime;

import cn.nju.edu.movie.common.Comment;
import cn.nju.edu.movie.common.Source;
import cn.nju.edu.movie.dao.FilmDao;
import cn.nju.edu.movie.entity.Cinema;
import cn.nju.edu.movie.entity.Film;
import cn.nju.edu.movie.entity.FilmIntro;
import com.google.gson.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: J.D. Liao
 * Date: 2019-05-18
 * Description:
 */
public class MTimeFilmDao implements FilmDao {

    private static final String HOST = "http://localhost:5000";

    private static final String FILM_LIST = HOST + "/listFilms";

    private static final String FILM= HOST + "/detail?id=";

    private static final String COMMENT = HOST + "/comments?id=";

    private static final String CINEMA = HOST + "/cinemas?id=";

    private HttpClient client = HttpClients.createDefault();

    private static Gson gson = new Gson();

    private static JsonParser parser = new JsonParser();

    private String get(String url) {
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpEntity entity = client.execute(httpGet).getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Comment> getCommentsByFilmId(long id) {
        JsonArray comments = parser.parse(get(COMMENT + id)).getAsJsonArray();
        List<Comment> result = new ArrayList<>();
        for (JsonElement comment : comments) {
            JsonObject curr = comment.getAsJsonObject();
            String content = curr.get("content").getAsString();
            String time = curr.get("time").getAsString();
            String username = curr.get("username").getAsString();
            double score = curr.get("socre").getAsDouble();

            Comment co = new Comment();
            co.setContent(content);
            co.setTime(time);
            co.setUserName(username);
            co.setScore(score);
            co.setSource(Source.SHI_GUANG_WANG);

            result.add(co);
        }
        return result;
    }

    @Override
    public List<Cinema> getAvailableCinemasByFilmId(long id) {
        JsonArray cinemas = parser.parse(get(CINEMA + id)).getAsJsonArray();
        List<Cinema> result = new ArrayList<>();

        for (JsonElement e : cinemas) {
            JsonObject curr = e.getAsJsonObject();
            Cinema cinema = new Cinema();
            cinema.setName(curr.get("name").getAsString());
            cinema.setPosition(curr.get("position").getAsString());
            cinema.setPrice(curr.get("price").getAsString());
            cinema.setSource(Source.SHI_GUANG_WANG);
            cinema.setUrl(curr.get("url").getAsString());
            result.add(cinema);
        }

        return result;
    }

    @Override
    public Film getFilmById(long id) {
        return gson.fromJson(get(FILM + id), MTimeFilm.class).toFilm();
    }

    @Override
    public List<FilmIntro> getAllFilm() {

        JsonArray intros = parser.parse(get(FILM_LIST)).getAsJsonArray();
        List<FilmIntro> result = new ArrayList<>();

        for (JsonElement e : intros) {
            JsonObject curr = e.getAsJsonObject();
            FilmIntro filmIntro = new FilmIntro();
            filmIntro.setCategories(parseJsonStringArray(curr.get("categories").getAsJsonArray()));
            filmIntro.setId(curr.get("global_id").getAsLong());
            filmIntro.setLength(curr.get("length").getAsString());
            filmIntro.setName(curr.get("name").getAsString());
            filmIntro.setPicUrl(curr.get("img").getAsString());
            filmIntro.setScore(curr.get("score").getAsDouble());
            result.add(filmIntro);
        }
        return result;
    }

    private List<String> parseJsonStringArray(JsonArray jsonArray) {
        List<String> result = new ArrayList<>();
        for (JsonElement e : jsonArray) {
            result.add(e.getAsString());
        }

        return result;
    }
}
