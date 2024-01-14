package com.teste.apiTeste.application.usecases;

import com.teste.apiTeste.application.gateways.CSVGateway;

import java.io.InputStream;

public class CSVInteractor {

    private final CSVGateway csvGateway;

    public CSVInteractor(CSVGateway csvGateway) {
        this.csvGateway = csvGateway;
    }

    public void parserCSVToDataBase(InputStream inputStream){
        csvGateway.parserCSVToDataBase(inputStream);
    }
}
