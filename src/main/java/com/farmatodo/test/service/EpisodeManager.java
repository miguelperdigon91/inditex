package com.farmatodo.test.service;

import com.farmatodo.test.api.RickAndMortiApi;
import com.farmatodo.test.api.dto.EpisodeApiResponse;
import com.farmatodo.test.commons.utils.DateUtil;
import com.farmatodo.test.dto.EpisodeRequest;
import com.farmatodo.test.dto.EpisodeResponse;
import com.farmatodo.test.model.Episode;
import com.farmatodo.test.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EpisodeManager {
    private final RickAndMortiApi rickAndMortiApi;
    private final EpisodeRepository episodeRepository;
    private final CharacterManager characterManager;
    private final DateUtil dateUtil;

    public EpisodeResponse findEpisode(EpisodeRequest request) {
        log.info("Trying to find Episode [{}]", request.getId());
        Optional<Episode> episodeOpt = episodeRepository.findByNumber(request.getId());

        if (episodeOpt.isPresent()) {
            log.info("The Episode [{}] was in DB", request.getId());
            Episode episode = episodeOpt.get();
            return toResponse(episode);
        }

        EpisodeApiResponse episodeResponse = rickAndMortiApi.getEpisode(request.getId());

        Episode episode = Episode.builder()
                .number(episodeResponse.getId())
                .name(episodeResponse.getName())
                .createAt(dateUtil.now())
                .characters(episodeResponse.getCharacters())
                .build();

        return toResponse(episodeRepository.save(episode));
    }

    private EpisodeResponse toResponse(Episode episode) {
        return EpisodeResponse.builder()
                .episode(episode.getNumber())
                .episodeName(episode.getName())
                .characters(episode.getCharacters()
                        .stream()
                        .map(characterManager::getCharacter)
                        .collect(Collectors.toList()))
                .build();
    }
}
