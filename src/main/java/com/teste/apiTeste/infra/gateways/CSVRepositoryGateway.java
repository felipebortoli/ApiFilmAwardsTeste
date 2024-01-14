package com.teste.apiTeste.infra.gateways;

import com.teste.apiTeste.application.gateways.CSVGateway;
import com.teste.apiTeste.domain.entity.FilmAwards;
import com.teste.apiTeste.domain.entity.Producer;
import com.teste.apiTeste.domain.entity.Studio;
import com.teste.apiTeste.infra.mapper.ProducersMapper;
import com.teste.apiTeste.infra.mapper.StudioMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CSVRepositoryGateway implements CSVGateway {

    private final FilmAwardRepositoryGateway filmAwardRepositoryGateway;
    private final ProducerRepositoryGateway producerRepositoryGateway;
    private final StudioRepositoryGateway studioRepositoryGateway;
    private final ProducersMapper producersMapper;
    private final StudioMapper studioMapper;
    Map<String, Producer> producerCache = new ConcurrentHashMap<>();
    Map<String, Studio> studioCache = new ConcurrentHashMap<>();

    public CSVRepositoryGateway(FilmAwardRepositoryGateway filmAwardRepositoryGateway, ProducerRepositoryGateway producerRepositoryGateway, StudioRepositoryGateway studioRepositoryGateway, ProducersMapper producersMapper, StudioMapper studioMapper) {
        this.filmAwardRepositoryGateway = filmAwardRepositoryGateway;
        this.producerRepositoryGateway = producerRepositoryGateway;
        this.studioRepositoryGateway = studioRepositoryGateway;
        this.producersMapper = producersMapper;
        this.studioMapper = studioMapper;
    }


    @Override
    public void parserCSVToDataBase(InputStream inputStream) {
        readCsvParseToH2DataBase(inputStream);
    }

    public void readCsvParseToH2DataBase(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.newFormat(';').withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            Iterable<CSVRecord> csvRecordsList = csvParser.getRecords();

            List<FilmAwards> filmAwardsToSave = new ArrayList<>();
            for (CSVRecord csvRecord : csvRecordsList) {
                List<Producer> producers = addCacheProducer(csvRecord.get("producers"));
                List<Studio> studios = addCacheStudio(csvRecord.get("studios"));

                FilmAwards filmAwards = new FilmAwards(
                        csvRecord.get("year"),
                        csvRecord.get("title"),
                        csvRecord.get("winner"),
                        getProducerFromCache(producers),
                        getStudioFromCache(studios)
                );
                filmAwardsToSave.add(filmAwards);
            }

            producerRepositoryGateway.createManyCostumers(new ArrayList<>(producerCache.values()));
            studioRepositoryGateway.createManyStudios(new ArrayList<>(studioCache.values()));
            filmAwardRepositoryGateway.createManyFilmsAward(filmAwardsToSave);
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    private List<Producer> addCacheProducer(String producersFilmLine) {
        String[] producerNames = producersFilmLine.replaceAll("\\sand\\s", ",").replaceAll(",,", ",").split(",");
        List<Producer> producers = producersMapper.toDomainObj(producerNames);

        for (Producer producer : producers) {
            producerCache.computeIfAbsent(producer.name().strip(), key -> producer);
        }

        return producers;
    }

    private List<Studio> addCacheStudio(String studiosFilmLine) {
        String[] studioNames = studiosFilmLine.replaceAll("\\sand\\s", ",").replaceAll(",,", ",").split(",");
        List<Studio> studios = studioMapper.toDomainObj(studioNames);

        for (Studio studio : studios) {
            studioCache.computeIfAbsent(studio.name(), key -> studio);
        }

        return studios;
    }

    private List<Producer> getProducerFromCache(List<Producer> producersFilmLine) {
        List<Producer> producers = new ArrayList<>();
        for (Producer producer : producersFilmLine) {
            producers.add(producerCache.get(producer.name().strip()));
        }
        return producers;
    }

    private List<Studio> getStudioFromCache(List<Studio> studios) {
        List<Studio> studiosReturn = new ArrayList<>();
        for (Studio studio : studios) {
            studiosReturn.add(studioCache.get(studio.name()));
        }
        return studiosReturn;
    }

}
