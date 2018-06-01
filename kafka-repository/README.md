# Kafka and Zookeeper docker image builds

## Run all of it
 - docker-compose up -d

## Zookeeper
 - should run from the box as is
 - `docker build -t suggitpe/zookeeper zookeeper`
 - `docker run --detach=true --publish=2181:2181 --net=host suggitpe/zookeeper`
 - `docker run --detach=true --publish=9092:9092 --net=host suggitpe/kafka`
