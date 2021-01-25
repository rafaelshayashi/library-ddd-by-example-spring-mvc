package br.com.rafaelshayashi.catalogue.controller.response;

import br.com.rafaelshayashi.catalogue.model.BookValue;

public class BookValueResponse {

    private final String currency;
    private final Integer value;
    private final String unit;

    public BookValueResponse() {
        this.currency = null;
        this.value = null;
        this.unit = null;
    }

    public BookValueResponse(String currency, Integer value, String unit) {
        this.currency = currency;
        this.value = value;
        this.unit = unit;
    }

    public static BookValueResponse of(BookValue value) {
        if(value == null){
            return new BookValueResponse();
        }
        return new BookValueResponse(value.getCurrency(), value.getAmount(), value.getUnitType().toString());
    }

    public String getCurrency() {
        return currency;
    }

    public Integer getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
