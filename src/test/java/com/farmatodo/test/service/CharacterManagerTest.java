package com.farmatodo.test.service;

import com.farmatodo.test.api.RickAndMortiDynamicApi;
import com.farmatodo.test.api.dto.CharacterApiResponse;
import com.farmatodo.test.commons.utils.DateUtil;
import com.farmatodo.test.commons.utils.IdExtractor;
import com.farmatodo.test.dto.CharacterDto;
import com.farmatodo.test.dto.LocationDto;
import com.farmatodo.test.model.Character;
import com.farmatodo.test.repository.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.farmatodo.test.TestDataCommons.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CharacterManagerTest {
    private final static String URL = "https://rickandmortyapi.com/api/character/3";
    private final static int CHARACTER_ID = 3;
    private final static String CHARACTER_ID_STR = "3";
    private final static LocalDateTime NOW = LocalDateTime.now();
    private final static String KEY_SEPARATOR = "character/";

    @Mock
    private RickAndMortiDynamicApi dynamicApi;
    @Mock
    private CharacterRepository characterRepository;
    @Mock
    private IdExtractor idExtractor;
    @Mock
    private LocationManager locationManager;
    @Mock
    private DateUtil dateUtil;

    @InjectMocks
    private CharacterManager manager;

    @Test
    public void getCharacter_WhenCharacterDoesNotExistInDB() {
        Character character = buildCharacter(NOW, CHARACTER_ID);
        CharacterApiResponse apiResponse = buildCharacterApiResponse(CHARACTER_ID);
        LocationDto locationDto = buildLocationDto();
        CharacterDto expected = buildCharacterDto();

        when(idExtractor.extract(URL, KEY_SEPARATOR)).thenReturn(CHARACTER_ID_STR);
        when(characterRepository.findByCharacterId(CHARACTER_ID)).thenReturn(Optional.empty());
        when(dynamicApi.findCharacter(URL)).thenReturn(apiResponse);
        when(locationManager.getLocation("locationUrl")).thenReturn(locationDto);
        when(dateUtil.now()).thenReturn(NOW);
        when(characterRepository.save(character)).thenReturn(character);

        CharacterDto response = manager.getCharacter(URL);

        assertEquals(expected, response);
    }

    @Test
    public void getCharacter_WhenApiError_ThenReturnNull() {
        when(idExtractor.extract(URL, KEY_SEPARATOR)).thenReturn(CHARACTER_ID_STR);
        when(characterRepository.findByCharacterId(CHARACTER_ID)).thenReturn(Optional.empty());
        when(dynamicApi.findCharacter(URL)).thenReturn(null);

        CharacterDto response = manager.getCharacter(URL);

        assertNull(response);
    }

    @Test
    public void getCharacter_WhenCharacterExistsInDB() {
        Character character = buildCharacter(NOW, CHARACTER_ID);
        LocationDto locationDto = buildLocationDto();
        CharacterDto expected = buildCharacterDto();

        when(idExtractor.extract(URL, KEY_SEPARATOR)).thenReturn(CHARACTER_ID_STR);
        when(characterRepository.findByCharacterId(CHARACTER_ID)).thenReturn(Optional.of(character));
        when(locationManager.getLocation("locationUrl")).thenReturn(locationDto);

        CharacterDto response = manager.getCharacter(URL);

        assertEquals(expected, response);
    }
}
