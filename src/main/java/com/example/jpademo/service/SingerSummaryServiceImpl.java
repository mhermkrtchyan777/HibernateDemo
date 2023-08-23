package com.example.jpademo.service;

import com.example.jpademo.view.SingerSummary;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SingerSummaryServiceImpl implements SingerSummaryService {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<SingerSummary> findAll() {
        return entityManager.createQuery(
                "select new com.example.jpademo.view.SingerSummary("
                + "s.firstName, s.lastName, a.title) from Singer s "
                + "left join s.albums a "
                + "where a.releaseDate = (select max(a2.releaseDate) "
                + "from Album a2 where a2.singer.id = s.id)",
                SingerSummary.class).getResultList();
    }
}
