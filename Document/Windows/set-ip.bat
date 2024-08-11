@echo off
@REM setlocal：命令用于启动本地环境变量设置。这意味着在其后的命令中对环境变量的修改仅在当前脚本或批处理文件中有效，不会影响到调用该脚本或批处理文件的父进程。
@REM enabledelayedexpansion：是 setlocal 的一个选项，用于启用延迟环境变量扩展。延迟环境变量扩展允许在脚本执行过程中动态地获取和更新环境变量的值。在启用延迟环境变量扩展后，可以使用 !variable! 语法来访问变量的值，而不是 %variable% 语法。这在处理循环或需要多次更新变量值的场景中非常有用。

chcp 65001>nul

SET /P "v_name=设置网络接口名称（默认“以太网”）："
if "%v_name%"=="" set v_name=以太网

set /P "v_ip=设置静态IP地址（默认“DHCP”）："
if "%v_ip%"=="" set v_ip=DHCP

if "%v_ip%"=="DHCP" (
    netsh interface ip set address "%v_name%" dhcp
) else (
    set /P "subnet_mask=设置子网掩码（默认“255.255.255.0”）："
    if "%subnet_mask%"=="" set subnet_mask=255.255.255.0

    set /P "gateway=设置网关（默认“192.168.0.1”）："
    if "%gateway%"=="" set gateway=192.168.0.1

    set /P "dns_main=设置首选DNS服务器(默认“无”)："

    set /P "dns_secondary=设置备用DNS服务器(默认“无”)："

    :: 禁用动态主机配置协议(DHCP)
    netsh interface ip set address %v_name% static "%v_ip%" "%subnet_mask%" "%gateway%"

    :: 设置首选DNS服务器
    if not "%dns_main%"=="" netsh interface ip set dns name="%v_name%" source=static addr="%dns_main%" register=primary

    :: 设置备用DNS服务器
    if not "%dns_secondary%"=="" netsh interface ip add dns name="%v_name%" addr="%dns_secondary%" index=2
)
