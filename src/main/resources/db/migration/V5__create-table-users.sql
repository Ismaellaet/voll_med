create table users(

    id bigint not null auto_increment,
    username varchar(100) not null,
    password varchar(255) not null,

    primary key(id)

);