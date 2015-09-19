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
    constraint product_questionnaires_fk foreign key (product_id) references products (id),
    constraint answer_type_questionnaires_fk foreign key (answer_type_id) references answer_types (id)
);

/* table : answer_values */
create table answer_values(
    id integer auto_increment not null primary key,
    questionnaire_id integer not null,
    answer_value varchar(128) not null,
    constraint questionnaire_answer_values_fk foreign key (questionnaire_id) references questionnaires (id)
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
    constraint broker_quotation_requests_fk foreign key (broker_id) references brokers (id),
    constraint product_quotation_requests_fk foreign key (product_id) references products (id)
);

/* table : quotation_questoionnaires */
create table answers(
    id integer auto_increment not null primary key,
    quotation_request_id integer not null,
    question varchar(256) not null,
    answer varchar(256),
    constraint quotation_request_answers_fk1 foreign key (quotation_request_id) references quotation_requests (id)   
);


/* table : quotations */
create table quotations(
    id integer auto_increment not null primary key,
    quotation_request_id integer not null,
    created_date date not null,
    expired_date date null,
    constraint quotation_request_quotations_fk foreign key (quotation_request_id) references quotation_requests (id)
    
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
    constraint quotation_quotation_options_fk foreign key (quotation_id) references quotations (id)
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
    constraint quotation_policy_requests_fk foreign key (quotation_id) references quotations (id),
    constraint quotation_option_policy_requests_fk foreign key (quotation_option_id) references quotation_options (id)
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
      code varchar(32) not null,
      suburb varchar(32) not null,
      work_tel_number varchar(32) not null,
      fax_number varchar(32) not null,
      email varchar(32) not null,
      contact_person varchar(32) not null,
);

/* table : insurers */
create table insurers(
    id integer auto_increment not null primary key, 
    name varchar(64) not null, 
    business_description varchar(256),
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
      constraint bank_account_clients_fk foreign key (bank_account_id) references bank_accounts (id),
      constraint contact_clients_fk foreign key (contact_id) references contacts(id)

);


/* table : underwriters */
create table underwriters(
    id integer auto_increment not null primary key, 
    first_name varchar(64) not null, 
    middle_name varchar(64) not null,
    last_name varchar(64) not null,
    email varchar(64) not null,
);

/* table : sub_agents */
create table sub_agents(
    id integer auto_increment not null primary key, 
    broker_id integer not null,
    first_name varchar(64) not null, 
    middle_name varchar(64) not null,
    last_name varchar(64) not null,
    email varchar(64) not null,
    constraint broker_sub_agents_fk foreign key(broker_id) references brokers(id),
);


/* table : policy_masters */
create table policies(
    id integer auto_increment not null primary key,
    policy_reference varchar(32) not null,
    sub_agent_id integer not null,
    client_id integer not  null ,
    underwriter_id integer not  null ,
    inception_date varchar(30) not null,
    renewal_date varchar(30) not null,
    underwriting_year integer not null,
    status varchar(20) not null,
    frequency varchar(30) not null,
    sasria_frequency varchar(30) not null,
    device varchar(30) not null,
    retro_active_date varchar(30) not null,
    re_instatement varchar(45),
    collect_by_debit_order boolean,
    exclude_sasria boolean,
    underwriter_fee decimal not null,
    broker_fee decimal not null,
    notes varchar(256) not null,
    constraint sub_agent_policies_fk foreign key (sub_agent_id) references sub_agents (id),
    constraint client_policies_fk foreign key(client_id) references clients(id),
    constraint underwriter_policies_fk foreign key(underwriter_id) references underwriters(id),
);


/* table : policy_schedule */
create table policy_schedules(
    id integer auto_increment not null primary key,
    policy_id varchar(16) not null,
    sum_insured decimal not null,
    maximum_sum_insured decimal not null,
    broker_commission decimal not null,
    UA_commission decimal not null,
    premium decimal not null,
    sasria_premium decimal not null,
    schedule_attaching varchar(1024) not null,
    type_of_cover varchar(1024) not null,
    subject_matter varchar(16) not null,
    excess_structure varchar(256) not null,
    special_condition varchar(1024) not null,
    conveyances varchar(64) not null,
    geographical_duration varchar(2048) not null,
    constraint policies_policy_schedules_fk foreign key (policy_id) references policies (id)
);

/* table : indemnity_option */

create table indemnity_options(
    id integer auto_increment not null primary key,
    policy_schedule_id integer not null,
    indemity_item_option varchar(64) not null,   
    indemnity_value varchar(64) not null,
    sum_insured decimal not null,
    premium decimal not null,
    constraint policy_schedules_indemnity_options_fk foreign key (policy_schedule_id) references policy_schedules (id)
);
