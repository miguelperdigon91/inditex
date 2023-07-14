package com.farmatodo.test.service;

import com.farmatodo.test.api.RickAndMortiDynamicApi;
import com.farmatodo.test.api.dto.LocationApiResponse;
import com.farmatodo.test.commons.utils.DateUtil;
import com.farmatodo.test.commons.utils.IdExtractor;
import com.farmatodo.test.dto.LocationDto;
import com.farmatodo.test.model.Location;
import com.farmatodo.test.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isEmpty;

@Slf4j
@Component
@RequiredArgsConstructor
public class LocationManager {
    private final static String KEY_SEPARATOR = "location/";
    private final RickAndMortiDynamicApi dynamicApi;
    private final LocationRepository locationRepository;
    private final IdExtractor idExtractor;
    private final DateUtil dateUtil;

    public LocationDto getLocation(String url) {
        if (isEmpty(url)) {
            log.info("the Location is unknown");
            return null;
        }

        log.info("Trying to find location from url [{}]", url);
        String locationId = idExtractor.extract(url, KEY_SEPARATOR);

        Optional<Location> locationOpt = locationRepository.findByLocationId(parseInt(locationId));

        if (locationOpt.isPresent()) {
            log.info("The location [{}] was in DB", locationId);
            Location location = locationOpt.get();
            return toLocationDto(location);
        }

        log.info("The location [{}] must be searched by api", locationId);
        LocationApiResponse locationApiResponse = dynamicApi.findLocation(url);

        if (isNull(locationApiResponse)) {
            return null;
        }

        Location location = Location.builder()
                .dimension(locationApiResponse.getDimension())
                .name(locationApiResponse.getName())
                .type(locationApiResponse.getType())
                .locationId(locationApiResponse.getId())
                .createAt(dateUtil.now())
                .build();

        return toLocationDto(locationRepository.save(location));
    }

    private LocationDto toLocationDto(Location location) {
        return LocationDto.builder()
                .type(location.getType())
                .dimension(location.getDimension())
                .name(location.getName())
                .build();
    }
}
