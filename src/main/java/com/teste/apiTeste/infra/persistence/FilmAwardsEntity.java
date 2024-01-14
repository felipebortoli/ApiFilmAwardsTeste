package com.teste.apiTeste.infra.persistence;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Lazy;


import java.time.Year;
import java.util.*;


@Data
@Entity
@Table(name = "film_awards")
public class FilmAwardsEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int year;

    private boolean winner;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "films_producers",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id"))
    private List<ProducerEntity> producers = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "films_studios",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "studio_id"))
    private List<StudioEntity> studios = new ArrayList<>();

}
