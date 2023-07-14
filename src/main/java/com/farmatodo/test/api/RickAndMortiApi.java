package com.farmatodo.test.api;

import com.farmatodo.test.api.dto.CharacterApiResponse;
import com.farmatodo.test.api.dto.EpisodeApiResponse;
import com.farmatodo.test.api.dto.LocationApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${app.api.tv-show.name}", url = "${app.api.tv-show.url}")
public interface RickAndMortiApi {

    @GetMapping("${app.api.tv-show.paths.episode}")
    EpisodeApiResponse getEpisode(@PathVariable("id") int episodeId);

    @GetMapping
    CharacterApiResponse getCharacter();

    @GetMapping
    LocationApiResponse getLocation();
}
