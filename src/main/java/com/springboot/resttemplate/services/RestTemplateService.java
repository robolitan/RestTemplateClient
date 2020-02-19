package com.springboot.resttemplate.services;

import com.springboot.resttemplate.models.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class RestTemplateService {
    private static final String URL_SERVER_USER = "http://localhost:8081/api/admin";
    private static final String URL_SERVER_USERS = "http://localhost:8081/api/admin/user";
    private static final String URL_SERVER_DELETE_USER = "http://localhost:8081/api/admin";
    private static final String URL_SERVER_CREATE_USER = "http://localhost:8081/api/admin/create";
    private static final String URL_SERVER_EDIT_USER = "http://localhost:8081/api/admin/edit";
    private static final String USERNAME_ON_SERVER = "admin";
    private static final String USERPASSWORD_ON_SERVER = "admin11";

    public ResponseEntity getUser(Integer id){
        HttpEntity httpEntity = new HttpEntity(getHttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> user =
                restTemplate.exchange(URL_SERVER_USER + "/" + id, HttpMethod.GET, httpEntity, User.class);
        return user;
    }

    public ResponseEntity getUsers(){
        HttpEntity httpEntity = new HttpEntity(getHttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity users =
                restTemplate.exchange(URL_SERVER_USERS, HttpMethod.GET,
                        httpEntity, new ParameterizedTypeReference<List<User>>() {
                        });
        return users;
    }

    public void deleteUser(Integer id){
        HttpEntity httpEntity = new HttpEntity(getHttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(URL_SERVER_DELETE_USER + "/" + id,
                HttpMethod.DELETE, httpEntity, Integer.class);
    }

    public ResponseEntity createUser(String userResponse){
        HttpEntity httpEntity = new HttpEntity(userResponse, getHttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity user =
                restTemplate.exchange(URL_SERVER_CREATE_USER,HttpMethod.POST, httpEntity, User.class);
        return user;
    }

    public ResponseEntity editUser(String userResponse){
        HttpEntity httpEntity = new HttpEntity(userResponse, getHttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity user = restTemplate.exchange(URL_SERVER_EDIT_USER,HttpMethod.POST, httpEntity, User.class);
        return user;
    }

    public UserDetails getUserByLogin(String login) {
        HttpEntity httpEntity = new HttpEntity(getHttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> user = restTemplate.exchange(URL_SERVER_USERS + "/" + login, HttpMethod.GET, httpEntity, User.class);
        return user.getBody();
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType("application", "json", Charset.forName("UTF-8"))));
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
        headers.setBasicAuth(Base64.getEncoder()
                .encodeToString((USERNAME_ON_SERVER + ":" + USERPASSWORD_ON_SERVER)
                        .getBytes(Charset.forName("UTF-8"))));
        return headers;
    }
}
