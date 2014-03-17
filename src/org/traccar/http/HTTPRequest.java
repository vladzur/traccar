package org.traccar.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static void sendPost(String imei, Double lat, Double lon) {
        String url = "http://www.control-flota.com/track";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("token", imei));
        urlParameters.add(new BasicNameValuePair("lat", lat.toString()));
        urlParameters.add(new BasicNameValuePair("long", lon.toString()));
        urlParameters.add(new BasicNameValuePair("speed", "0"));
        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HTTPRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpResponse response;
        try {
            response = client.execute(post);
        } catch (IOException ex) {
            Logger.getLogger(HTTPRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
