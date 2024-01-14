package com.teste.apiTeste.infra.gateways;

import com.teste.apiTeste.application.gateways.ProducerGateway;
import com.teste.apiTeste.domain.entity.Producer;
import com.teste.apiTeste.infra.dto.response.ProducerResponse;
import com.teste.apiTeste.infra.dto.request.ProducerResquest;
import com.teste.apiTeste.infra.mapper.ProducersMapper;
import com.teste.apiTeste.infra.persistence.ProducerEntity;
import com.teste.apiTeste.infra.persistence.ProducersRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class ProducerRepositoryGateway implements ProducerGateway {

    private final ProducersRepository producersRepository;
    private final ProducersMapper producersMapper;

    public ProducerRepositoryGateway(ProducersRepository producersRepository, ProducersMapper producersMapper) {
        this.producersRepository = producersRepository;
        this.producersMapper = producersMapper;
    }

    @Override
    public void createManyCostumers(List<Producer> producersDomain) {
        List<ProducerEntity> producerEntities = new ArrayList<>();
        for(Producer producer : producersDomain ){
            ProducerEntity entity = new ProducerEntity();
            entity.setId(producer.id());
            entity.setName(producer.name());
            producerEntities.add(entity);
        }
        this.producersRepository.saveAll(producerEntities);
    }

    @Override
    public List<ProducerEntity> findProducersWinnerFilmsAwards() {
        return producersRepository.findAllProducersWinnerFilmAward();
    }

    @Override
    public List<ProducerResponse> list() {
        return producersMapper.toResponseList(producersRepository.findAll());
    }

    @Override
    public boolean create(ProducerResquest producerResquest) throws Exception {
        var opt = producersRepository.findByName(producerResquest.name());
        if(opt.isPresent()) {
            throw new Exception("Name already in use.");
        }
        ProducerEntity entity = new ProducerEntity();
        entity.setId(UUID.randomUUID());
        entity.setName(producerResquest.name());

        try{
            this.producersRepository.save(entity);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("Producer not create.");
        }
        return true;
    }

    @Override
    public boolean update(ProducerResquest producerResquest, UUID id) throws Exception {
        var opt = producersRepository.findById(id);
        if(opt.isPresent()){
            ProducerEntity entity = opt.get();
            entity.setName(producerResquest.name());
            try{
                this.producersRepository.save(entity);
            }catch (Exception e) {
                log.error(e.getMessage());
                throw new Exception("Producer not updated.");
            }
        }else{
            throw new Exception("Producer not found.");
        }
        return true;
    }

    @Override
    public boolean delete(UUID id) throws Exception {
        var opt = this.producersRepository.findById(id);
        if(opt.isPresent()) {
            try {
                this.producersRepository.deleteById(id);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new Exception("Producer not deleted.");
            }
        } else {
            throw new Exception("Producer not found.");
        }
        return true;
    }

    @Override
    public ProducerResponse show(UUID id) throws Exception {
        var opt = producersRepository.findById(id);
        if(opt.isEmpty()) {
            throw new Exception("Producer not found.");
        }
        ProducerResponse response = producersMapper.toResponse(opt.get());
        return response;
    }
}
