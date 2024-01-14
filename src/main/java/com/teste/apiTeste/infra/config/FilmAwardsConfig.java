package com.teste.apiTeste.infra.config;

import com.teste.apiTeste.application.gateways.FilmAwardsGateway;
import com.teste.apiTeste.application.gateways.ReportFilmsAwardsGateway;
import com.teste.apiTeste.application.gateways.ProducerGateway;
import com.teste.apiTeste.application.usecases.FilmAwardsInteractor;
import com.teste.apiTeste.application.usecases.ReportFilmsAwardsInteractor;
import com.teste.apiTeste.application.usecases.ProducerInteractor;
import com.teste.apiTeste.infra.gateways.*;
import com.teste.apiTeste.infra.mapper.FilmAwardsMapper;
import com.teste.apiTeste.infra.mapper.ProducersMapper;
import com.teste.apiTeste.infra.mapper.StudioMapper;
import com.teste.apiTeste.infra.persistence.FilmAwardsRepository;
import com.teste.apiTeste.infra.persistence.ProducersRepository;
import com.teste.apiTeste.infra.persistence.StudioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilmAwardsConfig {

    @Bean
    ReportFilmsAwardsInteractor createReportFilmsAwardsInteractor(ReportFilmsAwardsGateway filmsAwardsGateway){
        return new ReportFilmsAwardsInteractor(filmsAwardsGateway);
    }

    @Bean
    FilmAwardsInteractor createFilmAwardsInteractor(FilmAwardsGateway filmAwardsGateway){
        return new FilmAwardsInteractor(filmAwardsGateway);
    }

    @Bean
    ProducerInteractor createProducerInteractor(ProducerGateway producerGateway){
        return new ProducerInteractor(producerGateway);
    }

    @Bean
    FilmAwardRepositoryGateway filmAwardRepositoryGateway(FilmAwardsRepository filmAwardsRepository, FilmAwardsMapper filmAwardsMapper,ProducerRepositoryGateway producerRepositoryGateway,ProducersMapper producersMapper, StudioMapper studioMapper){
        return new FilmAwardRepositoryGateway(filmAwardsRepository, filmAwardsMapper, producerRepositoryGateway, producersMapper, studioMapper);
    }

    @Bean
    ReportFilmAwardsRepositoryGateway reportFilmAwardsRepositoryGateway(ProducerRepositoryGateway producerRepositoryGateway){
        return new ReportFilmAwardsRepositoryGateway(producerRepositoryGateway);
    }

    @Bean
    CSVRepositoryGateway CSVGateway(FilmAwardRepositoryGateway filmAwardsRepositoryGateway, ProducerRepositoryGateway producerRepositoryGateway, StudioRepositoryGateway studioRepositoryGateway, ProducersMapper producersMapper, StudioMapper studioMapper){
        return new CSVRepositoryGateway(filmAwardsRepositoryGateway,producerRepositoryGateway, studioRepositoryGateway, producersMapper, studioMapper);
    }

    @Bean
    StudioRepositoryGateway studioRepositoryGateway(StudioRepository studioRepository){
        return new StudioRepositoryGateway(studioRepository);
    }

    @Bean
    ProducerRepositoryGateway producerRepositoryGateway(ProducersRepository producersRepository, ProducersMapper producersMapper){
        return new ProducerRepositoryGateway(producersRepository,producersMapper);
    }

    @Bean
    FilmAwardsMapper filmAwardsMapper() {
        return new FilmAwardsMapper();
    }

    @Bean
    StudioMapper studioMapper() {
        return new StudioMapper();
    }

    @Bean
    ProducersMapper producersMapper() {
        return new ProducersMapper();
    }

}
