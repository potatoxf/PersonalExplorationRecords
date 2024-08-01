#!/bin/bash
set -e

echo_red() {
    echo -e "\033[0;31m$@\033[0m"
}

echo_yellow() {
    echo -e "\033[0;33m$@\033[0m"
}

echo_green() {
    echo -e "\033[32m$@\033[0m"
}

# 一、声明安装变量
ORACLE_VERSION="11.2.0"
ORACLE_SID="orcl"
ORACLE_ROOT="/opt/oracle"
ORACLE_SCRIPT="$ORACLE_ROOT/script"
ORACLE_INSTALL="$ORACLE_ROOT/install"
ORACLE_BASE="$ORACLE_ROOT/app"
ORACLE_INVENTORY="$ORACLE_ROOT/oraInventory"
ORACLE_DPDUMP="$ORACLE_ROOT/dpdump"
ORACLE_HOME="$ORACLE_BASE/product/$ORACLE_VERSION/dbhome_1"
PATH="$PATH:$ORACLE_BASE/bin:$ORACLE_HOME/bin"
LD_LIBRARY_PATH="$ORACLE_HOME/lib:/lib:/usr/lib"
CLASSPATH="$ORACLE_HOME/jlib:$ORACLE_HOME/rdbms/jlib"
NLS_DATE_FORMAT="YYYY-MM-DD HH24:MI:SS"
ORACLE_HOME_LISTNER="$ORACLE_HOME"

log_alert="$ORACLE_BASE/diag/rdbms/orcl/$ORACLE_SID/trace/alert_$ORACLE_SID.log"
log_listener="$ORACLE_BASE/diag/tnslsnr/$HOSTNAME/listener/trace/listener.log"
sid_file=$ORACLE_HOME/dbs/init$ORACLE_SID.ora

monitor() {
    tail -F -n 0 $1 | while read line; do echo -e "$2: $line"; done
}

trap_db() {
	trap "echo_red 'Caught SIGTERM signal, shutting down...'; stop" SIGTERM;
	trap "echo_red 'Caught SIGINT signal, shutting down...'; stop" SIGINT;
}

init(){
  trap "echo_red '******* ERROR: Something went wrong.'; exit 1" SIGTERM
  trap "echo_red '******* Caught SIGINT signal. Stopping...'; exit 2" SIGINT

  if [ -d "$ORACLE_HOME" ]; then
    echo_green "Database is installed."
    exit 0
  fi
  if [ ! -d "$ORACLE_INSTALL" ]; then
    echo_red "Installation files not found. Unzip installation files into mounted($ORACLE_INSTALL) folder"
    exit 1
  fi
  echo_yellow "Database is not installed. Installing..."

  # 二、安装Oracle相关依赖
  echo_green "Installing dependencies"
  yum -y install binutils compat-libstdc++-33 compat-libstdc++-33.i686 ksh elfutils-libelf elfutils-libelf-devel glibc glibc-common glibc-devel gcc gcc-c++ libaio libaio.i686 libaio-devel libaio-devel.i686 libgcc libstdc++ libstdc++.i686 libstdc++-devel libstdc++-devel.i686 make sysstat unixODBC unixODBC-devel
  yum clean all
  rm -rf /var/lib/{cache,log} /var/log/lastlog

  # 三、配置配置
  echo_green "Configuring setting"
  echo "net.ipv4.ip_local_port_range = 9000 65500">>/etc/sysctl.conf
  echo "fs.file-max = 6815744">>/etc/sysctl.conf
  echo "kernel.shmall = 10523004">>/etc/sysctl.conf
  echo "kernel.shmmax = 6465333657">>/etc/sysctl.conf
  echo "kernel.shmmni = 4096">>/etc/sysctl.conf
  echo "kernel.sem = 250 32000 100 128">>/etc/sysctl.conf
  echo "net.core.rmem_default=262144">>/etc/sysctl.conf
  echo "net.core.wmem_default=262144">>/etc/sysctl.conf
  echo "net.core.rmem_max=4194304">>/etc/sysctl.conf
  echo "net.core.wmem_max=1048576">>/etc/sysctl.conf
  echo "fs.aio-max-nr = 1048576">>/etc/sysctl.conf

  echo "oracle  soft   nproc    2047">>/etc/security/limits.conf &&\
  echo "oracle  hard   nproc    16384">>/etc/security/limits.conf &&\
  echo "oracle  soft   nofile   1024">>/etc/security/limits.conf &&\
  echo "oracle  hard   nofile   65536">>/etc/security/limits.conf

  # 四、配置用户
  echo_green "Configuring user"
  groupadd -g 200 oinstall
  groupadd -g 201 dba
  useradd -u 440 -g oinstall -G dba -d /home/oracle oracle
  echo "oracle ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers
  echo "oracle:install" | chpasswd
  sed -i "s/pam_namespace.so/pam_namespace.so\nsession    required     pam_limits.so/g" /etc/pam.d/login
  echo_green "Configuring configuration"
  mkdir -p $ORACLE_BASE && mkdir -p $ORACLE_INVENTORY  && mkdir -p $ORACLE_DPDUMP
  chown -R oracle:oinstall $ORACLE_ROOT && chmod -R 755 $ORACLE_ROOT

  ## 初始化环境
  echo_green "Configuring environment"
  echo -En '#!'>/etc/profile.d/init-oracle.sh &&\
  echo "/bin/bash">>/etc/profile.d/init-oracle.sh &&\
  echo "export ORACLE_VERSION=$ORACLE_VERSION">>/etc/profile.d/init-oracle.sh &&\
  echo "export ORACLE_SID=$ORACLE_SID">>/etc/profile.d/init-oracle.sh &&\
  echo "export ORACLE_ROOT=$ORACLE_ROOT">>/etc/profile.d/init-oracle.sh &&\
  echo "export ORACLE_BASE=\$ORACLE_ROOT/app">>/etc/profile.d/init-oracle.sh &&\
  echo "export ORACLE_INVENTORY=\$ORACLE_ROOT/oraInventory">>/etc/profile.d/init-oracle.sh &&\
  echo "export ORACLE_DPDUMP=\$ORACLE_ROOT/dpdump">>/etc/profile.d/init-oracle.sh &&\
  echo "export ORACLE_HOME=\$ORACLE_BASE/product/\$ORACLE_VERSION/dbhome_1">>/etc/profile.d/init-oracle.sh &&\
  echo "export PATH=\$PATH:\$ORACLE_BASE/bin:\$ORACLE_HOME/bin">>/etc/profile.d/init-oracle.sh &&\
  echo "export LD_LIBRARY_PATH=\$ORACLE_HOME/lib:/lib:/usr/lib">>/etc/profile.d/init-oracle.sh &&\
  echo "export CLASSPATH=\$ORACLE_HOME/jlib:\$ORACLE_HOME/rdbms/jlib">>/etc/profile.d/init-oracle.sh &&\
  echo "export NLS_DATE_FORMAT=$NLS_DATE_FORMAT">>/etc/profile.d/init-oracle.sh &&\
  echo "export ORACLE_HOME_LISTNER=\$ORACLE_HOME">>/etc/profile.d/init-oracle.sh &&\
  chmod 755 /etc/profile.d/init-oracle.sh
}

install(){
  echo_green "Installing Oracle Database 11g"
  $ORACLE_INSTALL/runInstaller -silent -ignorePrereq -waitforcompletion -responseFile $ORACLE_SCRIPT/db_install.rsp
  $ORACLE_INVENTORY/orainstRoot.sh
  $ORACLE_HOME/root.sh

  chmod 777 /opt/oracle/dpdump

  echo "Checking shared memory..."
  df -h | grep "Mounted on" && df -h | egrep --color "^.*/dev/shm" || echo "Shared memory is not mounted."
  if [ ! -f $sid_file ]; then
    echo_yellow "Database does not exist. Creating database..."
    date "+%F %T"
    monitor "$log_alert" "alertlog" &
    MON_ALERT_PID=$!
    monitor "$log_listener" "listener" &
    #MON_LSNR_PID=$!
    echo "START DBCA"
    dbca -silent -createDatabase -responseFile $ORACLE_SCRIPT/dbca.rsp
    echo_green "Database created."
    date "+%F %T"
    echo_green "Changind dpdump dir to $ORACLE_DPDUMP"
    sqlplus / as sysdba <<-EOF |
      create or replace directory data_pump_dir as '$ORACLE_DPDUMP';
      commit;
      exit 0
EOF
    while read line; do echo -e "sqlplus: $line"; done
    touch $sid_file
    trap_db
    kill $MON_ALERT_PID
    #wait $MON_ALERT_PID
  fi
  rm -rf $ORACLE_INSTALL
}

start(){
	echo_yellow "Starting listener..."
	monitor "$log_listener" "listener" &
	lsnrctl start | while read line; do echo -e "lsnrctl: $line"; done
	MON_LSNR_PID=$!
	echo_yellow "Starting database..."
	trap_db
	monitor "$log_alert" "alertlog" &
	MON_ALERT_PID=$!
	sqlplus / as sysdba <<-EOF |
		pro Starting with sid_file='$sid_file' ...
		startup;
		alter system register;
		exit 0
	EOF
	while read line; do echo -e "sqlplus: $line"; done
	wait $MON_ALERT_PID
}

stop(){
  trap '' SIGINT SIGTERM
	ps -ef | grep ora_pmon | grep -v grep > /dev/null && \
	echo_yellow "Shutting down the database..." && \
	sqlplus / as sysdba <<-EOF |
		set echo on
		shutdown immediate;
		exit 0
	EOF
	while read line; do echo -e "sqlplus: $line"; done
	echo_yellow "Shutting down listener..."
	lsnrctl stop | while read line; do echo -e "lsnrctl: $line"; done
	kill $MON_ALERT_PID $MON_LSNR_PID
	exit 0
}

# 根据条件调用函数
if [ "$1" = "install" ]; then
  if [ "$2" = "" ]; then
    init
    su oracle -c "$0 install once"
  else
    install
  fi
elif [ "$1" = "start" ]; then
  start
elif [ "$1" = "stop" ]; then
  stop
else
  echo "Usage: $0 {install|start|stop}"
fi
