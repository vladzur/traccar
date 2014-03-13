package org.traccar.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.traccar.helper.Log;

/**
 *
 * @author vladzur
 */


public class HTTPRequest {

    public static void sendPost(String imei, Double lat, Double lon) throws Exception {
        String url = "http://www.control-flota.com/track";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("token", imei));
        urlParameters.add(new BasicNameValuePair("lat", lat.toString()));
        urlParameters.add(new BasicNameValuePair("long", lon.toString()));
        urlParameters.add(new BasicNameValuePair("speed", "0"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        Log.info("\nSending 'POST' request to URL : " + url);
        Log.info("Post parameters : " + post.getEntity());
        Log.info("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        Log.info(result.toString());

    }
}
