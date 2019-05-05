package com.bulgarian.culture.factory;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public final class RestTemplateFactory {

    private RestTemplateFactory() {

    }

    public static RestTemplate getDefaultRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        return restTemplate;
    }
}