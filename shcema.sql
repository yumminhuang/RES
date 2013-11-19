create table User(
       id int primary key AUTO_INCREMENT,
       name varchar(255) not null,
       telphone int not null,
       Address varchar(255),
       email varchar(255),
       type enum('Landlord','Tenant','Agent')
);
create table Apartment (
     id int primary key AUTO_INCREMENT,
	 number varchar(31) not null,
	 address varchar(255),
	 area double not null,
	 owner int references User(id)
);

create table Topic(
       id int primary key AUTO_INCREMENT,
       title varchar(255) not null,
       content text,
       image1 varchar(255),
       image2 varchar(255),
       postby int references User(id)
);
create table Reply(
       id int primary key AUTO_INCREMENT,
       userId int references User(id),
       topicId int references Topic(id),
       content text not null,
       postby int references User(id),
       replytime date not null,
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
       id int primary key AUTO_INCREMENT,
       messagefrom  int references FromUser(id),
       messageto  int references ToUser(id),
       content text not null,
       messagetime date not null
);

create table Schedule(
       id int primary key AUTO_INCREMENT,
       schedulefrom int references FromUser(id),
       scheduleto int references ToUser(id),
       scheduletime date not null,
       content text not null
);
