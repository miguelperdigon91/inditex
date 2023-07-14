package com.farmatodo.test.service;

import com.farmatodo.test.api.RickAndMortiApi;
import com.farmatodo.test.api.dto.EpisodeApiResponse;
import com.farmatodo.test.commons.utils.DateUtil;
import com.farmatodo.test.dto.CharacterDto;
import com.farmatodo.test.dto.EpisodeRequest;
import com.farmatodo.test.dto.EpisodeResponse;
import com.farmatodo.test.model.Episode;
import com.farmatodo.test.repository.EpisodeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.farmatodo.test.TestDataCommons.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EpisodeManagerTest {
    private final static int EPISODE_ID = 3;
    private final static LocalDateTime NOW = LocalDateTime.now();

    @Mock
    private RickAndMortiApi rickAndMortiApi;
    @Mock
    private EpisodeRepository episodeRepository;
    @Mock
    private CharacterManager characterManager;
    @Mock
    private DateUtil dateUtil;

    @InjectMocks
    private EpisodeManager manager;

    @Test
    public void findEpisode_WhenEpisodeDoesNotExistInDB() {
        Episode episode = buildEpisode(NOW, EPISODE_ID);
        EpisodeApiResponse apiResponse = buildEpisodeApiResponse(EPISODE_ID);
        CharacterDto characterDto = buildCharacterDto();
        EpisodeResponse expected = buildEpisodeResponse(EPISODE_ID);
        EpisodeRequest request = buildEpisodeRequest(EPISODE_ID);

        when(episodeRepository.findByNumber(EPISODE_ID)).thenReturn(Optional.empty());
        when(rickAndMortiApi.getEpisode(EPISODE_ID)).thenReturn(apiResponse);
        when(characterManager.getCharacter("characterUrl")).thenReturn(characterDto);
        when(episodeRepository.save(episode)).thenReturn(episode);
        when(dateUtil.now()).thenReturn(NOW);

        EpisodeResponse response = manager.findEpisode(request);

        assertEquals(expected, response);
    }

    @Test
    public void findEpisode_WhenEpisodeExistsInDB() {
        Episode episode = buildEpisode(NOW, EPISODE_ID);
        CharacterDto characterDto = buildCharacterDto();
        EpisodeResponse expected = buildEpisodeResponse(EPISODE_ID);
        EpisodeRequest request = buildEpisodeRequest(EPISODE_ID);

        when(episodeRepository.findByNumber(EPISODE_ID)).thenReturn(Optional.of(episode));
        when(characterManager.getCharacter("characterUrl")).thenReturn(characterDto);

        EpisodeResponse response = manager.findEpisode(request);

        assertEquals(expected, response);
    }
}
