package com.farmatodo.test.service;

import com.farmatodo.test.api.RickAndMortiDynamicApi;
import com.farmatodo.test.api.dto.CharacterApiResponse;
import com.farmatodo.test.commons.utils.DateUtil;
import com.farmatodo.test.commons.utils.IdExtractor;
import com.farmatodo.test.dto.CharacterDto;
import com.farmatodo.test.model.Character;
import com.farmatodo.test.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class CharacterManager {
    private final static String KEY_SEPARATOR = "character/";
    private final RickAndMortiDynamicApi dynamicApi;
    private final CharacterRepository characterRepository;
    private final IdExtractor idExtractor;
    private final LocationManager locationManager;
    private final DateUtil dateUtil;

    public CharacterDto getCharacter(String url) {
        log.info("Trying to find Character from url [{}]", url);
        String characterId = idExtractor.extract(url, KEY_SEPARATOR);

        Optional<Character> characterOpt = characterRepository.findByCharacterId(parseInt(characterId));

        if (characterOpt.isPresent()) {
            log.info("The Character [{}] was in DB", characterId);
            Character character = characterOpt.get();
            return toCharacterDto(character);
        }

        log.info("The Character [{}] must be searched by api", characterId);
        CharacterApiResponse characterApiResponse = dynamicApi.findCharacter(url);

        if (isNull(characterApiResponse)) {
            return null;
        }

        Character character = Character.builder()
                .characterId(characterApiResponse.getId())
                .name(characterApiResponse.getName())
                .gender(characterApiResponse.getGender())
                .image(characterApiResponse.getImage())
                .species(characterApiResponse.getSpecies())
                .createAt(dateUtil.now())
                .name(characterApiResponse.getName())
                .location(characterApiResponse.getLocation().getUrl())
                .build();

        return toCharacterDto(characterRepository.save(character));
    }

    private CharacterDto toCharacterDto(Character character) {
        return CharacterDto.builder()
                .species(character.getSpecies())
                .image(character.getImage())
                .gender(character.getGender())
                .name(character.getName())
                .location(locationManager.getLocation(character.getLocation()))
                .build();
    }
}
