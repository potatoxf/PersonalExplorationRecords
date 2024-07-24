FROM centos:centos7.9.2009
LABEL authors="potatoxf"
MAINTAINER "potatoxf <potato.xf@qq.com>"

USER root

RUN \
echo "base-centos">/etc/hostname &&\
curl -o /etc/yum.repos.d/CentOS-Base.repo https://mirrors.aliyun.com/repo/Centos-7.repo &&\
yum clean all && yum makecache yum update &&\
yum install -y kde-l10n-Chinese glibc-common &&\
localedef -c -f UTF-8 -i zh_CN zh_CN.utf8 &&\
echo "">/etc/locale.conf &&\
echo "LC_ALL=zh_CN.utf8">>/etc/locale.conf &&\
echo "LANG=zh_CN.utf8">> /etc/locale.conf &&\
echo -En '#!'>/etc/profile.d/locale.sh &&\
echo "/bin/bash">>/etc/profile.d/locale.sh &&\
echo "export LC_ALL=zh_CN.utf8">> /etc/profile.d/locale.sh &&\
echo "export LANG=zh_CN.utf8">> /etc/profile.d/locale.sh &&\
chmod 755 /etc/profile.d/locale.sh &&\
yum install -y tzdata &&\
ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime &&\
echo "Asia/Shanghai">/etc/timezone &&\
yum install -y ntpdate &&\
ntpdate cn.pool.ntp.org &&\
yum install -y coreutils tar vim less &&\
yum install -y curl wget nmap iproute net-tools&&\
yum install -y openssh* openssl lsof telnet passwd &&\
mkdir /var/run/sshd &&\
ssh-keygen -t rsa -f /etc/ssh/ssh_host_rsa_key -N '' -q &&\
ssh-keygen -t ecdsa -f /etc/ssh/ssh_host_ecdsa_key -N '' -q &&\
ssh-keygen -t ed25519 -f /etc/ssh/ssh_host_ed25519_key -N '' -q &&\
sed -i "s/#*PermitRootLogin.*/PermitRootLogin yes/" /etc/ssh/sshd_config &&\
echo -En '#!'>/etc/profile.d/start-init.sh &&\
echo "/bin/bash">>/etc/profile.d/start-init.sh &&\
echo "alias ls='ls --color=auto'">> /etc/profile.d/start-init.sh &&\
echo "alias ll='ls -al'">> /etc/profile.d/start-init.sh &&\
echo "/usr/sbin/sshd -D">> /etc/profile.d/start-init.sh &&\
chmod 755 /etc/profile.d/start-init.sh &&\
echo 'root:root' | chpasswd &&\
yum clean all

# 指定容器运行时监听的端口
EXPOSE 22

# 启动SSH服务
ENTRYPOINT ["/etc/profile.d/start-init.sh"]