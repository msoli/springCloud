package com.example.serverzuul.configuration;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author moises.macero
 */
@Configuration
public class HystrixFallbackConfiguration {

    /*@Bean
    public FallbackProvider zuulFallbackProvider() {

        return new FallbackProvider() {

            @Override
            public ClientHttpResponse fallbackResponse(Throwable cause) {
                return null;
            }

            @Override
            public String getRoute() {
                // Might be confusing: it's the serviceId property and not the route
                return "demo.microservice";
            }

            @Override
            public ClientHttpResponse fallbackResponse() {
                return new ClientHttpResponse() {
                    @Override
                    public HttpStatus getStatusCode() throws IOException {
                        return HttpStatus.OK;
                    }

                    @Override
                    public int getRawStatusCode() throws IOException {
                        return HttpStatus.OK.value();
                    }

                    @Override
                    public String getStatusText() throws IOException {
                        return HttpStatus.OK.toString();
                    }

                    @Override
                    public void close() {}

                    @Override
                    public InputStream getBody() throws IOException {
                        return new ByteArrayInputStream("{\"factorA\":\"Sorry, Service is Down!\",\"factorB\":\"?\",\"id\":null}".getBytes());
                    }

                    @Override
                    public HttpHeaders getHeaders() {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.setAccessControlAllowCredentials(true);
                        headers.setAccessControlAllowOrigin("*");
                        return headers;
                    }
                };
            }
        };
    }*/

    @Bean
    public FallbackProvider route1FallbackProvider() {
        GenericFallbackProvider route1Fallback = new GenericFallbackProvider();
        route1Fallback.setRoute("saludo.microservice");
        return route1Fallback;
    }

    @Bean
    public FallbackProvider route2FallbackProvider() {
        GenericFallbackProvider route2Fallback = new GenericFallbackProvider();
        route2Fallback.setRoute("demo.microservice");
        route2Fallback.setRawStatusCode(200);
        route2Fallback.setStatusCode(HttpStatus.OK);
        route2Fallback.setResponseBody("{\"message\":\"Service Unavailable. En breve regresamos\"}");
        return route2Fallback;
    }


}
