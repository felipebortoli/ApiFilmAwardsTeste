package com.teste.apiTeste.application.usecases;

import com.teste.apiTeste.application.gateways.ReportFilmsAwardsGateway;

public class ReportFilmsAwardsInteractor {

    private final ReportFilmsAwardsGateway filmsAwardsGateway;

    public ReportFilmsAwardsInteractor(ReportFilmsAwardsGateway filmsAwardsGateway){
        this.filmsAwardsGateway = filmsAwardsGateway;
    }

    public Object getProducerLongestGapBetweenConsecutiveAwards() {
        return this.filmsAwardsGateway.getProducerLongestGapBetweenConsecutiveAwards();
    }

}
