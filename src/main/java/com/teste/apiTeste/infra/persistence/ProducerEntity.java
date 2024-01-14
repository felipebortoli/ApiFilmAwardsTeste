package com.teste.apiTeste.infra.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "producer")
public class ProducerEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "producers")
    List<FilmAwardsEntity> filmsAwards = new ArrayList<>();


}
