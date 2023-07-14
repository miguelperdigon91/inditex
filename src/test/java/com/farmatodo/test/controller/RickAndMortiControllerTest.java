package com.farmatodo.test.controller;

import com.farmatodo.test.dto.EpisodeResponse;
import com.farmatodo.test.dto.SumAllNumberResponse;
import com.farmatodo.test.model.Character;
import com.farmatodo.test.model.Episode;
import com.farmatodo.test.model.Location;
import com.farmatodo.test.repository.CharacterRepository;
import com.farmatodo.test.repository.EpisodeRepository;
import com.farmatodo.test.repository.LocationRepository;
import com.farmatodo.test.service.CharacterManager;
import com.farmatodo.test.service.EpisodeManager;
import com.farmatodo.test.service.LocationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import static com.farmatodo.test.TestDataCommons.*;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RickAndMortiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private EpisodeManager episodeManager;
    @Autowired
    private EpisodeRepository episodeRepository;
    @Autowired
    private CharacterManager characterManager;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private LocationManager locationManager;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    private void init() {
        locationRepository.deleteAll();
        characterRepository.deleteAll();
        episodeRepository.deleteAll();
    }

    @Test
    public void get() throws MalformedURLException {
        Location location = buildLocation(LocalDateTime.now(), 3);
        locationRepository.save(location);

        Character character = buildCharacter(LocalDateTime.now(), 3);
        character.setLocation("https://rickandmortyapi.com/api/location/3");
        characterRepository.save(character);

        Episode episode = buildEpisode(LocalDateTime.now(), 3);
        episode.setCharacters(singletonList("https://rickandmortyapi.com/api/character/3"));
        episodeRepository.save(episode);

        EpisodeResponse expected = buildEpisodeResponse(3);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        ResponseEntity<EpisodeResponse> response = restTemplate.exchange(
                buildGetEpisodeUrl(3),
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<EpisodeResponse>() {
                });

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expected, response.getBody());
    }

    private String buildGetEpisodeUrl(int num) throws MalformedURLException {
        return new URL("http://localhost:" + port +
                "/api/farmatodo-test/rick-and-morti/episode/" + num
        ).toString();
    }
}
