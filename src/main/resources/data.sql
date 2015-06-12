insert into login (user_name, password, enabled, role) values('admin', 'secret', true, 'ROLE_ADMIN');

insert into products(name, description, image) values('Cash and Valuables in Transit', 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery – as per standard policy wording.', '/img/products/Cash and Valuables in Transit1.jpg');
insert into products(name, description, image) values('Static Cover Cash and Valuables', 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery – as per standard policy wording.','/img/products/Static Cover Cash and Valuables.jpg');
insert into products(name, description, image) values('Fine Art and Collectables', 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery – as per standard policy wording.', '/img/products/Fine Art and Collectables1.jpg');
insert into products(name, description, image) values('Static and In Transit Cover Cash and Valuables', 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery – as per standard policy wording.', '/img/products/Static and In Transit Cover Cash and Valuables1.jpg'); 

insert into answer_types(answer_type) values('text');
insert into answer_types(answer_type) values('number');
insert into answer_types(answer_type) values('select');
insert into answer_types(answer_type) values('checkbox');
insert into answer_types(answer_type) values('textarea');


insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, if_answer)  values('1', '1', 'What is your full name ?', 1, null, null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, if_answer)  values('1', '2', 'What is your gender ?',    3, null, null);
insert into answer_values(questionnaire_id, answer_value) values(2, 'Female');
insert into answer_values(questionnaire_id, answer_value) values(2, 'Male');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, if_answer)  values('1', '3', 'What is your salary ?',    2, 2,    'Female');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, if_answer)  values('1', '4', 'What is your age ?',       2, 2,    'Male');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, if_answer)  values('1', '5', 'Do you have any criminal record ?', 4, null, null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, if_answer)  values('1', '6', 'Please provide details of your crime', 5, 5, 'true');




