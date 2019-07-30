FROM suggitpe/19.04-openjdk11-base

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && \
    apt-get install -y wget supervisor && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get clean

ENV ZOOKEEPER_VERSION=3.4.14
ENV ZOOKEEPER_HOME=/opt/zookeeper-${ZOOKEEPER_VERSION}
ENV ZOOKEEPER_DOWNLOAD_URL=http://mirrors.ukfast.co.uk/sites/ftp.apache.org/zookeeper/zookeeper-${ZOOKEEPER_VERSION}/zookeeper-${ZOOKEEPER_VERSION}.tar.gz
ENV ZOOKEEPER_TMP=/tmp/zookeeper-${ZOOKEEPER_VERSION}.tgz

RUN echo "Installing Zookeeper from ${ZOOKEEPER_DOWNLOAD_URL}" && \
	wget -q ${ZOOKEEPER_DOWNLOAD_URL} -O ${ZOOKEEPER_TMP} && \
	ls -l ${ZOOKEEPER_TMP} && \
	tar --extract --file=${ZOOKEEPER_TMP} --gzip --directory=/opt && \
	rm ${ZOOKEEPER_TMP}

ADD zoo.cfg ${ZOOKEEPER_HOME}/conf
ADD start-zookeeper.sh /usr/bin/start-zookeeper.sh
ADD zookeeper-supervisord.conf /etc/supervisor/conf.d/
RUN ls -alF ${ZOOKEEPER_HOME}

EXPOSE 2181

CMD ["supervisord", "-n"]

