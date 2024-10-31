-- 集数据定义语言（Data Definition Language）DDL
-- SQL动词如下：
-- CREATE,DROP,ALTER


-- >>>>>>>>>>>>定义基本表
-- CREATE TABLE <表名>(
--     <列名><数据类型>[<列级完整性约束条件>],
--     <列名><数据类型>[<列级完整性约束条件>],
--     ...
-- [<表级完整性约束条件>]
-- );
-- <表名>：所要定义的基本表的名字。
-- <列名>：组成该表的各个属性 (列)。
-- <列级完整性约束条件>：涉及相应属性列的完整性约束条件。
-- <表级完整性约束条件>：涉及一个或多个属性列的完整性约束条件。
-- 如果完整性约束条件涉及到该表的多个属性列，则必须定义在表级上，否则既可以定义在列级也可以定义在表级。

-- 练习
-- 建立“学生”表student，学号是主码，姓名取值唯一
create table student
(
    no   varchar(10) primary key,
    name varchar(10) unique,
    sex  char(2),
    age  smallint,
    dept varchar(20)
);
drop table student;

-- 建立“课程”表course，课程码是主码，姓名取值唯一
create table course
(
    no     varchar(10) primary key,
    name   varchar(40),
    cno    varchar(10),
    credit smallint,
    foreign key (cno) references course(no)
);
drop table course;

-- 建立“选课表”表curricula_variable，课程码是主码，姓名取值唯一
create table sc(
    sno     varchar(10),
    cno     varchar(10),
    grade   smallint,
    primary key (sno,cno),
    foreign key (sno) references student(no),
    foreign key (cno) references student(no)
);

-- >>>>>>>>>>>>定义基本表



-- >>>>>>>>>>>>定义存储过程







-- >>>>>>>>>>>>定义存储过程




select * from student for update

declare
    avg_age number;
begin
    avg_age := get_avg_age('人事部');
    dbms_output.put_line(avg_age);
end;


-- >>>>>>>>>>>>定义函数
-- 函数一般用于计算或表示某种功能，函数可以接收参数，有的函数也可以没有参数。但是函数必须要有返回值。


create or replace function get_avg_age(param_dept varchar2) return number is
    avg_age_result number;
begin
    select avg(s.age)
    into   avg_age_result
    from   student s
    where  s.dept = param_dept;
    return round(avg_age_result, 1);
exception
    when no_data_found then
        dbms_output.put_line('dept not found');
        return 0;
end;





-- >>>>>>>>>>>>定义函数



-- >>>>>>>>>>>>定义触发器
-- 触发器(trigger)是提供给程序员和数据分析员来保证数据完整性的一种方法，它是与表事件相关的特殊的存储过程，它的执行是由事件来触发。
-- 比如当对一个表进行操作(insert,delete,update)时就会激活它执行。
-- 触发器经常用于加强数据的完整性约束和业务规则等。 
-- 触发器可以从 DBA_TRIGGERS，USER_TRIGGERS 数据字典中查到SQL3的触发器是一个能由系统自动执行对数据库修改的语句。


-- 语句级触发器
--          针对一条DML语句而引起的触发器执行。无论数据操作影响多少行，触发器都只会执行一次。
-- 替换触发器:
--         定义在视图上的触发器，当用户操作视图时执行替换触发器，实现对基表的操作。
-- 系统事件触发器:
--         DDL:create、alter、drop等发生DDL语言时，会触发系统事件触发器。除此之外还能在系统服务器的启动、关闭、出错时也会触发特定的系统事件触发器。


create trigger tri_test before

-- >>>>>>>>>>>>定义触发器
