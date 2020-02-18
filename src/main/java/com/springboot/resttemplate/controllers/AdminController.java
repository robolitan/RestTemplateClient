package com.springboot.resttemplate.controllers;

import com.springboot.resttemplate.models.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    private static final String URL_SERVER_USER = "http://localhost:8081/api/admin";
    private static final String URL_SERVER_USERS = "http://localhost:8081/api/admin/user";
    private static final String URL_SERVER_DELETE_USER = "http://localhost:8081/api/admin";
    private static final String URL_SERVER_CREATE_USER = "http://localhost:8081/api/admin/create";
    private static final String URL_SERVER_EDIT_USER = "http://localhost:8081/api/admin/edit";
    private static final String USERNAME_ON_SERVER = "admin";
    private static final String USERPASSWORD_ON_SERVER = "admin11";

    @GetMapping(value = "user/{id}")
    public ResponseEntity getUser(@PathVariable("id") Integer id) {
        HttpEntity httpEntity = new HttpEntity(getHttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> user =
                restTemplate.exchange(URL_SERVER_USER + "/" + id, HttpMethod.GET, httpEntity, User.class);
        return ResponseEntity.status(HttpStatus.OK).body(user.getBody());
    }

    @GetMapping(value = "user")
    public ResponseEntity<List<User>> getUsers() {
        HttpEntity httpEntity = new HttpEntity(getHttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL_SERVER_USERS, HttpMethod.GET,
                        httpEntity, new ParameterizedTypeReference<List<User>>() {
                        });
        return ResponseEntity.status(HttpStatus.OK).body(responseEntity.getBody());
    }

    @PostMapping(value = "user/{id}/delete")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        HttpEntity httpEntity = new HttpEntity(getHttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(URL_SERVER_DELETE_USER + "/" + id,
                HttpMethod.DELETE, httpEntity, Integer.class);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @PostMapping(value = "user/create")
    public ResponseEntity create(@RequestBody String user) {
        HttpEntity httpEntity = new HttpEntity(user, getHttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        User userResponse =
                restTemplate.postForObject(URL_SERVER_CREATE_USER, httpEntity, User.class);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PostMapping(value = "user/edit")
    public ResponseEntity edit(@RequestBody String user) {
        HttpEntity httpEntity = new HttpEntity(user, getHttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        User userResponse = restTemplate.postForObject(URL_SERVER_EDIT_USER, httpEntity, User.class);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    public UserDetails getUserByLogin(String login) {
        HttpEntity httpEntity = new HttpEntity(getHttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.exchange(URL_SERVER_USERS + "/" + login, HttpMethod.GET, httpEntity, User.class).getBody();
        return user;
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
