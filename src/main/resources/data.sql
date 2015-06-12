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


/*insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '1', 'What is your full name ?', 				1, 	null, null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '2', 'What is your gender ?',    				3, 	null, null);
insert into answer_values(questionnaire_id, answer_value) values(2, 'Female');
insert into answer_values(questionnaire_id, answer_value) values(2, 'Male');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '3', 'What is your salary ?',    				2, 	2,    'Female');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '4', 'What is your age ?',       				2, 	2,    'Male');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '5', 'Do you have any criminal record ?', 	4, 	null,  null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '6', 'Please provide details of your crime', 	5, 	5,     'true');*/



insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '1', 'What is the maximum amount you wish to insure ?',                      2, 	null, 	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '2', 'Is the above amount the total full value of the goods being moved ?',  4,	null, 	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '3', 'What is the total full value of the goods being moved ?',              2,   2, 		'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '4', 'For Additional Premium, Do you want first loss cover ?',               4,   null,	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '5', 'Do you use the service of a professional valuables carriers ?',        4,   null,	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '6', 'What is the name of the professional valuables carriers ?',            3,   5,	  	'true');
insert into answer_values(questionnaire_id, answer_value) values(6, 'Protea Coin Service');
insert into answer_values(questionnaire_id, answer_value) values(6, 'G4S Service');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '7', 'By whom and how are the valuables carried ?',                           5,	5, 		'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '8', 'How many times per week are the valuables carried ?',                   3,  null,  	null);
insert into answer_values(questionnaire_id, answer_value) values(8, '1 time /week');
insert into answer_values(questionnaire_id, answer_value) values(8, '2 times/week');
insert into answer_values(questionnaire_id, answer_value) values(8, '3 times/week');
insert into answer_values(questionnaire_id, answer_value) values(8, '4 times/week');
insert into answer_values(questionnaire_id, answer_value) values(8, '5 times/week');
insert into answer_values(questionnaire_id, answer_value) values(8, '6 times/week');
insert into answer_values(questionnaire_id, answer_value) values(8, '7 times/week');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '9', 'From where and to where are the valuables carried ?',                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(9, 'Port Elizabeth to Bloemfontein');
insert into answer_values(questionnaire_id, answer_value) values(9, 'Johannesburg to Durban');
insert into answer_values(questionnaire_id, answer_value) values(9, 'Polokwane to Mbombela');
insert into answer_values(questionnaire_id, answer_value) values(9, 'Rustenburg to Kimberley');
insert into answer_values(questionnaire_id, answer_value) values(9, 'Kimberley to Cape Town');
insert into answer_values(questionnaire_id, answer_value) values(9, 'Cape Town to Johannesburg ');
insert into answer_values(questionnaire_id, answer_value) values(9, 'Durban to Bloemfontein');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '10', 'What is the approximate distant?'                 ,                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(10, '0km to 100km');
insert into answer_values(questionnaire_id, answer_value) values(10, '100km to 200km');
insert into answer_values(questionnaire_id, answer_value) values(10, '200km to 300km');
insert into answer_values(questionnaire_id, answer_value) values(10, '300km to 400km');
insert into answer_values(questionnaire_id, answer_value) values(10, '400km to 500km');
insert into answer_values(questionnaire_id, answer_value) values(10, '500km to 600km');
insert into answer_values(questionnaire_id, answer_value) values(10, '600km to 700km');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '11', 'Do you require SASRIA cover?'                     ,                    4,    null,  null);




insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '12', 'Maximum amount you wish to insure ?', 				    2, 	null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '13', 'Is the above amount the Total Full Value of the goods being stored ?', 4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '14', 'What is the Total Full Value of the goods being stored ?',             2,  2,  'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '15', 'Please give a full description of the Goods to be insured ?',            2,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '16', 'What is the SABS safety category of the vault, if any ?',                 3,  null,    null);
insert into answer_values(questionnaire_id, answer_value) values(16, 'No SABS grading');
insert into answer_values(questionnaire_id, answer_value) values(16, 'SABS Category I ');
insert into answer_values(questionnaire_id, answer_value) values(16, 'SABS Category II');
insert into answer_values(questionnaire_id, answer_value) values(16, 'SABS Category II HD grading');
insert into answer_values(questionnaire_id, answer_value) values(16, 'SABS Category II ADM');
insert into answer_values(questionnaire_id, answer_value) values(16, 'SABS Category II ADM Grading D3');
insert into answer_values(questionnaire_id, answer_value) values(16, 'SABS Category III grading');
insert into answer_values(questionnaire_id, answer_value) values(16, 'SABS Category IV grading');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '17', 'Details of Vault:(If applicable) ?',                                      4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '18', 'Will Goods be stored in Vault ?',                                       4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '19', 'Secured with re-enforced concrete on all sides as well as top and bottm ?',                 4,  7,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '20', 'Have seismic detectors fitted inside the Vault ?',                 4,  7,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '21', 'Have 24 hour inferred cameras and normal CCTV 24 hours recording ?',                 4,  7,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '22', 'Have an alarm and is it linked to an external response company ?',                 4,  7,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '23', 'Type of Storage ?',                 5,  7,    'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '24', 'Address ?',                 5,  7,    'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '25', 'If secured by any other means not noted, please provide details in the adjacent fields ?',                 5,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '26', 'Do you require SASRIA cover ?',                 4,  null,    null);
