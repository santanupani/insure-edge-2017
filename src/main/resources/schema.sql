
/* Table login */
create table login (
    id integer not null, 
    user_name varchar(32) not null, 
    password varchar(32) not null, 
    enabled boolean not null, 
    role varchar(16) not null, 
    constraint login_pk primary key (id)
);

create sequence login_id_seq start with 1 increment by 1 ;

create trigger login_id_trigger before insert on login referencing new row as new for each row set new.id = next value for login_id_seq;


/* Table product */
create table product(
    id integer not null, 
    name varchar(70) not null, 
    description varchar(250), 
    constraint product_pk primary key (product_id)
);

create sequence product_id_seq start with 1 increment by 1 ;

create trigger product_id_trigger before insert on product referencing new row as new for each row set new.id = next value for product_id_seq;