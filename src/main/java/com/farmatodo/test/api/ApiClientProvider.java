package com.farmatodo.test.api;

import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class ApiClientProvider {
    private final FeignClientBuilder feignClientBuilder;

    public ApiClientProvider(ApplicationContext applicationContext) {
        this.feignClientBuilder = new FeignClientBuilder(applicationContext);
    }

    public RickAndMortiApi getRickAndMortiApiClient(String url) {
        return feignClientBuilder.forType(RickAndMortiApi.class, "RickAndMorti")
                .url(url)
                .build();
    }
}
