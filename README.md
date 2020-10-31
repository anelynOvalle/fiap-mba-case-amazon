<h1 align="center"> 
  <br>FIAP:  MBA Engenharia de Software - 75AOJ
  <br>
</h1>

<h4 align="center">Services Architecture / API / Mobile Architecture -  Case Amazon</h4>


<p align="center">
  <a href="#arquitetura-do-projeto">Arquitetura do projeto</a> •
  <a href="#funcionalidades-chave">Funcionalidades chave</a> •
  <a href="#como-usar">Como usar</a> •
  <a href="#creditos">Créditos</a> •
  <a href="#relacionado">Relacionado</a> •
  <a href="#licença">Licença</a>
</p>

## Arquitetura do projeto
![screenshot](https://user-images.githubusercontent.com/5009611/97039649-2274bc80-1543-11eb-9815-eb6785874a50.jpg)

## Funcionalidades chave

* JAVA Microservices - Spring Boot.
* Config Manager - Spring Boot.
* Service discovery - Netflix Eureka.
* Message broker - Apache Kafka + Zookeper.
* Postgres Databases.


## Como usar

1 - Suba os bancos de dados com docker-compose:
  - cd services-architecture/databases
  - docker-compose up
  
2 - Suba o Kafka e Zookeeper com docker-compose:
  - cd services-architecture/kafka-broker
  - docker-compose up
  
3 - Crie um tópico "productEventTopic" no kafka. Ex.: docker-compose exec kafka kafka-topics --create --bootstrap-server :9092 --replication-factor 1 --partitions 1 --topic productEventTopic

4 - Importe os microserviços como projeto Maven no IDE de sua escolha e rode na ordem a seguir:
  - configserver
  - eurekaserver
  - product-amazon-service
  - order-amazon-service
  - ticket-amazon-service
  
5 - A documentação das APIs está disponível no Swagger (via docker-compose):
  - cd services-architecture/swagger
  - docker-compose up
  - Acesse: localhost:8082
  

## Créditos

> Anelyn [@anelynOvalle](https://github.com/anelynOvalle) &nbsp;&middot;&nbsp;
> Fábio Alencar [@fabioalencar](https://github.com/fabioalencar) &nbsp;&middot;&nbsp;
> Juliana Medeiros [@jujmor](https://github.com/jujmor) &nbsp;&middot;&nbsp;
> Rafael Barbosa [@rafaelxbs](https://github.com/rafaelxbs)

## Relacionado

* [netflix-eureka](https://github.com/Netflix/eureka) - Eureka is a REST (Representational State Transfer) based service that is primarily used in the AWS cloud for locating services for the purpose of load balancing and failover of middle-tier servers.
* [apache-kafka](https://github.com/apache/kafka) - Apache Kafka is an open-source distributed event streaming platform used by thousands of companies for high-performance data pipelines, streaming analytics, data integration, and mission-critical applications.


## Licença

MIT
