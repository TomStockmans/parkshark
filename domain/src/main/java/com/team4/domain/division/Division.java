package com.team4.domain.division;

import javax.persistence.*;

@Entity
@Table(name = "DIVISION")
public class Division {

    @Id
    @SequenceGenerator(name = "division_seq", sequenceName = "DIVISION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "division_seq")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ORIGINAL_NAME")
    private String originalName;

    @Embedded
    private Director director;


    public Division(String name, String originalName, Director director) {
        this.name = name;
        this.originalName = originalName;
        this.director = director;
    }

    public Division() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public Director getDirector() {
        return director;
    }
}
