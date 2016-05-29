package org.ogrodnik.quoteapp.mvc;


import org.ogrodnik.quoteapp.domain.Quote;
import org.ogrodnik.quoteapp.repo.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Piotr on 27.05.2016.
 */
@Controller
@RequestMapping("/quotes")
public class QuoteController {
    @Autowired
    private QuoteRepository repository;

    /*@RequestMapping(value = "/{value}",method = RequestMethod.GET,produces="application/json")
    @ResponseBody
    public Quote lookupQuoteById(@PathVariable("value") Long value)
    {
        Quote abc = repository.findById(value);
        return abc;
    }*/

    @RequestMapping(value = "/{value}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Quote lookupQuoteByDate(@PathVariable("value") String value) throws Exception {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse(value);
        Quote abc = repository.findByDate(date);
        return abc;
    }

    @RequestMapping(value = "/date", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Quote> lookupQuoteByDates(@RequestParam("date") String date, @RequestParam("date2") String date2) throws Exception {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateFormatted = formatter.parse(date);
        Date dateFormatted2 = formatter.parse(date2);
        List<Quote> abc;
        if (dateFormatted.getTime()<dateFormatted2.getTime())
            abc =repository.findByQuotes2(dateFormatted,dateFormatted2);
        else
            abc = repository.findByQuotes2(dateFormatted2, dateFormatted);
        return abc;
    }
}
