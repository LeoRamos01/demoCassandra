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

# Configurar cassandra com docker em cluster

Cluster pai:

```sh
docker run --name boston -p 9044:9042 -e CASSANDRA_CLUSTER_NAME=myCluster -e CASSANDRA_ENDPOINT_SNITCH=GossipingPropertyFileSnitch -e CASSANDRA_DC=datacenter1 -d cassandra
```

Cluster filho:

```sh
docker run --name newyork -e CASSANDRA_SEEDS="$(docker inspect --format='{{ .NetworkSettings.IPAddress }}' boston)" -e CASSANDRA_CLUSTER_NAME=myCluster -e CASSANDRA_ENDPOINT_SNITCH=GossipingPropertyFileSnitch -e CASSANDRA_DC=datacenter2 -d cassandra
```
Uma vez dentro do cassandra dentro do container:

```CQL
CREATE KEYSPACE mykeyspace WITH REPLICATION = {
	'class' : 'NetworkTopologyStrategy',
	'datacenter1' : 3,
	'datacenter2' : 3
};

USE mykeyspace;

CREATE TABLE person ( id INT, fullname TEXT, age INT, PRIMARY KEY (id, fullname) ) WITH CLUSTERING ORDER BY(fullname  DESC); 

INSERT INTO person (id, fullname, age) VALUES (1, 'Leonardo Ramos', 26);

SELECT * FROM people WHERE id = 1;
```

#Monitorar clusters

```sh
docker exec -ti boston nodetool status
```

Lembrando que rodar o Cluster localmente apenas para testes pode consumir muita RAM e pode demorar para os Clusters se comunicarem dependendo da máquina.
