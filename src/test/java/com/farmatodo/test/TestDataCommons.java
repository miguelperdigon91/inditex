package com.farmatodo.test;

import com.farmatodo.test.api.dto.CharacterApiResponse;
import com.farmatodo.test.api.dto.CharacterLocation;
import com.farmatodo.test.api.dto.EpisodeApiResponse;
import com.farmatodo.test.api.dto.LocationApiResponse;
import com.farmatodo.test.dto.CharacterDto;
import com.farmatodo.test.dto.EpisodeRequest;
import com.farmatodo.test.dto.EpisodeResponse;
import com.farmatodo.test.dto.LocationDto;
import com.farmatodo.test.model.Character;
import com.farmatodo.test.model.Episode;
import com.farmatodo.test.model.Location;

import java.time.LocalDateTime;

import static java.util.Collections.singletonList;

public class TestDataCommons {
    private TestDataCommons() {
    }

    public static Location buildLocation(LocalDateTime createdAt, int id) {
        return Location.builder()
                .createAt(createdAt)
                .name("name")
                .locationId(id)
                .type("type")
                .dimension("dimension")
                .build();
    }

    public static LocationApiResponse buildLocationApiResponse(int id) {
        return LocationApiResponse.builder()
                .name("name")
                .id(id)
                .dimension("dimension")
                .type("type")
                .build();
    }

    public static LocationDto buildLocationDto() {
        return LocationDto.builder()
                .name("name")
                .dimension("dimension")
                .type("type")
                .build();
    }

    public static Character buildCharacter(LocalDateTime now, int characterId) {
        return Character.builder()
                .location("locationUrl")
                .characterId(characterId)
                .species("species")
                .image("image")
                .gender("gender")
                .createAt(now)
                .name("name")
                .build();
    }

    public static CharacterApiResponse buildCharacterApiResponse(int characterId) {
        return CharacterApiResponse.builder()
                .gender("gender")
                .id(characterId)
                .image("image")
                .location(CharacterLocation.builder()
                        .url("locationUrl")
                        .build())
                .name("name")
                .species("species")
                .build();
    }

    public static CharacterDto buildCharacterDto() {
        return CharacterDto.builder()
                .name("name")
                .gender("gender")
                .image("image")
                .species("species")
                .location(buildLocationDto())
                .build();
    }

    public static Episode buildEpisode(LocalDateTime now, int id) {
        return Episode.builder()
                .createAt(now)
                .characters(singletonList("characterUrl"))
                .name("name")
                .number(id)
                .build();
    }

    public static EpisodeApiResponse buildEpisodeApiResponse(int id) {
        return EpisodeApiResponse.builder()
                .characters(singletonList("characterUrl"))
                .id(id)
                .name("name")
                .build();
    }

    public static EpisodeResponse buildEpisodeResponse(int id) {
        return EpisodeResponse.builder()
                .characters(singletonList(buildCharacterDto()))
                .episodeName("name")
                .episode(id)
                .build();
    }

    public static EpisodeRequest buildEpisodeRequest(int id) {
        return EpisodeRequest.builder()
                .id(id)
                .build();
    }
}
