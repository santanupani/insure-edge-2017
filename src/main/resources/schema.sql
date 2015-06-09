
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
    image varchar(150), 
    constraint product_pk primary key (id)
);

create sequence product_id_seq start with 1 increment by 1 ;

create trigger product_id_trigger before insert on product referencing new row as new for each row set new.id = next value for product_id_seq;


/* Table questionnaires */
create table questionnaires(
    id integer not null, 
    product_id integer not null,
    seq_uence integer not null,
    question varchar(250) not null,
    answer_type varchar(10) not null,
    depends_on integer,
    if_answer varchar(128),
    constraint questionnaires_pk primary key (id),
    constraint questionnaires_fk foreign key (product_id) references product (id)

);

create sequence questionnaires_id_seq start with 1 increment by 1 ;

create trigger questionnaires_id_trigger before insert on questionnaires referencing new row as new for each row set new.id = next value for questionnaires_id_seq;


/* Table questionnaires_options */
create table questionnaires_options(
    id integer not null, 
    questionnaires_id integer not null,
    constraint questionnaires_options_pk primary key (id),
    constraint questionnaires_fk_oprions foreign key (questionnaires_id) references questionnaires (id)

);

create sequence questionnaires_options_id_seq start with 1 increment by 1 ;

create trigger questionnaires_options_id_trigger before insert on questionnaires_options referencing new row as new for each row set new.id = next value for questionnaires_options_id_seq;


/* Table answer_types */
create table answer_types(
    id integer not null,
    questionnaires_id integer not null,
    answer_type varchar(8) not null,
    constraint answer_id_pk primary key (id),
    constraint questionnaires_fk_answers foreign key (questionnaires_id) references questionnaires (id)

);

create sequence answer_id_seq start with 1 increment by 1 ;

create trigger answer_id_trigger before insert on answer_types referencing new row as new for each row set new.id = next value for answer_id_seq;




