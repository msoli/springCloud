package com.example.serverzuul.configuration;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import lombok.Data;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Data
@Component
public class GenericFallbackProvider implements FallbackProvider {

    private String responseBody = "{\"message\":\"Service Unavailable. Please try after sometime\"}";
    private HttpHeaders headers = null;
    private String route = null;
    private int rawStatusCode = 503;
    private HttpStatus statusCode = HttpStatus.SERVICE_UNAVAILABLE;
    private String statusText = "Service Unavailable";


    private ClientHttpResponse getCustomClientHttpResponse() {
        return new ClientHttpResponse() {

            @Override
            public InputStream getBody() throws IOException {
                if (responseBody == null)
                    responseBody = "{\"message\":\"Service Unavailable. Please try after sometime\"}";
                return new ByteArrayInputStream(responseBody.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                if (headers == null) {
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                }
                return headers;
            }

            @Override
            public void close() {

            }

            @Override
            public int getRawStatusCode() throws IOException {
                return rawStatusCode;
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                if (statusCode == null)
                    statusCode = HttpStatus.SERVICE_UNAVAILABLE;
                return statusCode;
            }

            @Override
            public String getStatusText() throws IOException {
                if (statusText == null)
                    statusText = "Service Unavailable";
                return statusText;
            }

        };
    }

    @Override
    public String getRoute() {
        if (route == null) {
            route = "route";
        }
        return route;
    }


    @Override
    public ClientHttpResponse fallbackResponse() {
        return getCustomClientHttpResponse();
    }


    @Override
    public ClientHttpResponse fallbackResponse(Throwable cause) {
        return getCustomClientHttpResponse();
    }
}