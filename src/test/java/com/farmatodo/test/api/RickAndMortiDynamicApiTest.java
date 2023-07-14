package com.farmatodo.test.api;

import com.farmatodo.test.api.dto.CharacterApiResponse;
import com.farmatodo.test.api.dto.LocationApiResponse;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.farmatodo.test.TestDataCommons.buildCharacterApiResponse;
import static com.farmatodo.test.TestDataCommons.buildLocationApiResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RickAndMortiDynamicApiTest {
    private final static String URL = "URL";
    private final static int ID = 3;

    @Mock
    private ApiClientProvider apiClientProvider;

    @InjectMocks
    private RickAndMortiDynamicApi dynamicApi;

    @Test
    public void findLocation_WhenApiResponse() {
        RickAndMortiApi rickAndMortiApi = Mockito.mock(RickAndMortiApi.class);
        LocationApiResponse expected = buildLocationApiResponse(ID);

        when(apiClientProvider.getRickAndMortiApiClient(URL)).thenReturn(rickAndMortiApi);
        when(rickAndMortiApi.getLocation()).thenReturn(expected);

        LocationApiResponse result = dynamicApi.findLocation(URL);

        assertEquals(expected, result);
    }

    @Test
    public void findLocation_WhenApiResponseError_ThenReturnNull() {
        RickAndMortiApi rickAndMortiApi = Mockito.mock(RickAndMortiApi.class);

        when(apiClientProvider.getRickAndMortiApiClient(URL)).thenReturn(rickAndMortiApi);
        when(rickAndMortiApi.getLocation()).thenThrow(FeignException.class);

        LocationApiResponse result = dynamicApi.findLocation(URL);

        assertNull(result);
    }

    @Test
    public void findCharacter_WhenApiResponse() {
        RickAndMortiApi rickAndMortiApi = Mockito.mock(RickAndMortiApi.class);
        CharacterApiResponse expected = buildCharacterApiResponse(ID);

        when(apiClientProvider.getRickAndMortiApiClient(URL)).thenReturn(rickAndMortiApi);
        when(rickAndMortiApi.getCharacter()).thenReturn(expected);

        CharacterApiResponse result = dynamicApi.findCharacter(URL);

        assertEquals(expected, result);
    }

    @Test
    public void findCharacter_WhenApiResponseError_ThenReturnNull() {
        RickAndMortiApi rickAndMortiApi = Mockito.mock(RickAndMortiApi.class);

        when(apiClientProvider.getRickAndMortiApiClient(URL)).thenReturn(rickAndMortiApi);
        when(rickAndMortiApi.getCharacter()).thenThrow(FeignException.class);

        CharacterApiResponse result = dynamicApi.findCharacter(URL);

        assertNull(result);
    }
}
