# Kafka and Zookeeper docker image builds

## Run all of it
 - docker-compose up -d

## Kafka cluster
 - should run from the box as is
 - `docker-compose build` # build 
 - `docker run -it <container ref> bash` # run a bash shell on a container itself
 - `docker-compose run zookeeper` # run zookeeper in the foreground
 - `docker-compose run kafka` # run kafka in the foreground
 - `docker-compose up -d` # run all in the background

## Kafka stuff
All these are based on being ssh'd onto the kafka container
 - `./kafka-topics.sh --list --zookeeper localhost:2181` # list all the topics
 - `./kafka-topics.sh --zookeeper localhost:2181 --create --replication-factor 1 --partitions 1 --topic test` # create a test topic called test
 - `./kafka-topics.sh --describe --zookeeper localhost:2181 --topic test` # describe the topic

## Test the broker
 - `./kafka-console-producer.sh --broker-list localhost:9092 --topic test` # enter this and write some messages
 - `./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning`
