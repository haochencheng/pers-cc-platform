package pers.platform.core.id;

/*
    mysql事务实现并发安全的自增ID的方法

    CREATE TABLE auto_id(
idname varchar(20) NOT NULL DEFAULT '',
id bigint(20) NOT NULL DEFAULT 0 COMMENT '',
primary key(idname)
)ENGINE=Innodb DEFAULT CHARSET=utf8;

一个存储过程：

复制代码 代码示例:
delimiter //
drop procedure if exists get_increment_id;
create procedure get_increment_id(in idname_in varchar(20), in small_in bigint, out id_out bigint)
begin
declare oldid bigint;
start transaction;
select id into oldid from maibo_auto_id where idname=idname_in for update;
if oldid is NULL then
insert into maibo_auto_id(idname,id) value(idname_in, small_in);
set id_out=small_in;
else
update maibo_auto_id set id=id+1 where idname=idname_in;
set id_out=oldid+1;
end if;
commit;
end;
//


* select id into oldid from maibo_auto_id where idname=idname_in for update

会给相关数据加一个独占锁定，这时候别的进程如果来读取该条记录，就会进入等待，等待这个进程commit之后，再继续，这样就保证了在并发的情况下，不同的进程不会取到相同的值。
如果前端是用php实现的。
只需执行如下两个sql，就可以获取到，这个small参数是定义的是从多少开始自增：

复制代码 代码示例:
$sql = "call get_increment_id('{$key}', {$small}, @id)";
$ret = $db->getData("select @id");
*
* */