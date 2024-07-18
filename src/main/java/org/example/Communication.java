package org.example;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;

    private final String address = "http://94.198.50.185:7081/api/users";

    public List<User> getUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange(address, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });

        List<User> allUsers = response.getBody();

        httpHeaders.add(HttpHeaders.COOKIE, response.getHeaders().getFirst(HttpHeaders.SET_COOKIE));

        System.out.println(response);

        return allUsers;
    }

    public String addUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(address, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }

    public String editUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(address, HttpMethod.PUT, entity, String.class);

        return response.getBody();
    }

    public String deleteUser(User user, Long id) {
        HttpEntity<Long> entity = new HttpEntity<>(id, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(address + "/" + id, HttpMethod.DELETE, entity, String.class);

        return response.getBody();
    }
}
