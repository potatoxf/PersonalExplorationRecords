-- 数据控制语言（Data Control Language）DCL
-- SQL动词如下：
-- GRANT,REVOKE


-- 删除用户
drop user scott
-- 将用户连同其创建的东西全部删除
    cascade
;

-- 创建用户
create user scott
-- 用户密码
    identified by "scott"
-- 默认表空间
    default tablespace USERS
-- 默认临时表空间
    temporary tablespace TEMP
-- 账户锁定
-- account lock
-- 密码过期
-- password expire
;


-- ORACLE系统提供三种权限：Object 对象级、System 系统级、Role 角色级。
-- 权限分类
-- 1、系统权限：系统规定用户使用数据库的权限。(系统权限是对用户而言)。
--      DBA: 拥有全部特权，是系统最高权限，只有DBA才可以创建数据库结构。
--      RESOURCE:拥有Resource权限的用户只可以创建实体，不可以创建数据库结构。
--      CONNECT:拥有Connect权限的用户只可以登录Oracle，不可以创建实体，不可以创建数据库结构。
-- with admin option：
--      (1)如果使用为某个用户授予系统权限，那么对于被这个用户授予相同权限的所有用户来说，取消该用户的系统权限并不会级联取消这些用户的相同权限。
--      (2)系统权限无级联，即A授予B权限，B授予C权限，如果A收回B的权限，C的权限不受影响；系统权限可以跨用户回收，即A可以直接收回C用户的权限。

grant connect, resource, dba to scott with admin option;
-- 系统权限回收：系统权限只能由DBA用户回收
revoke connect, resource, dba from scott;

-- 授予用户登录权限
grant create session to scott;
-- 授予用户“创建表”权限
grant create any table to scott;
-- 授予用户“删除表”权限
grant drop any table to scott;

-- 授予用户“创建索引”权限
grant create any index to scott;
-- 授予用户“删除索引”权限
grant drop any index to scott;

-- 授予用户表空间操作权限
grant unlimited tablespace to scott;


-- 2、实体权限：某种权限用户对其它用户的表或视图的存取权限。(是针对表或视图而言的)。
-- select, update, insert, alter, index, delete,
-- all：包括所有权限
-- execute：执行存储过程权限
-- with grant option：
--      (1)使用该选项取消了A用户权限，那么对于A用户授予权限的用户来说，也会取消这些用户的相同权限，也就是说取消授权是级联的。
-- 赋予用户表访问权限，得到权限，并可以传递
grant select any table to scott with grant option;
-- 取消用户表访问权限，传递的权限将全部丢失
revoke select any table from scott;

grant read any table to scott;

-- 赋予用户表访问权限
grant select any table to scott;
-- 赋予用户指定表访问权限
-- grant select on table_name to scott;

-- 赋予用户表更新权限
grant update any table to scott;
-- 赋予用户指定表更新权限
-- grant update on table_name to scott;

-- 赋予用户表删除权限
grant delete any table to scott;
-- 赋予用户指定表删除权限
-- grant delete on table_name to scott;

-- 传递的权限将全部丢失
revoke select, update any table, delete from scott;


-- 查自己拥有哪些系统权限
select *
from session_privs;



select *
from dba_role_privs;

select *
from dba_sys_privs;

select *
from role_sys_privs;


-- 获权可以存取的表(被授权的)
select grantor, table_schema, table_name, privilege
from all_tab_privs;
-- 授出权限的表(授出的权限)
select grantee, owner, table_name, privilege
from user_tab_privs;