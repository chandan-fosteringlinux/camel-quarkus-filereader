package com.example.config;

import org.citrusframework.http.client.HttpClient;
import org.citrusframework.http.client.HttpClientBuilder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class CitrusHttpClientConfig {

    @Produces
    public HttpClient createHttpClient() {
        return new HttpClientBuilder()
                .requestUrl("http://localhost:8080")  
                .build();
    }
}

