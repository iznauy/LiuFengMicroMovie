package cn.nju.edu.movie.dao;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2019-06-13.
 * Description:
 *
 * @author iznauy
 */
@Component
public class CommentSentimentDaoImpl implements CommentSentimentDao {

    private static final String HOST = "http://localhost:5005";

    private HttpClient client = HttpClients.createDefault();

    private static Gson gson = new Gson();

    private String post(String url, List<String> comments) {
        HttpPost post = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<>();
        try {
            params.add(new BasicNameValuePair("comments", gson.toJson(comments)));
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
    public List<String> getCommentTags(List<String> comments) {
        String content = post(HOST, comments);
        String[] arr = gson.fromJson(content, String[].class);
        return Arrays.asList(arr);
    }

}
