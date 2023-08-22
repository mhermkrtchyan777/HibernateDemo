package com.example.hibernatedemo;

import com.example.hibernatedemo.config.AppConfig;
import com.example.hibernatedemo.dao.SingerDao;
import com.example.hibernatedemo.entities.Album;
import com.example.hibernatedemo.entities.Singer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class SpringHibernateDemo {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);

        //listSingersWithAlbum(singerDao.findAllWithAlbum());
        Singer singer=singerDao.findById(2L);
        System.out.println(singer.toString());
        ctx.close();
    }

    private static void listSingersWithAlbum(List<Singer> singers) {
        for (Singer singer : singers) {
            System.out.println(singer.toString());
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println("\t" + album.toString());
                }
            }
        }
    }
}
