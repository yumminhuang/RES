create table User(
       id int primary key ,
       name varchar(255) not null,
       phone varchar(255) not null,
       address varchar(255),
       email varchar(255),
       type enum('Landlord','Tenant','Agent')
);
create table Apartment (
        id int primary key ,
        number varchar(255) not null,
        address varchar(255),
        area int not null,
        owner int references User(id)
);

create table Topic(
       id int primary key ,
       title varchar(255) not null,
       uid int references User(id),
       content text,
       image1 varchar(255),
       image2 varchar(255)
);
create table Reply(
       id int primary key ,
       uid int references User(id),
       tid int references Topic(id),
       content text not null,
       rtime date not null,
       image1 varchar(255),
       image2 varchar(255)
);

create table FromUser(
       id int primary key references User(id)
);
create table ToUser(
       id int primary key references User(id)
);
create table Message(
       id int primary key ,
       mfrom  int references FromUser(id),
       mto  int references ToUser(id),
       content text not null,
       mtime date not null
);

create table Schedule(
       id int primary key ,
       sfrom int references FromUser(id),
       sto int references ToUser(id),
       content text not null,
       stime date not null
);

DELIMITER |
create trigger grant_access after insert on User
 for each row begin
 insert into FromUser set id = new.id;
 insert into ToUser set id = new.id;
 end
 |
 create trigger revoke_access after delete on User
 for each row begin
 delete from FromUser where FromUser.`id` = old.`id`;
 delete from ToUser where ToUser.`id` = old.`id`;
 end|
