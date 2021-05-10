package top.okhrimchuk.addTimeToJira.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;


public class SenderHttp {
    public final String urlJiraWorklog = "http://localhost:9004/rest/api/2/issue/";
    public final String WORKLOG = "/worklog";
    public final String userName = "andrey";
    public final String password = "12345";

    public boolean send(Map map) throws IOException {

        JSONObject body = new JSONObject();
        body.put("comment", map.get("description"));
        body.put("timeSpentSeconds", map.get("time"));

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpPost request = new HttpPost(urlJiraWorklog + map.get("id") + WORKLOG);
            setHeader(request);
            request.setEntity(new StringEntity(body.toString(), StandardCharsets.UTF_8));
            HttpResponse httpresponse = client.execute(request);

            return httpresponse.getStatusLine().getStatusCode() == 201;
        }
    }

    private void setHeader (HttpPost request){
        String encodedString = Base64.getEncoder().encodeToString((userName + ":" + password).getBytes());
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", "Basic " + encodedString);
        request.setHeader("Accept", "*/*");
        request.setHeader("Accept-Encoding", "gzip, deflate, br");
    }
}
