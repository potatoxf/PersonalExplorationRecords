FROM potatoxf/base-centos:latest
LABEL authors="potatoxf"
MAINTAINER "potatoxf <potato.xf@qq.com>"

USER root

RUN yum install -y java-1.8.0-openjdk-devel &&\
echo -En '#!'>/etc/profile.d/init-jdk.sh &&\
echo "/bin/bash">>/etc/profile.d/init-jdk.sh &&\
echo "export JAVA_HOME=\$(dirname \$(dirname \$(readlink -f \$(which java))))">> /etc/profile.d/init-jdk.sh &&\
echo "export JAVA_HOME=\${JAVA_HOME/\/jre/}">> /etc/profile.d/init-jdk.sh &&\
echo "export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar">> /etc/profile.d/init-jdk.sh &&\
chmod 755 /etc/profile.d/init-jdk.sh
