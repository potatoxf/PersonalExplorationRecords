# 使用Ubuntu官方镜像作为基础镜像
FROM ubuntu:latest
LABEL authors="potatoxf"
MAINTAINER "potatoxf <potato.xf@qq.com>"

# 设置环境变量,所有操作非交互式
ENV DEBIAN_FRONTEND noninteractive
ENV LANG zh_CN.UTF-8
ENV LOCALE Asia/Shanghai

USER root

RUN \
echo "base-ubuntu">/etc/hostname &&\
echo "#sources list">/etc/apt/sources.list &&\
echo "deb http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "# deb-src http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "deb http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-updates main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "# deb-src http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-updates main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "deb http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-backports main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "# deb-src http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-backports main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "# deb http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-proposed main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "# deb-src http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-proposed main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "deb http://us.archive.ubuntu.com/ubuntu/ focal main restricted">>/etc/apt/sources.list &&\
echo "deb http://us.archive.ubuntu.com/ubuntu/ focal-updates main restricted">>/etc/apt/sources.list &&\
echo "deb http://us.archive.ubuntu.com/ubuntu/ focal universe">>/etc/apt/sources.list &&\
echo "deb http://us.archive.ubuntu.com/ubuntu/ focal-updates universe">>/etc/apt/sources.list &&\
echo "deb http://us.archive.ubuntu.com/ubuntu/ focal multiverse">>/etc/apt/sources.list &&\
echo "deb http://us.archive.ubuntu.com/ubuntu/ focal-updates multiverse">>/etc/apt/sources.list &&\
echo "deb http://us.archive.ubuntu.com/ubuntu/ focal-backports main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "deb http://archive.ubuntu.com/ubuntu/ focal main restricted">>/etc/apt/sources.list &&\
echo "deb http://archive.ubuntu.com/ubuntu/ focal-updates main restricted">>/etc/apt/sources.list &&\
echo "deb http://archive.ubuntu.com/ubuntu/ focal universe">>/etc/apt/sources.list &&\
echo "deb http://archive.ubuntu.com/ubuntu/ focal-updates universe">>/etc/apt/sources.list &&\
echo "deb http://archive.ubuntu.com/ubuntu/ focal multiverse">>/etc/apt/sources.list &&\
echo "deb http://archive.ubuntu.com/ubuntu/ focal-updates multiverse">>/etc/apt/sources.list &&\
echo "deb http://archive.ubuntu.com/ubuntu/ focal-backports main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "deb http://security.ubuntu.com/ubuntu/ focal-security main restricted universe multiverse">>/etc/apt/sources.list &&\
echo "# deb-src http://security.ubuntu.com/ubuntu/ focal-security main restricted universe multiverse">>/etc/apt/sources.list &&\
apt-get update &&\
apt-get install -y language-pack-zh-hans &&\
update-locale LANG=$LANG &&\
update-locale LANGUAGE=$LANG &&\
update-locale LC_ALL=$LANG &&\
apt-get install -y fonts-wqy-zenhei &&\
apt-get install -y tzdata &&\
ln -snf /usr/share/zoneinfo/$LOCALE /etc/localtime &&\
echo "$LOCALE">/etc/timezone &&\
dpkg-reconfigure -f noninteractive tzdata &&\
apt-get install -y ntp &&\
echo "NTP server(s)">/etc/ntp.conf &&\
echo "pool.ntp.org">>/etc/ntp.conf &&\
apt-get install -y curl wget nmap iproute2&&\
apt-get install -y tar vim &&\
apt-get install -y openssh* net-tools lsof telnet passwd &&\
mkdir /var/run/sshd &&\
sed -i "s/#*PermitRootLogin.*/PermitRootLogin yes/" /etc/ssh/sshd_config &&\
echo "LANG=zh_CN.UTF-8" > /etc/locale.conf &&\
echo -n "#!">/etc/init.d/start-init.sh &&\
echo "/bin/bash">>/etc/init.d/start-init.sh &&\
echo "export LANG=zh_CN.UTF-8">> /etc/init.d/start-init.sh &&\
echo "alias ls='ls --color=auto'">> /etc/init.d/start-init.sh &&\
echo "alias ll='ls -al'">> /etc/init.d/start-init.sh &&\
echo "/usr/sbin/sshd -D">> /etc/init.d/start-init.sh &&\
chmod 755 /etc/init.d/start-init.sh &&\
update-rc.d start-init.sh defaults 90 &&\
echo 'root:root' | chpasswd &&\
apt-get clean

# 指定容器运行时监听的端口
EXPOSE 22

# 启动SSH服务
ENTRYPOINT ["/etc/init.d/start-init.sh"]