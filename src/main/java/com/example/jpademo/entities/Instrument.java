package com.example.jpademo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "instrument")
public class Instrument implements Serializable {
    @Id
    @Column(name = "INSTRUMENT_ID")
    private String instrumentId;

    @ManyToMany
    @JoinTable(name = "singer_instrument",
            joinColumns = @JoinColumn(name = "INSTRUMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "SINGER_ID"))
    private Set<Singer> singers = new HashSet<>();

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public void setSingers(Set<Singer> singers) {
        this.singers = singers;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public Set<Singer> getSingers() {
        return singers;
    }
    @Override
    public String toString() {
        return "Instrument: " + getInstrumentId();
    }
}
