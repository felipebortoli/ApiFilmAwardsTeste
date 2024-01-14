package com.teste.apiTeste.infra.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "studio")
public class StudioEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "studios")
    List<FilmAwardsEntity> filmsAwards = new ArrayList<>();
}
