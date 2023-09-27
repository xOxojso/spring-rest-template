package ru.kata.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.kata.rest.entity.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Communication {
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final HttpHeaders HEADERS = new HttpHeaders();
    private static final String URL = "http://94.198.50.185:7081/api/users";

    public void getAll() {
        ResponseEntity<List<User>> entity = REST_TEMPLATE.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
        List<String> cookies = entity.getHeaders().get("Set-Cookie");
        HEADERS.setContentType(MediaType.APPLICATION_JSON);
        HEADERS.set("Cookie", String.join(";", Objects.requireNonNull(cookies)));
        Objects.requireNonNull(entity.getBody()).forEach(System.out::println);
    }

    public void add(User user) {
        HttpEntity<User> requestBody = new HttpEntity<>(user, HEADERS);
        System.out.println(REST_TEMPLATE.exchange(URL, HttpMethod.POST, requestBody, String.class).getBody());
    }

    public void update() {
        User user = new User(3L, "Thomas", "Shelby", (byte) 30);
        HttpEntity<User> requestBody = new HttpEntity<>(user, HEADERS);
        System.out.println(REST_TEMPLATE.exchange(URL, HttpMethod.PUT, requestBody, String.class).getBody());
    }

    public void delete(Long id) {
        System.out.println(REST_TEMPLATE.exchange(URL + "/" + id,
                HttpMethod.DELETE, new HttpEntity<>(HEADERS),
                String.class).getBody());
    }
}
