/* table : login */
create table login (
    id integer auto_increment not null primary key,
    user_name varchar(32) not null, 
    password varchar(32) not null, 
    enabled boolean not null, 
    role varchar(16) not null
);

/* table : product */
create table products(
    id integer auto_increment not null primary key, 
    name varchar(64) not null, 
    description varchar(256),
    image varchar(128) 
);

/* table: answer_types */
create table answer_types(
    id integer auto_increment not null primary key,
    answer_type varchar(16) not null
);


/* table : questionnaires */
create table questionnaires(
    id integer auto_increment not null primary key, 
    product_id integer not null,
    sequence_number integer not null,
    question varchar(256) not null,
    answer_type_id integer not null,
    depends_on integer,
    on_answer varchar(128),
    is_required boolean not null,
    constraint questionnaires_fk1 foreign key (product_id) references products (id),
    constraint questionnaires_fk2 foreign key (answer_type_id) references answer_types (id)
);

/* table : answer_values */
create table answer_values(
    id integer auto_increment not null primary key,
    questionnaire_id integer not null,
    answer_value varchar(128) not null,
    constraint answer_values_fk1 foreign key (questionnaire_id) references questionnaires (id)
);


/* table : broker */
create table brokers(
    id integer auto_increment not null primary key,
    code varchar(8) not null,
    name varchar(64) not null,
    email varchar(64) not null
);

/* table : quotation_requests */
create table quotation_requests(
    id integer auto_increment not null primary key,
    reference varchar(64) not null,
    applicant_name varchar(32) not null,
    company_name varchar(32) not null,
    applicant_email varchar(64) not null,
    broker_id integer not null,
    product_id integer not null,
    create_date date not null,
    status varchar(16) not null,
    constraint quotation_requests_fk1 foreign key (broker_id) references brokers (id),
    constraint quotation_requests_fk2 foreign key (product_id) references products (id)
);

/* table : quotation_questoionnaires */
create table answers(
    id integer auto_increment not null primary key,
    quotation_request_id integer not null,
    question varchar(256) not null,
    answer varchar(256),
    constraint answers_fk1 foreign key (quotation_request_id) references quotation_requests (id)   
);


/* table : quotations */
create table quotations(
    id integer auto_increment not null primary key,
    quotation_request_id integer not null,
    created_date date not null,
    expired_date date null,
    constraint quotation_fk foreign key (quotation_request_id) references quotation_requests (id)
    
);

/* table : quotation_options  */
create table quotation_options(
    id integer auto_increment not null primary key,
    quotation_id integer not null,
    location varchar(32) not null,
    limits varchar(32) not  null ,
    commodity varchar(32) not null,
    cover varchar(256) not null,
    duration varchar(256) not null,
    excess varchar(256) not null,
    cross_pavement varchar(32),
    static_limit varchar(32),
    premium varchar(256) not null,
    constraint quotation_options_fk foreign key (quotation_id) references quotations (id)
);

/*table : policy_requests */
create table policy_requests(
    id integer auto_increment not null primary key,
    quotation_id integer not null,
    quotation_option_id integer not null,
    company_reg_number varchar(32) not null,
    vat_reg_number varchar(32) not  null ,
    telephone_number varchar(32) not null,
    fax_number varchar(256) not null,
    street_address varchar(32) not null,
    suburb varchar(64) not null,
    postal_code varchar(64) not null,
    designation varchar(64) not null,
    buisness_desc varchar(64) not null,
    account_holder varchar(64) not null,
    account_name varchar(64) not null,
    bank_name varchar(64) not null,
    account_number varchar(64) not null,
    branch_code varchar(64) not null,
    acc_type varchar(64) not null,
    debit_order_date varchar(32) not null,
    bank_statement longblob not null,
    status varchar(32) not null,
    constraint applicant_details_fk1 foreign key (quotation_id) references quotations (id),
    constraint applicant_details_fk2 foreign key (quotation_option_id) references quotation_options (id)
);





/* table : bank_accounts */
create table bank_accounts(
      id integer auto_increment not null primary key,
      account_number varchar(32) not null,
      account_name varchar(32) not null,
      branch varchar(32) not null,
      bank_name varchar(32) not null,
   
);

/* table :  contacts */
create table contacts(
      id integer auto_increment not null primary key,
      street varchar(32) not null,
      city varchar(32) not null,
      code varchar(32) not null,
      suburb varchar(32) not null,
      work_tel_number varchar(32) not null,
      fax_number varchar(32) not null,
      email varchar(32) not null,
      contact_person varchar(32) not null
);

/* table : clients */
create table clients (
      id integer auto_increment not null primary key,
      bank_account_id integer not null,
      contact_id integer not null,
      company_name varchar(64) not null,
      reg_no varchar(32) not null,
      income_tax_number varchar(32),
      vat_number varchar(32) not null ,
      constraint clients_fk1 foreign key (bank_account_id) references bank_accounts (id),
<<<<<<< HEAD
      constraint clients_fk2 foreign key (contact_id) references contacts (id)
=======
      constraint clients_fk2 foreign key (contact_id) references contacts(id)
>>>>>>> d3ba23efb7290e5df4a751802e9cd804707eeab6
);

/* table : insurers */
create table insurers(
    id integer auto_increment not null primary key, 
    name varchar(64) not null, 
    business_description varchar(256),
);

/* table : underwriters */
create table underwriters(
    id integer auto_increment not null primary key, 
    insurer_id integer not null,
    name varchar(64) not null,
    commission_rate decimal not null,
    category varchar(64) not null,
    uma_fee decimal not null,
    constraint underwriters_details_fk1 foreign key(insurer_id) references insurers(id)
);

/* table : sub_agents */
create table sub_agents(
    id integer auto_increment not null primary key, 
    broker_id integer not null,
    name varchar(64) not null, 
    constraint sub_agent_detail_fk1 foreign key(broker_id) references brokers(id),
);


/* table : policy_masters */
create table client_policies(
    id integer auto_increment not null primary key,
    sub_agent_id integer not null,
    insurer_id integer not  null ,
    client_id integer not  null ,
    underwriter_id integer not  null ,
    inception_date date not null,
    renewal_date date not null,
    underwriting_year integer not null,
    status varchar(20) not null,
    frequency varchar(30) not null,
    sasria_frequency varchar(30) not null,
    device varchar(30) not null,
    retroactive_date date not null,
    collect_by_debit_order boolean,
    exclude_sasria boolean,
    underwriter_fee decimal not null,
    broker_fee decimal not null,
    notes varchar(256) not null,
    constraint client_policies_details_fk1 foreign key (sub_agent_id) references sub_agents (id),
    constraint client_policies_details_fk2 foreign key(insurer_id) references insurers(id),
    constraint client_policies_details_fk3 foreign key(client_id) references clients(id),
    constraint client_policies_details_fk4 foreign key(underwriter_id) references underwriters(id),
);
