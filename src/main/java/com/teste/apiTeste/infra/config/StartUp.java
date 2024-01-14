package com.teste.apiTeste.infra.config;

import com.teste.apiTeste.infra.gateways.CSVRepositoryGateway;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;


@Component
public class StartUp  implements ApplicationRunner {

   private final CSVRepositoryGateway csvRepositoryGateway;

    public StartUp(CSVRepositoryGateway csvRepositoryGateway) {
        this.csvRepositoryGateway = csvRepositoryGateway;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("movielist.csv");
        csvRepositoryGateway.parserCSVToDataBase(inputStream);
    }
}
