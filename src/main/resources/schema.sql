create table login (id integer not null, user_name varchar(32) not null, password varchar(32) not null, enabled boolean not null, role varchar(16) not null, constraint login_pk primary key (id));

create sequence login_id_seq start with 1 increment by 1 ;

create trigger login_id_trigger before insert on login referencing new row as new for each row set new.id = next value for login_id_seq;

