package com.example.springdatajpa;

import com.example.jpademo.entities.Singer;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("springJpaSingerService")
@Transactional
public class SingerServiceImpl implements SingerService {
    private final SingerRepository singerRepository;

    @Autowired
    public SingerServiceImpl(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return Lists.newArrayList(singerRepository.findAll());
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        return singerRepository.findByFirstName(firstName);
    }

    @Override
    public List<Singer> findByFirstNameAndLastName(String firstName, String lastName) {
        return singerRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
