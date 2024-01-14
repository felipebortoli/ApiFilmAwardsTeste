package com.teste.apiTeste.application.gateways;

import java.io.InputStream;

public interface CSVGateway {

    void parserCSVToDataBase(InputStream inputStream);
}
