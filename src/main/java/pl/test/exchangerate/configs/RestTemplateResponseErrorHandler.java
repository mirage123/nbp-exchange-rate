//package pl.test.exchangerate.configs;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.HttpMessageConverterExtractor;
//import org.springframework.web.client.ResponseErrorHandler;
//import pl.test.exchangerate.dtos.api.ServiceErrorResponse;
//import pl.test.exchangerate.exceptions.CustomException;
//
//import java.io.IOException;
//import java.util.List;
//
//@Component
//public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
//    public RestTemplateResponseErrorHandler(List<HttpMessageConverter<?>> messageConverters) {
//        this.messageConverters = messageConverters;
//    }
//
//    @Override
//    public boolean hasError(ClientHttpResponse httpResponse)
//            throws IOException {
//
//        return (
//                httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
//                        || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
//    }
//
//    private List<HttpMessageConverter<?>> messageConverters;
//
//    @Override
//    public void handleError(ClientHttpResponse httpResponse)
//            throws IOException {
//        @SuppressWarnings({"unchecked", "rawtypes"})
//        HttpMessageConverterExtractor<ServiceErrorResponse> errorMessageExtractor =
//                new HttpMessageConverterExtractor<>(ServiceErrorResponse.class, messageConverters);
//
//        ServiceErrorResponse errorObject = errorMessageExtractor.extractData(httpResponse);
//
//
//        if (httpResponse.getStatusCode()
//                .series() == HttpStatus.Series.SERVER_ERROR) {
//            // handle SERVER_ERROR
//        } else if (httpResponse.getStatusCode()
//                .series() == HttpStatus.Series.CLIENT_ERROR) {
//            // handle CLIENT_ERROR
//            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
//                throw new CustomException("Url not found", HttpStatus.BAD_REQUEST);
//            }
//            if (httpResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
//                throw new CustomException("httpResponse", HttpStatus.BAD_REQUEST);
//            }
//        }
//    }
//
//
//}