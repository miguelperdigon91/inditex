package com.farmatodo.test.service;

import com.farmatodo.test.api.RickAndMortiDynamicApi;
import com.farmatodo.test.api.dto.LocationApiResponse;
import com.farmatodo.test.commons.utils.DateUtil;
import com.farmatodo.test.commons.utils.IdExtractor;
import com.farmatodo.test.dto.LocationDto;
import com.farmatodo.test.model.Location;
import com.farmatodo.test.repository.LocationRepository;
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
public class LocationManagerTest {
    private final static String URL = "https://rickandmortyapi.com/api/location/3";
    private final static int LOCATOR_ID = 3;
    private final static String LOCATOR_ID_STR = "3";
    private final static LocalDateTime NOW = LocalDateTime.now();
    private final static String KEY_SEPARATOR = "location/";

    @Mock
    private RickAndMortiDynamicApi dynamicApi;
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private IdExtractor idExtractor;
    @Mock
    private DateUtil dateUtil;
    @InjectMocks
    private LocationManager manager;

    @Test
    public void getLocation_WhenLocationDoesNotExistInDB() {
        Location location = buildLocation(NOW, LOCATOR_ID);
        LocationApiResponse apiResponse = buildLocationApiResponse(LOCATOR_ID);
        LocationDto expected = buildLocationDto();

        when(locationRepository.findByLocationId(LOCATOR_ID)).thenReturn(Optional.empty());
        when(dynamicApi.findLocation(URL)).thenReturn(apiResponse);
        when(dateUtil.now()).thenReturn(NOW);
        when(idExtractor.extract(URL, KEY_SEPARATOR)).thenReturn(LOCATOR_ID_STR);
        when(locationRepository.save(location)).thenReturn(location);

        LocationDto response = manager.getLocation(URL);

        assertEquals(expected, response);
    }

    @Test
    public void getLocation_WhenApiError_ThenReturnNull() {
        when(locationRepository.findByLocationId(LOCATOR_ID)).thenReturn(Optional.empty());
        when(dynamicApi.findLocation(URL)).thenReturn(null);
        when(idExtractor.extract(URL, KEY_SEPARATOR)).thenReturn(LOCATOR_ID_STR);

        LocationDto response = manager.getLocation(URL);

        assertNull(response);
    }

    @Test
    public void getLocation_WhenLocationExistsInDB() {
        Location location = buildLocation(NOW, LOCATOR_ID);
        LocationDto expected = buildLocationDto();

        when(locationRepository.findByLocationId(LOCATOR_ID)).thenReturn(Optional.of(location));
        when(idExtractor.extract(URL, KEY_SEPARATOR)).thenReturn(LOCATOR_ID_STR);

        LocationDto response = manager.getLocation(URL);

        assertEquals(expected, response);
    }

    @Test
    public void getLocation_WhenUnknownLocation_ThenReturnNull() {
        LocationDto response = manager.getLocation("");

        assertNull(response);
    }
}
