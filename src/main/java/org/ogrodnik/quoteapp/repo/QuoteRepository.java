package org.ogrodnik.quoteapp.repo;

import org.ogrodnik.quoteapp.domain.Quote;

import java.util.Date;
import java.util.List;

/**
 * Created by Piotr on 26.05.2016.
 */
public interface QuoteRepository {
    //List<Quote> findByQuoteValue(double quoteValue);
    //Quote findByQuoteDate(Date quoteDate);
    Quote findById(Long id);

    Quote findByDate(Date id);

    List<Quote> findByQuotes(Date quoteDate, Date quoteDate2, int page, int pageSize);

    List<Quote> findByQuotes2(Date quoteDate, Date quoteDate2);
}
