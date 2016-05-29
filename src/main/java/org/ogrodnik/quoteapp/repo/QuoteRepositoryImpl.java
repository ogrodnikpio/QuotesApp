package org.ogrodnik.quoteapp.repo;

import org.ogrodnik.quoteapp.domain.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * Created by Piotr on 28.05.2016.
 */
@Repository
@Transactional
public class QuoteRepositoryImpl implements QuoteRepository{
    @Autowired
    private EntityManager em;

    @Override
    public Quote findById(Long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Quote> criteria = builder.createQuery(Quote.class);
        Root<Quote> member = criteria.from(Quote.class);


        criteria.select(member).where(builder.equal(member.get("id"), id));
        return em.createQuery(criteria).getSingleResult();
    }

    @Override
    public Quote findByDate(Date id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Quote> criteria = builder.createQuery(Quote.class);
        Root<Quote> member = criteria.from(Quote.class);


        criteria.select(member).where(builder.equal(member.get("quoteDate"), id));
        return em.createQuery(criteria).getSingleResult();
    }

    @Override
    public List<Quote> findByQuotes(Date quoteDate, Date quoteDate2, int page, int pageSize) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Quote> criteria = builder.createQuery(Quote.class);
        Root<Quote> member = criteria.from(Quote.class);

        criteria.select(member).where(builder.between(member.<Date>get("quoteDate"), quoteDate,quoteDate2));
        int indexOfFirst = (page-1)*pageSize;
        int indexOfLast = indexOfFirst+pageSize;
        List<Quote> result =em.createQuery(criteria).getResultList();
        if (indexOfFirst >= result.size())
            return null; //Zmienić co ma zwracać jeżeli taka strona już nie istnieje
        if (indexOfLast>result.size())
            result = result.subList(indexOfFirst,result.size());
        else
            result = result.subList(indexOfFirst,indexOfLast);

        return result;
    }
    @Override
    public List<Quote> findByQuotes2(Date quoteDate, Date quoteDate2) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Quote> criteria = builder.createQuery(Quote.class);
        Root<Quote> member = criteria.from(Quote.class);
        criteria.select(member).where(builder.between(member.<Date>get("quoteDate"), quoteDate,quoteDate2));
        return em.createQuery(criteria).getResultList();
    }
}
