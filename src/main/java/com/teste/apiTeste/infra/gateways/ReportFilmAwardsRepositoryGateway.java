package com.teste.apiTeste.infra.gateways;

import com.teste.apiTeste.application.gateways.ReportFilmsAwardsGateway;
import com.teste.apiTeste.infra.dto.IntervalYearWinnerAwards;
import com.teste.apiTeste.infra.dto.response.ReportFilmAwardInfoResponse;
import com.teste.apiTeste.infra.dto.response.ReportFilmAwardResponse;


import com.teste.apiTeste.infra.persistence.FilmAwardsEntity;
import com.teste.apiTeste.infra.persistence.ProducerEntity;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReportFilmAwardsRepositoryGateway implements ReportFilmsAwardsGateway {

    private final ProducerRepositoryGateway producerRepositoryGateway;


    public ReportFilmAwardsRepositoryGateway(ProducerRepositoryGateway producerRepositoryGateway) {
        this.producerRepositoryGateway = producerRepositoryGateway;
    }

    @Override
    public ReportFilmAwardResponse getProducerLongestGapBetweenConsecutiveAwards() {
        List<ProducerEntity> producerEntities = producerRepositoryGateway.findProducersWinnerFilmsAwards();
        List<IntervalYearWinnerAwards> intervals = calculateIntervals(producerEntities);

        Map<Integer, List<IntervalYearWinnerAwards>> mapCompareYear = intervals.stream()
                .collect(Collectors.groupingBy(IntervalYearWinnerAwards::getInterval));

        ReportFilmAwardResponse response = new ReportFilmAwardResponse();
        response.setMin(createReportList(mapCompareYear, Comparator.reverseOrder()));
        response.setMax(createReportList(mapCompareYear, Comparator.naturalOrder()));

        return response;
    }

    private List<IntervalYearWinnerAwards> calculateIntervals(List<ProducerEntity> producerEntities) {
        return producerEntities.stream()
                .filter(producerEntity -> producerEntity.getFilmsAwards().size() > 1)
                .flatMap(this::calculateIntervalsForProducer)
                .collect(Collectors.toList());
    }

    private Stream<IntervalYearWinnerAwards> calculateIntervalsForProducer(ProducerEntity producerEntity) {
        List<Integer> years = producerEntity.getFilmsAwards().stream()
                .map(FilmAwardsEntity::getYear)
                .sorted()
                .collect(Collectors.toList());

        return IntStream.range(0, years.size() - 1)
                .mapToObj(i -> {
                    int currentYear = years.get(i);
                    int nextYear = years.get(i + 1);

                    IntervalYearWinnerAwards intervalYearWinnerAwards = new IntervalYearWinnerAwards();
                    intervalYearWinnerAwards.setName(producerEntity.getName());
                    intervalYearWinnerAwards.setFirstYear(currentYear);
                    intervalYearWinnerAwards.setLastYear(nextYear);
                    intervalYearWinnerAwards.setIntervalYearsHighestValue();

                    return intervalYearWinnerAwards;
                });
    }

    private List<ReportFilmAwardInfoResponse> createReportList(Map<Integer, List<IntervalYearWinnerAwards>> mapCompareYear, Comparator<Integer> comparator) {
        return mapCompareYear.entrySet().stream()
                .max(Map.Entry.comparingByKey(comparator))
                .map(entry -> entry.getValue().stream()
                        .map(info -> new ReportFilmAwardInfoResponse(
                                info.getName(),
                                info.getInterval(),
                                info.getFirstYear(),
                                info.getLastYear()))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

}
