package com.example.jpademo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "singer")
@NamedQueries({
        @NamedQuery(name = Singer.FIND_ALL, query = "select s from Singer s"),
        @NamedQuery(name = Singer.FIND_SINGER_BY_ID,
                query = """
                        select distinct s from Singer s
                        left join fetch s.albums a
                        left join fetch s.instruments i
                        where s.id=:id
                        """),
        @NamedQuery(name = Singer.FIND_ALL_WITH_ALBUM,
                query = """
                        select distinct s from Singer s
                        left join fetch s.albums a
                        left join fetch s.instruments i
                        """)
})
@SqlResultSetMapping(name = "singerResult",
        entities = @EntityResult(entityClass = Singer.class))
public class Singer implements Serializable {
    public static final String FIND_ALL = "Singer.findAll";//?????
    public static final String FIND_SINGER_BY_ID = "Singer.findById";
    public static final String FIND_ALL_WITH_ALBUM = "Singer.findAllWithAlbum";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Album> albums = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "singer_instrument",
            joinColumns = @JoinColumn(name = "SINGER_ID"), inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
    private Set<Instrument> instruments = new HashSet<>();

    public void setInstruments(Set<Instrument> instruments) {
        this.instruments = instruments;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getVersion() {
        return version;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public Set<Instrument> getInstruments() {
        return instruments;
    }

    @Override
    public String toString() {
        return "Singer - Id: " + id + ", First name: " + firstName
               + ", Last name: " + lastName + ", Birthday: " + birthDate;
    }
}
