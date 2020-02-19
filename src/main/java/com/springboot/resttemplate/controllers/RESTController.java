package com.springboot.resttemplate.controllers;

import com.springboot.resttemplate.models.User;
import com.springboot.resttemplate.services.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RESTController {

    @Autowired
    RestTemplateService restTemplateService;

    @GetMapping(value = "user/{id}")
    public ResponseEntity getUser(@PathVariable("id") Integer id) {
        ResponseEntity user = restTemplateService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(user.getBody());
    }

    @GetMapping(value = "user")
    public ResponseEntity getUsers() {
       ResponseEntity users = restTemplateService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users.getBody());
    }

    @PostMapping(value = "user/{id}/delete")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        restTemplateService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @PostMapping(value = "user/create")
    public ResponseEntity create(@RequestBody String userResponse) {
        ResponseEntity user = restTemplateService.createUser(userResponse);
        return ResponseEntity.status(HttpStatus.OK).body(user.getBody());
    }

    @PostMapping(value = "user/edit")
    public ResponseEntity edit(@RequestBody String userResponse) {
        ResponseEntity user = restTemplateService.editUser(userResponse);
        return ResponseEntity.status(HttpStatus.OK).body(user.getBody());
    }
}
