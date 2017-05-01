package fedbycat.util;

import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

/**
 * Created by anjin on 4/30/17.
 */
public class ApiHttp {
    public static String doPost(String url, JsonObject jsonObject) {
        HttpClient httpClient = HttpClientBuilder.create().build();

        try {

            HttpPost request = new HttpPost(url);
            StringEntity params =new StringEntity(jsonObject.toString());
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            return EntityUtils.toString(response.getEntity(), "UTF-8");

        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
