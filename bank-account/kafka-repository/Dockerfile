FROM suggitpe/18.04-orajava8-base

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && \
    apt-get install -y wget supervisor && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get clean

## Add zookeeper to the image
ENV ZOOKEEPER_VERSION=3.4.12
ENV ZOOKEEPER_HOME=/opt/zookeeper-${ZOOKEEPER_VERSION}
ENV ZOOKEEPER_DOWNLOAD_URL=http://mirrors.ukfast.co.uk/sites/ftp.apache.org/zookeeper/zookeeper-${ZOOKEEPER_VERSION}/zookeeper-${ZOOKEEPER_VERSION}.tar.gz
ENV ZOOKEEPER_TMP=/tmp/zookeeper-${ZOOKEEPER_VERSION}.tgz

RUN echo "Installing Zookeeper from ${ZOOKEEPER_DOWNLOAD_URL}" && \
	wget -q ${ZOOKEEPER_DOWNLOAD_URL} -O ${ZOOKEEPER_TMP} && \
	ls -l ${ZOOKEEPER_TMP} && \
	tar --extract --file=${ZOOKEEPER_TMP} --gzip --directory=/opt && \
	rm ${ZOOKEEPER_TMP}
ADD conf/zoo.cfg ${ZOOKEEPER_HOME}/conf
RUN ls -alF ${ZOOKEEPER_HOME}

## Add Kafka to the image
ENV SCALA_VERSION 2.12
ENV KAFKA_VERSION 1.1.0
ENV KAFKA_HOME /opt/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION"
ENV KAFKA_DOWNLOAD_URL http://apache.mirrors.nublue.co.uk/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz
env KAFKA_TMP /tmp/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION".tgz

RUN echo "Installing Kafka from ${KAFKA_DOWNLOAD_URL}" && \
	wget -q $KAFKA_DOWNLOAD_URL -O ${KAFKA_TMP} && \
	tar --extract --file ${KAFKA_TMP} --gzip -C /opt && \
	rm ${KAFKA_TMP}
