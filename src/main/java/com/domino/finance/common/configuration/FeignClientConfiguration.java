package com.domino.finance.common.configuration;

import com.domino.finance.common.exception.ExternalClientCanNotProceedException;
import feign.Logger;
import feign.Response;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.util.List;

public class FeignClientConfiguration {

    private static final int PERIOD = 1000;
    private static final int MAX_PERIOD = 2000;
    private static final int MAX_ATTEMPTS = 1;
    private static final List<HttpStatus> NORMAL_STATUS = List.of(HttpStatus.OK,
            HttpStatus.CREATED,
            HttpStatus.ACCEPTED,
            HttpStatus.MOVED_PERMANENTLY);

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(PERIOD, MAX_PERIOD, MAX_ATTEMPTS);
    }

    @Bean
    public ErrorDecoder decoder() {
        return (methodKey, response) -> {
            HttpStatus responseStatus = HttpStatus.resolve(response.status());

            if (NORMAL_STATUS.contains(responseStatus)) {
                return null;
            }

            return new ExternalClientCanNotProceedException(getBodyString(response));
        };
    }

    private String getBodyString(Response response) {
        if (null == response || null == response.body()) {
            return Strings.EMPTY;
        }

        return response.body().toString();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
