package org.ogrodnik.quoteapp.mvc;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Piotr on 29.05.2016.
 */
public class DatesWrapper {
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    @NotNull
    @Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])\\-(0?[1-9]|1[012])\\-\\d{4}$",message =
    "wrong date format")
    private String date;
    @NotNull
    @Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])\\-(0?[1-9]|1[012])\\-\\d{4}$",message =
            "wrong date format")
    private String date2;



}
