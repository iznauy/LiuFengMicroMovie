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
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

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

    private static final String HOST = "http://localhost:5005";

    private HttpClient client = HttpClients.createDefault();

    private static Gson gson = new Gson();

    private String post(String url, FilmBasicInfo basicInfo) {
        HttpPost post = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<>();
        try {
            params.add(new BasicNameValuePair("name", basicInfo.getName()));
            params.add(new BasicNameValuePair("directors", gson.toJson(basicInfo.getDirectors())));
            params.add(new BasicNameValuePair("categories", gson.toJson(basicInfo.getCategories())));
            post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            System.out.println(client);
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
}
