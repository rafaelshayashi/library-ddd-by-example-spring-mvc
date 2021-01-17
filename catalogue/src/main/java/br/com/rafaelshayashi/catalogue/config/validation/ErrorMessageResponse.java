package br.com.rafaelshayashi.catalogue.config.validation;

import java.util.List;

public class ErrorMessageResponse {

    private String message;
    private List<FieldErrorResponse> errorResponseList;

    public ErrorMessageResponse(String message, List<FieldErrorResponse> errorResponseList) {
        this.message = message;
        this.errorResponseList = errorResponseList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldErrorResponse> getErrorResponseList() {
        return errorResponseList;
    }

    public void setErrorResponseList(List<FieldErrorResponse> errorResponseList) {
        this.errorResponseList = errorResponseList;
    }
}
