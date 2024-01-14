package com.teste.apiTeste.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProducersRepository  extends JpaRepository<ProducerEntity, UUID> {

    @Query("SELECT producer FROM ProducerEntity producer JOIN FETCH producer.filmsAwards films Where films.winner=true")
    List<ProducerEntity> findAllProducersWinnerFilmAward();

    @Query("SELECT producer FROM ProducerEntity producer  Where producer.name=:name")
    Optional<ProducerEntity> findByName(String name);


//    @Query("SELECT producer FROM ProducerEntity producer  Where producer.filmsAwards=:uuidsList")
//    Optional<ProducerEntity> findByIds(List<UUID> uuidsList);
}
