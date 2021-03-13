package br.com.rafaelshayashi.library.config.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private static final Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()) {
            case 400:
                logger.error("Status code " + response.status() + ", methodKey = " + methodKey);
                return null;
            case 404:
                logger.error("Error took place when using Feign client to send HTTP Request. Status code " + response.status() + ", methodKey = " + methodKey);
                return null;
            default:
                return new Exception(response.reason());
        }
    }
}
