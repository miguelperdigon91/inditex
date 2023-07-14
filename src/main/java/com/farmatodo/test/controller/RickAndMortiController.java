package com.farmatodo.test.controller;

import com.farmatodo.test.dto.EpisodeRequest;
import com.farmatodo.test.dto.EpisodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.farmatodo.test.service.EpisodeManager;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("rick-and-morti")
@RequiredArgsConstructor
public class RickAndMortiController {
    private final EpisodeManager episodeManager;

    @GetMapping("episode/{id}")
    public ResponseEntity<EpisodeResponse> get(@PathVariable("id") int episodeId) {
        EpisodeRequest request = EpisodeRequest.builder()
                .id(episodeId)
                .build();

        return ok(episodeManager.findEpisode(request));
    }
}
