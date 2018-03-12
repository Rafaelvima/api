/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.GenericData;
import config.Configuration;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Yo
 */
public class UsersDAO {
    
    JsonFactory JSON_FACTORY = new JacksonFactory();
    HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
        @Override
        public void initialize(HttpRequest request) {
            request.setParser(new JsonObjectParser(JSON_FACTORY));
            
        }
    });
    GenericUrl url = new GenericUrl("http://localhost:8083/DefinitivaApiServer/rest/users");
    ObjectMapper objectMapper = new ObjectMapper();
    
    public List<User> getAllUsers() throws IOException {
        List<User> json =null;
        try{
            HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
        requestGoogle.getHeaders().set("Apikey", "2");
        HttpResponse response = requestGoogle.execute();
        ObjectMapper mapper = new ObjectMapper();
         json = objectMapper.readValue(response.getContent(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
        }
        catch (HttpResponseException ex){
            
        }catch (IOException ex){
            
        }
        
        return json;
    }
    
    public int addUser(User a) throws IOException {
        ObjectMapper m = new ObjectMapper();
        url.set("user", m.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildPutRequest(url, new JsonHttpContent(new JacksonFactory(), a));
        requestGoogle.getHeaders().set("Apikey", "2");
        User json = requestGoogle.execute().parseAs(User.class);
        if (json != null) {
            return 1;
        } else {
            return 0;
        }
    }

    public int deleteUser(User a) throws IOException {
        ObjectMapper m = new ObjectMapper();
        url.set("user", m.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildDeleteRequest(url);
        requestGoogle.getHeaders().set("Apikey", "2");
        User json = requestGoogle.execute().parseAs(User.class);
        if (json != null) {
            return 1;
        } else {
            return 0;
        }
    }

    public int updateUser(User a) throws IOException {
        ObjectMapper m = new ObjectMapper();
        GenericData data = new GenericData();
        data.put("user", m.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
        requestGoogle.getHeaders().set("Apikey", "2");
        User json = requestGoogle.execute().parseAs(User.class);
        if (json != null) {
            return 1;
        } else {
            return 0;
        }
    }
}
