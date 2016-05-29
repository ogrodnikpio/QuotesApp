package org.ogrodnik.quoteapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Piotr on 26.05.2016.
 */
@Entity
@Table(name = "Quote")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date quoteDate;
    @NotNull
    private double quoteValue;

    public Quote() {
    }

    public Quote(Date quoteDate, double quoteValue) {
        this.quoteDate = quoteDate;
        this.quoteValue = quoteValue;
    }

    public Date getQuoteDate() {
        return quoteDate;
    }

    public double getQuoteValue() {
        return quoteValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
            return false;
        }
        Quote that = (Quote) obj;
        return this.id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

    public Long getId() {
        return id;
    }


}
