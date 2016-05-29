package org.ogrodnik.quoteapp.test;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.ogrodnik.quoteapp.domain.Quote;
import org.ogrodnik.quoteapp.repo.QuoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-persistence.xml", "classpath:/META-INF/spring/applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class QuoteRepositoryTest {

    @Autowired
    private QuoteRepository repository;

    @Test
    public void testFindById() {
        Quote quote = repository.findById(1L);
        Assert.assertEquals(100.0, quote.getQuoteValue());
        return;
    }

    @Test
    public void testFindByDates() {
        Date date1 = new Date(98, 0, 5);
        Date date2 = new Date(98, 0, 20);
        List<Quote> result = repository.findByQuotes(date1, date2, 1, 1);
        Assert.assertEquals(1, result.size());
        result = repository.findByQuotes(date1, date2, 2, 2);
        Assert.assertEquals(2, result.size());
        result = repository.findByQuotes(date1, date2, 2, 7);
        Assert.assertEquals(5, result.size());
        result = repository.findByQuotes(date1, date2, 13, 1);
        Assert.assertNull(result);
        return;
    }

    @Test
    public void testFindByDates2() {
        Date date1 = new Date(98, 0, 5);
        Date date2 = new Date(98, 0, 20);
        List<Quote> result = repository.findByQuotes2(date1, date2);
        Assert.assertEquals(12, result.size());
        date2.setDate(26);
        result = repository.findByQuotes2(date1, date2);
        Assert.assertEquals(16, result.size());
        //result=repository.findByQuotes(date1,date2,1,1);
        //Assert.assertEquals(1,result.size());
        return;
    }


}
