package br.com.rafaelshayashi.library.config.feign;

import br.com.rafaelshayashi.library.exception.ResourceNotExistsException;
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
                return new Exception(response.reason());
            case 404:
                String message = String.format("Error took place when using Feign client to send HTTP Request. Status code %s, methodkey = %s", response.status(), methodKey);
                logger.warn(message);
                return new ResourceNotExistsException(response.request().url());
            default:
                return new Exception(response.reason());
        }
    }
}
