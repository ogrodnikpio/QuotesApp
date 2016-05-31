package org.ogrodnik.quoteapp.mvc;

import org.ogrodnik.quoteapp.domain.Quote;
import org.ogrodnik.quoteapp.domain.QuoteLongDate;
import org.ogrodnik.quoteapp.repo.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Piotr on 29.05.2016.
 */
@Controller
@RequestMapping(value = "/")
public class TestController {
    @Autowired
    private QuoteRepository repository;


    @RequestMapping(method=RequestMethod.GET)
    public String displaySortedMembers(Model model)
    {

        model.addAttribute("datesWrapper", new DatesWrapper());
        return "index";
    }

    @RequestMapping(value = "/api/comment", method = RequestMethod.GET)
    @ResponseBody
    public String add(@Valid @RequestBody DatesWrapper comment) {
        return comment.getDate();
    }
    @RequestMapping(method=RequestMethod.POST)
    public String registerNewMember(@Valid @ModelAttribute("datesWrapper") DatesWrapper newMember, BindingResult result, Model model) throws Exception
    {
        if (!result.hasErrors()) {
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date dateFormatted = formatter.parse(newMember.getDate());
            Date dateFormatted2 = formatter.parse(newMember.getDate2());
            List<Quote> abc;
            if (dateFormatted.getTime()<dateFormatted2.getTime())
                abc =repository.findByQuotes2(dateFormatted,dateFormatted2);
            else
                abc = repository.findByQuotes2(dateFormatted2, dateFormatted);
            List<QuoteLongDate> wynik = new ArrayList<QuoteLongDate>();
            for (Quote q : abc){
                QuoteLongDate adding = new QuoteLongDate(q);
                wynik.add(adding);
            }
            model.addAttribute("quotes",wynik);
            return "index";
            //return "redirect:/quotes/date?date="+newMember.getDate()+"&date2="+newMember.getDate2();
        }
        else {
            return "index";
        }
    }
}