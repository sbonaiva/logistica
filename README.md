# Logística
**Stack**

* Java 11
* Spring Boot
* Maven
* Lombok
* Algoritmo de Dijkstra

**Passos para execução**

Requer JRE 11+

* Via JAR

Com o console na pasta target do projeto, executar o comando:

```properties
java -jar app.jar
```  

* Via Docker Compose

Com o console na raíz do projeto, executar o comando:

```properties
docker-compose up -d
```  
 
**Passos para requisição**

* Via Postman

Use a coleção localizada na pasta postman na raíz do projeto.

* Via cURL

Cadastro de mapas

```properties
curl --location --request POST 'http://localhost:8080/mapas' \
--header 'Content-Type: application/json' \
--data-raw '{
    "origem": "A",
    "destino": "B",
    "distancia": 11
}'
```  

Cálculo de rotas

```properties
curl --location --request POST 'http://localhost:8080/rotas' \
--header 'Content-Type: application/json' \
--data-raw '{
    "origem": "A",
    "destino": "B",
    "autonomia": 11,
    "valorLitro": 2
}'
```
