package org.traccar.http;

/**
 *
 * @author vladzur
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HTTPRequest {

    public void POST(String imei, Double lat, Double lon) {
        // Creating HTTP client
        HttpClient httpClient = new DefaultHttpClient();

        // Creating HTTP Post
        HttpPost httpPost = new HttpPost("http://www.control-flota.com/track");

        // Building post parameters, key and value pair
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(4);
        nameValuePair.add(new BasicNameValuePair("token", imei));
        nameValuePair.add(new BasicNameValuePair("lat", lat.toString()));
        nameValuePair.add(new BasicNameValuePair("long", lon.toString()));
        nameValuePair.add(new BasicNameValuePair("speed", "0"));
        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // writing error to Log
            
        }

        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);

            // writing response to log
           

        } catch (ClientProtocolException e) {
            // writing exception to log
           

        } catch (IOException e) {
            // writing exception to log
           
        }
    }

}
