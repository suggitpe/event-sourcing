FROM suggitpe/16.04-orajava8-base

ENV SCALA_VERSION 2.11 KAFKA_VERSION 0.9.0.1
ENV KAFKA_TMP /tmp/kafka.tgz
ENV KAFKA_BIN http://apache.mirror.anlx.net/kafka/"$KAFKA_VERSION"/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION".tgz
ENV KAFKA_HOME /opt/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION"

RUN apt-get update && \
    apt-get -y upgrade && \
    apt-get install -y zookeeper zookeeperd wget supervisor && \
    wget --quiet "$KAFKA_BIN" --output-document "$KAFKA_TMP" && \
    bash -c 'touch "$KAFKA_TMP"' && \
    tar xfz "$KAFKA_TMP" -C /opt && \
    rm "$KAFKA_TMP" && \
    apt-get clean

ADD kafka.conf /etc/supervisor/conf.d/

EXPOSE 2181

CMD ["supervisord", "-n"]
