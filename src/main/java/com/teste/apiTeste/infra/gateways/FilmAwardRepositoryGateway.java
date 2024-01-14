package com.teste.apiTeste.infra.gateways;

import com.teste.apiTeste.application.gateways.FilmAwardsGateway;
import com.teste.apiTeste.domain.entity.FilmAwards;
import com.teste.apiTeste.infra.dto.request.FilmAwardsRequest;
import com.teste.apiTeste.infra.dto.response.FilmAwardsResponse;
import com.teste.apiTeste.infra.mapper.FilmAwardsMapper;
import com.teste.apiTeste.infra.mapper.ProducersMapper;
import com.teste.apiTeste.infra.mapper.StudioMapper;
import com.teste.apiTeste.infra.persistence.FilmAwardsEntity;
import com.teste.apiTeste.infra.persistence.FilmAwardsRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class FilmAwardRepositoryGateway implements FilmAwardsGateway {

    private final FilmAwardsRepository filmAwardsRepository;
    private final FilmAwardsMapper filmAwardsMapper;
    private final ProducerRepositoryGateway producerRepositoryGateway;
    private final ProducersMapper producersMapper;
    private final StudioMapper studioMapper;

    public FilmAwardRepositoryGateway(FilmAwardsRepository filmAwardsRepository, FilmAwardsMapper filmAwardsMapper, ProducerRepositoryGateway producerRepositoryGateway, ProducersMapper producersMapper, StudioMapper studioMapper) {
        this.filmAwardsRepository = filmAwardsRepository;
        this.filmAwardsMapper = filmAwardsMapper;
        this.producerRepositoryGateway = producerRepositoryGateway;
        this.producersMapper = producersMapper;
        this.studioMapper = studioMapper;
    }

    @Override
    public void createManyFilmsAward(List<FilmAwards> filmAwardsDomain) {
        List<FilmAwardsEntity> filmAwardsToSave = new ArrayList<>();
        for (FilmAwards filmAwards : filmAwardsDomain){
            FilmAwardsEntity filmAwardsEntity = filmAwardsMapper.toEntity(filmAwards);
            filmAwardsEntity.setProducers(producersMapper.toEntity(filmAwards.producers()));
            filmAwardsEntity.setStudios(studioMapper.toEntity(filmAwards.studios()));
            filmAwardsToSave.add(filmAwardsEntity);
        }
        filmAwardsRepository.saveAll(filmAwardsToSave);
    }

    @Override
    public List<FilmAwardsResponse> list() {
        return filmAwardsMapper.toResponseList(filmAwardsRepository.findAll());
    }

    @Override
    public boolean create(FilmAwardsRequest filmAwardsRequest) throws Exception {
        var opt = filmAwardsRepository.findByTitle(filmAwardsRequest.title());
        if(opt.isPresent()) {
            throw new Exception("Title film already in use.");
        }
        FilmAwardsEntity entity = new FilmAwardsEntity();
        entity.setId(UUID.randomUUID());
        entity.setTitle(filmAwardsRequest.title());
        entity.setYear(filmAwardsRequest.year());
        entity.setWinner(filmAwardsRequest.winner());
        entity.setProducers(filmAwardsRequest.producers());
        entity.setStudios(filmAwardsRequest.studios());

        try{
            this.filmAwardsRepository.save(entity);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("Film not create.");
        }
        return true;
    }

    @Override
    public boolean update(FilmAwardsRequest filmAwardsRequest, UUID id) throws Exception {
        var opt = filmAwardsRepository.findById(id);
        if(opt.isPresent()){
            FilmAwardsEntity entity  = opt.get();
            entity.setTitle(filmAwardsRequest.title());
            entity.setYear(filmAwardsRequest.year());
            entity.setWinner(filmAwardsRequest.winner());
            entity.setProducers(filmAwardsRequest.producers());
            entity.setStudios(filmAwardsRequest.studios());
            try{
                this.filmAwardsRepository.save(entity);
            }catch (Exception e) {
                log.error(e.getMessage());
                throw new Exception("Film not updated.");
            }
        }else{
            throw new Exception("Film not found.");
        }
        return true;
    }

    @Override
    public boolean delete(UUID id) throws Exception {
        var opt = this.filmAwardsRepository.findById(id);
        if(opt.isPresent()) {
            try {
                this.filmAwardsRepository.deleteById(id);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new Exception("Film not deleted.");
            }
        } else {
            throw new Exception("Film not found.");
        }
        return true;
    }

    @Override
    public FilmAwardsResponse show(UUID id) throws Exception {
        var opt = filmAwardsRepository.findById(id);
        if(opt.isEmpty()) {
            throw new Exception("Film not found.");
        }
        FilmAwardsResponse response = filmAwardsMapper.toResponse(opt.get());
        return response;
    }
}
