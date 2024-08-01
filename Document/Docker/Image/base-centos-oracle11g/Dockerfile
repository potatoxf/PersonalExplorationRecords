FROM potatoxf/base-centos:latest
LABEL authors="potatoxf"
MAINTAINER "potatoxf <potato.xf@qq.com>"

ADD oracle /opt/oracle

RUN chmod -R 755 /opt/oracle && /opt/oracle/script/oracle.sh install

EXPOSE 1521 8080
