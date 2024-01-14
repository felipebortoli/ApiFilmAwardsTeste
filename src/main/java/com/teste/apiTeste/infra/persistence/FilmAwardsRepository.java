package com.teste.apiTeste.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FilmAwardsRepository extends JpaRepository<FilmAwardsEntity, UUID> {

    @Query("SELECT film FROM FilmAwardsEntity film LEFT JOIN FETCH film.producers Where film.winner=true")
    List<FilmAwardsEntity> findAllAndFilmsWinners();


    @Query("SELECT film FROM FilmAwardsEntity film  Where film.title=:title")
    Optional<FilmAwardsEntity> findByTitle(String title);

    @Query("SELECT film.id FROM FilmAwardsEntity film ")
    List<UUID> findAllUUIDs();
}
