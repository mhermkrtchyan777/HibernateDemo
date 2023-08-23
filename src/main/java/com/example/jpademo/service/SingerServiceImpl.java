package com.example.jpademo.service;

import com.example.jpademo.entities.Singer;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("jpaSingerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {
    final static String ALL_SINGER_NATIVE_QUERY =
            "select id, first_name, last_name, birth_date, version from singer";
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return entityManager.createNamedQuery(Singer.FIND_ALL, Singer.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllWithAlbum() {
        return entityManager.createNamedQuery(Singer.FIND_ALL_WITH_ALBUM, Singer.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Long id) {
        return entityManager.createNamedQuery(Singer.FIND_SINGER_BY_ID, Singer.class).
                setParameter("id", id).getSingleResult();
    }

    @Override
    public Singer save(Singer singer) {
        if(singer.getId()==null){
            System.out.println("Inserting new singer");
            entityManager.persist(singer);
        }else {
            entityManager.merge(singer);
            System.out.println("Updating existing singer");
        }
        System.out.println("Singer saved with id: "+singer.getId());
        return singer;
    }

    @Override
    public void delete(Singer singer) {
        Singer mergedSinger= entityManager.merge(singer);
        entityManager.remove(mergedSinger);
        System.out.println("Singer with id: "+singer.getId()+" deleted successfully");
    }

    @Override
    public List<Singer> findAllByNativeQuery() {
        return entityManager.createNativeQuery(ALL_SINGER_NATIVE_QUERY, "singerResult").getResultList();
    }
}
