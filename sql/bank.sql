create table account (
                         accid int primary key auto_increment,
                         name varchar(30) not null,
                         money double not null
);

create table log (
                     logid int primary key auto_increment,
                     accid int not null,
                     name varchar(30) not null,
                     money double not null,
                     date datetime not null
);

insert into account values(null, 'zhang san', 1000);
insert into log values(null, 1, 'zhang san', 1000, '2021-11-11 14:01:02');

select * from account;
select * from log;