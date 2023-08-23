package com.example.jpademo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "album")
public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Column(name = "TITLE")
    private String title;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name = "SINGER_ID")
    private Singer singer;

    public Album(){
        //needed by JPA
    }
    public Album(String title,Date releaseDate){
        this.title=title;
        this.releaseDate=releaseDate;
    }

    public int getVersion() {
        return version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Singer getSinger() {
        return singer;
    }
    @Override
    public String toString() {
        return "Album - Id: " + id + ", Title: " +
               title + ", Release Date: " + releaseDate;
    }
}
