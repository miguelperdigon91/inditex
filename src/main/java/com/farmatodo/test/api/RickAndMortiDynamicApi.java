package com.farmatodo.test.api;

import com.farmatodo.test.api.dto.CharacterApiResponse;
import com.farmatodo.test.api.dto.LocationApiResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RickAndMortiDynamicApi {
    private final ApiClientProvider apiClientProvider;

    public LocationApiResponse findLocation(String url) {
        log.info("Trying to find location in the next url [{}]", url);
        try {
            RickAndMortiApi apiClient = apiClientProvider.getRickAndMortiApiClient(url);
            LocationApiResponse response = apiClient.getLocation();
            log.info("Successfully search of location [{}]", response);

            return response;

        } catch (FeignException e) {
            log.error("Error => unexpected error when get location [{}]", e.getMessage());
            return null;
        }
    }

    public CharacterApiResponse findCharacter(String url) {
        log.info("Trying to find character in the next url [{}]", url);
        try {
            RickAndMortiApi apiClient = apiClientProvider.getRickAndMortiApiClient(url);
            CharacterApiResponse response = apiClient.getCharacter();
            log.info("Successfully search of character [{}]", response);

            return response;

        } catch (FeignException e) {
            log.error("Error => unexpected error when get character [{}]", e.getMessage());
            return null;
        }
    }

}
