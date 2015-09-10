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

/* table : underwritter_general */
create table underwritter_general(
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

