package cn.nju.edu.movie.dao;

import cn.nju.edu.movie.entity.FilmBasicInfo;
import cn.nju.edu.movie.entity.RelatedFilm;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2019-06-15.
 * Description:
 *
 * @author iznauy
 */
@Component
public class RecommendDaoImpl implements RecommendDao {

    private static final String HOST = "http://localhost:7777/recommend";

    private HttpClient client = HttpClients.createDefault();

    private static Gson gson = new Gson();

    private String post(String url, FilmBasicInfo basicInfo) {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-type", "application/json; charset=utf-8");
        try {

            StringEntity stringEntity = new StringEntity(gson.toJson(basicInfo), Charset.forName("UTF-8"));
            post.setEntity(stringEntity);

            HttpResponse response = client.execute(post);
            HttpEntity entity =  response.getEntity();
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RelatedFilm> getRelatedFilms(FilmBasicInfo basicInfo) {
        String content = post(HOST, basicInfo);
        if (content == null)
            return null;
        RelatedFilm[] relatedFilms = gson.fromJson(content, RelatedFilm[].class);
        return Arrays.asList(relatedFilms);

    }

    public static void main(String[] args) {
        RecommendDaoImpl recommendDao = new RecommendDaoImpl();
        FilmBasicInfo basicInfo = new FilmBasicInfo();
        basicInfo.setName("复仇者联盟3");
        List<String> categories = new ArrayList<>();
        categories.add("动作");
        categories.add("冒险");
        categories.add("奇幻");
        basicInfo.setCategories(categories);
        List<String> directors = new ArrayList<>();
        directors.add("安东尼·罗素");
        basicInfo.setDirectors(directors);
        String content = recommendDao.post(HOST, basicInfo);
        System.out.println(content);
        System.out.println("o98k");
        List<RelatedFilm> relatedFilms = recommendDao.getRelatedFilms(basicInfo);
        for (RelatedFilm film: relatedFilms) {
            System.out.println(film);
        }
    }
}
