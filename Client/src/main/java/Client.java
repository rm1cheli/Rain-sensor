import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String url = "http://localhost:8080/measurements/add";
        RestTemplate restTemplate = new RestTemplate();
        Random random = new Random();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Map<String, Object>> httpEntity;
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Bob");
        httpEntity = new HttpEntity<>(map);
        restTemplate.postForObject("http://localhost:8080/sensors/registration", httpEntity, String.class);
        map.clear();
        for(int i = 0; i < 1000; i++){
            map.put("temperature", random.nextDouble());
            map.put("rain", random.nextBoolean());
            map.put("sensor", Map.of("name", "Bob"));
            httpEntity = new HttpEntity<>(map);
            restTemplate.postForObject(url, httpEntity, String.class);
        }
    }
}