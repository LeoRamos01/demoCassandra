# demoCassandra
Projeto simples para testes com Cassandra e Swagger

# Como acessar

Após clone do projeto:

```sh
cd demoCassandra
mvn clean install
java -jar target/demoCassandra-0.0.1-SNAPSHOT.jar
```
Então finalmente acessar pela seguinte URL
http://localhost:8082/swagger-ui.html

#Configurar cassandra com docker

```sh
docker run --name meucassandra -p 9042:9042 -d cassandra
docker exec -it meucassandra bash
cqlsh
```
Uma vez dentro do cassandra dentro do container:

```CQL
CREATE KEYSPACE mykeyspace WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

USE mykeyspace;

CREATE TABLE people ( id INT PRIMARY KEY, fullname TEXT, age INT );

INSERT INTO people (id, fullname, age) VALUES (1, 'Leonardo Ramos', 26);

SELECT * FROM people WHERE id = 1;
```
