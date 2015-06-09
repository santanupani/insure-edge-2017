
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

create table quotequestion(
    id integer not null, 
    maxamount varchar(100) not null, 
    totalvalue varchar(100) not null,
    additionalpremium varchar(100) not null, 
    professionalcarriers varchar(100) not null,
    timeperweek varchar(100) not null,
    location varchar(100) not null,
    distanceinkms varchar(100) not null,
    goodsdescription varchar(100) not null,
    vaultdetails varchar(100) not null,
    sabs varchar(100) not null,
    goodstorage varchar(100) not null,
    reenforcedconcrete varchar(100) not null,
    seismicconductors varchar(100) not null,
    cctvfootgae varchar(100) not null,
    alarmresponse varchar(100) not null,
    physicaladdresscode varchar(100) not null,
    postaladdresscode varchar(100) not null,
    insuredstatus varchar(100) not null,
    insureddetails varchar(100) not null,
    requiressasria varchar(100) not null,
    constraint quotequestion_pk primary key (id)
);

create sequence quotequestion_id_seq start with 1 increment by 1 ;

create trigger quotequestion_id_trigger before insert on quotequestion referencing new row as new for each row set new.id = next value for quotequestion_id_seq;

