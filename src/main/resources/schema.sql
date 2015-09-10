/* table : schedule_attachings */
create table schedule_attachings(
    id integer auto_increment not null primary key,
    schedule_attaching_values varchar(1024) not null
);

/* table : subject_matters */
create table subject_matters(
    id integer auto_increment not null primary key,
    subject_matter_values varchar(1024) not null
);

/* table : type_of_covers */
create table type_of_covers(
    id integer auto_increment not null primary key,
    type_of_cover_values varchar(1024) not null
);

/* table : excess_structures */
create table excess_structures(
    id integer auto_increment not null primary key,
    excess_structure_values varchar(512) not null
);

/* table : special_policy_conditions */
create table special_policy_conditions(
    id integer auto_increment not null primary key,
    special_policy_condition_values varchar(1024) not null
);

/* table : underwritter_generals */
create table underwritter_generals(
    id integer auto_increment not null primary key,
    schedule_attaching_id integer not null,
    type_of_cover_id integer not null,
    subject_matter_id integer not null,
    excess_structure_id integer not null,
    special_policy_condition_id integer not null,
    constraint underwritter_general_fk1 foreign key (schedule_attaching_id) references schedule_attachings (id),
    constraint underwritter_general_fk2 foreign key (type_of_cover_id) references subject_matters (id),
    constraint underwritter_general_fk3 foreign key (subject_matter_id) references type_of_covers (id),
    constraint underwritter_general_fk4 foreign key (excess_structure_id) references excess_structures (id),
    constraint underwritter_general_fk5 foreign key (special_policy_condition_id) references special_policy_conditions (id)
);

create table client_master_data (
      id integer auto increment not null primary key,
      client_no varchar(64) not null,
      client_name varchar(64) not null,
      ap_code varchar(32) not null,
      policy_number varchar(32) not null,
      street varchar(32) not null,
      city varchar(32) not null,
      code varchar(32) not null,
      postal_address varchar(32) not null,
      surbub varchar(32) not null,
      work_tel_number varchar(32) not null,
      home_tel_number varchar(32),
      fax_number varchar(32) not null,
      cell_number varchar(32),
      bank_code varchar(32),
      account_number varchar(32) not null,
      account_name varchar(32) not null,
      branch varchar(32) not null,
      bank_name varchar(32) not null,
      reg_number varchar(32) not null,
      income_tax_number varchar(32),
      vat_number varchar(32) not null,
      passport_number varchar(32),
      email varchar(32) not null,
      contact_person varchar(32) not null,
      pref_comm varchar(32) not null,
);

