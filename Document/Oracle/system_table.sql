
select * from dba_data_files;
-- 系统数据文件，SYSTEM[XX].DBF,SYSAUX[XX].DBF
-- 回滚数据文件，UNDOTBS[XX].DBF
-- 用户数据文件，USERS[XX].DBF
-- 临时数据文件，TEMP[XX].DBF
-- [XX]内是数字如，01，02

--
-- 数据字典是Oracle存储数据库内部信息的地方，描述了数据库内部的运行和管理情况，Oracle数据字典的名称由前缀和后缀组成，使用下划线“_”连接，如下：
-- dba: 包含数据库实例的所有对象信息
-- v$_:当前实例的动态视图，包含系统管理和系统优化等使用的视图
-- user_: 记录用户的对象信息
-- gV_:分布式环境下所有实例的动态视图，包含系统管理和系统优化使用的视图
-- all_:记录用户的对象信息及被授权访问的对象信息


-- >>>>>>>>>>>>基本的数据字典
-- DBA_TABLES       所有用户的所有表的信息
-- DBA_TAB COLUMNS  所有用户的表的字段信息
-- DBA_VIEWS        所有用户的所有视图信息
-- DBA_SYNONYMS     所有用户的同义词信息
-- DBA_SEQUENCES    所有用户的序列信息
-- DBA_CONSTRAINTS  所有用户的表的约束信息
-- DBA_INDEXES      所有用户的表的索引简要信息
-- DBA_IND COLUMNS  所有用户的索引的字段信息
-- DBA_TRIGGERS     所有用户的触发器信息
-- DBA_SOURCES      所有用户的存储过程信息
-- DBA_SEGMENTS     所有用户的段的使用空间信息
-- DBA_EXTENTS      所有用户的段的扩展信息
-- DBA_OBJECTS      所有用户对象的基本信息


select * from v$DATABASE;
-- >>>>>>>>>>>>常用动态性能视图
-- v$DATABASE       显示当前数据库实例
-- v$FXED_TABLE     显示当前发行的固定对象的说明
-- v$INSTANCE       显示当前实例信息
-- v$LATCH          显示锁存器的统计数据
-- v$LIBRARYCACHE   显示有关库缓存性能的统计数据
-- v$ROLLSTAT       显示联机的回滚段的名字
-- v$ROWCACHE       显示活动数据字典的统计
-- v$SGA            显示有关系统全局区的总结信息
-- v$SGASTAT        显示有关系统全局区的详细信息
-- v$SORT USAGE     显示临时段的大小及会话
-- v$SQLAREA        显示SQL区的SQL信息
-- v$SQLTEXT        显示在SGA中属于共享游标的SQL语句内容
-- v$STSSTAT        显示基本的实例统计数据
-- v$SYSTEM EVENT   显示一个事件的总计等待时间
-- v$WAITSTAT       显示块竞争统计数据
