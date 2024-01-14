<h1 align="center">
  API RESTful para possibilitar a leitura da lista de indicados e vencedores
da categoria Pior Filme do Golden Raspberry Awards.
</h1>

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JDBC](https://spring.io/projects/spring-data-jdbc)
- [H2](https://www.h2database.com)

## Como Executar

- Clonar repositório git:
```
git clone 
```
- Construir o projeto:
```
./mvn clean install
```
- Executar:
```
java -jar ./target/apiTeste-0.0.1-SNAPSHOT.jar
```
- Executar testes Integração:
```
mvn test
```
- testes end-points ( www.postman.com/ ):
```
importar arquivo ./resources/TestApi.postman_collection.json

REPORT
GET list
http://localhost:8080/api/report/list

```

