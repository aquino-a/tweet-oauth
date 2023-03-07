/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xyz.aquinoa.tweetDelete.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.aquinoa.tweetDelete.OauthValues;

/**
 *
 * @author alex
 */
@Controller
public class TokenController {
// var tokenUrl = "https://api.twitter.com/2/oauth2/token";
//    var urlParams = new URLSearchParams();
//    urlParams.append("code", code);
//    urlParams.append("grant_type", "authorization_code");
//    urlParams.append("redirect_uri", "http://aquinoa.xyz");
//    urlParams.append("code_verifier", "challenge");
//    
//    var auth = 'Basic ' + btoa(`${clientId}:${clientSecret}`);
//
//    const response = await fetch(tokenUrl, {
//        method: 'POST', // *GET, POST, PUT, DELETE, etc.
//        mode: 'cors',
//        headers: {
//            'Content-Type': 'application/x-www-form-urlencoded',
//            'Authorization': auth
//        },
//        body: urlParams.toString()
//    });

    private static final String TOKEN_URL = "https://api.twitter.com/2/oauth2/token";

    @Value("${clientId}")
    private String clientId;

    @Value("${clientSecret}")
    private String clientSecret;

    @Autowired
    private OkHttpClient httpClient;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/clientId")
    @ResponseBody
    public String getClientId() {
        return clientId;
    }

    @GetMapping("/token")
    @ResponseBody
    public OauthValues getToken(@RequestParam(name = "code", required = true) String code) throws IOException {
        var formBody = new FormBody.Builder()
                .add("code", code)
                .add("grant_type", "authorization_code")
                .add("redirect_uri", "http://localhost:8080")
                .add("code_verifier", "challenge")
                .build();

        var credentials = Credentials.basic(clientId, clientSecret);
        var request = new Request.Builder()
                .url(TOKEN_URL)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", credentials)
                .post(formBody)
                .build();

        var response = httpClient.newCall(request).execute();
        var body = response.body().string();
        System.out.println(body);

        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }

        return objectMapper.readValue(body, OauthValues.class);
    }
}
