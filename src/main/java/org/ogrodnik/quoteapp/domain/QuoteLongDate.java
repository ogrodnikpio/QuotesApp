package org.ogrodnik.quoteapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Piotr on 31.05.2016.
 */
public class QuoteLongDate {
        private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(long quoteDate) {
        this.quoteDate = quoteDate;
    }

    public double getQuoteValue() {
        return quoteValue;
    }

    public void setQuoteValue(double quoteValue) {
        this.quoteValue = quoteValue;
    }

    private long quoteDate;
        private double quoteValue;

        public QuoteLongDate() {
        }

        public QuoteLongDate(Date quoteDate, double quoteValue) {
            this.quoteDate = quoteDate.getTime();
            this.quoteValue = quoteValue;
        }
        public QuoteLongDate(Quote quote) {
            this.quoteDate=quote.getQuoteDate().getTime();
            this.quoteValue=quote.getQuoteValue();
            this.id=quote.getId();
        }






}
