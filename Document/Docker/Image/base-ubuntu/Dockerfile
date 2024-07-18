# 使用Ubuntu官方镜像作为基础镜像
FROM ubuntu:latest
LABEL authors="potatoxf"
MAINTAINER "potatoxf <potato.xf@qq.com>"
RUN \
echo "#aliyun">/etc/apt/sources.list &&\
echo "deb https://mirrors.aliyun.com/ubuntu/ focal main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "deb-src https://mirrors.aliyun.com/ubuntu/ focal main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "deb https://mirrors.aliyun.com/ubuntu/ focal-security main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "deb-src https://mirrors.aliyun.com/ubuntu/ focal-security main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "deb https://mirrors.aliyun.com/ubuntu/ focal-updates main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "deb-src https://mirrors.aliyun.com/ubuntu/ focal-updates main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "deb https://mirrors.aliyun.com/ubuntu/ focal-backports main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "deb-src https://mirrors.aliyun.com/ubuntu/ focal-backports main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "deb http://archive.ubuntu.com/ubuntu/ focal main restricted">>/etc/apt/sources.list &&\
echo "deb http://archive.ubuntu.com/ubuntu/ focal-updates main restricted">>/etc/apt/sources.list &&\
echo "deb http://archive.ubuntu.com/ubuntu/ focal universe">>/etc/apt/sources.list &&\
echo "deb http://archive.ubuntu.com/ubuntu/ focal-updates universe">>/etc/apt/sources.list &&\
echo "deb http://archive.ubuntu.com/ubuntu/ focal multiverse">>/etc/apt/sources.list &&\
echo "deb http://archive.ubuntu.com/ubuntu/ focal-updates multiverse">>/etc/apt/sources.list &&\
echo "deb http://archive.ubuntu.com/ubuntu/ focal-backports main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "deb http://security.ubuntu.com/ubuntu/ focal-security main restricted">>/etc/apt/sources.list &&\
echo "deb http://security.ubuntu.com/ubuntu/ focal-security universe">>/etc/apt/sources.list &&\
echo "deb http://security.ubuntu.com/ubuntu/ focal-security multiverse">>/etc/apt/sources.list &&\
apt-get clean && apt-get autoremove --purge && apt-get update &&\
apt-get install -y ntpdate &&\
ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime &&\
ntpdate pool.ntp.org &&\
apt-get install -y language-pack-zh-hans &&\
echo "#!/bin/bash"> /etc/init.d/start-init.sh &&\
echo "export LANG=zh_CN.UTF-8">> /etc/init.d/start-init.sh &&\
echo "alias ls='ls --color=auto'">> /etc/init.d/start-init.sh &&\
echo "alias ll='ls -al'">> /etc/init.d/start-init.sh &&\
chmod 755 /etc/init.d/start-init.sh &&\
update-rc.d start-init.sh defaults 90 &&\
apt-get install -y iproute2 &&\
apt-get install -y nmap &&\
apt-get install -y curl &&\
apt-get install -y wget &&\
apt-get install -y tar &&\
apt-get install -y vim &&\
apt-get install -y openssh-server &&\
sed -i "s/#*PermitRootLogin.*/PermitRootLogin yes/" /etc/ssh/sshd_config &&\
mkdir /var/run/sshd && service ssh start &&\
echo 'root:root' | chpasswd

# 指定容器运行时监听的端口
EXPOSE 22

# 启动SSH服务
CMD ["/usr/sbin/sshd", "-D"]
ENTRYPOINT ["top", "-b"]