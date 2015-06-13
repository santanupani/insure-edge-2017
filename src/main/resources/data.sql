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


/*Product1*/
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



/*Product2*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '12', 'Maximum amount you wish to insure ?', 				    2, 	null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '13', 'Is the above amount the Total Full Value of the goods being stored ?',  4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '14', 'What is the Total Full Value of the goods being stored ?',             2,  2,  'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '15', 'Please give a full description of the Goods to be insured ?',          2,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '16', 'Details of Vault:(If applicable) ?',                                      4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '17', 'Will Goods be stored in Vault ?',                                       4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '18', 'Secured with re-enforced concrete on all sides as well as top and bottm ?',                 4,  6,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '19', 'Have seismic detectors fitted inside the Vault ?',                 4,  6,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '20', 'Have 24 hour inferred cameras and normal CCTV 24 hours recording ?',                 4,  6,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '21', 'Have an alarm and is it linked to an external response company ?',                 4,  6,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '22', 'Type of Storage ?',                 5,  6,    'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '23', 'Address ?',                 5,  6,    'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '24', 'What is the SABS safety category of the vault, if any ?',              3,  null,   null);
insert into answer_values(questionnaire_id, answer_value) values(24, 'No SABS grading');
insert into answer_values(questionnaire_id, answer_value) values(24, 'SABS Category I ');
insert into answer_values(questionnaire_id, answer_value) values(24, 'SABS Category II');
insert into answer_values(questionnaire_id, answer_value) values(24, 'SABS Category II HD grading');
insert into answer_values(questionnaire_id, answer_value) values(24, 'SABS Category II ADM');
insert into answer_values(questionnaire_id, answer_value) values(24, 'SABS Category II ADM Grading D3');
insert into answer_values(questionnaire_id, answer_value) values(24, 'SABS Category III grading');
insert into answer_values(questionnaire_id, answer_value) values(24, 'SABS Category IV grading');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '25', 'If secured by any other means not noted, please provide details in the adjacent fields ?',                 5,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '26', 'Do you require SASRIA cover ?',                 4,  null,    null);


/*Product3*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '27', 'What is the maximum amount you wish to insure ?',                      2, 	null, 	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '28', 'Is the above amount the total full value of the goods being moved ?',  4,	null, 	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '29', 'What is the total full value of the goods being moved ?',              2,   2, 		'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '30', 'For Additional Premium, Do you want first loss cover ?',               4,   null,	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '5', 'Do you use the service of a professional valuables carriers ?',        4,   null,	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '32', 'What is the name of the professional valuables carriers ?',            3,   5,	  	'true');
insert into answer_values(questionnaire_id, answer_value) values(32, 'Protea Coin Service');
insert into answer_values(questionnaire_id, answer_value) values(32, 'G4S Service');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '33', 'By whom and how are the valuables carried ?',                           5,	5, 		'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '34', 'How many times per week are the valuables carried ?',                   3,  null,  	null);
insert into answer_values(questionnaire_id, answer_value) values(34, '1 time /week');
insert into answer_values(questionnaire_id, answer_value) values(34, '2 times/week');
insert into answer_values(questionnaire_id, answer_value) values(34, '3 times/week');
insert into answer_values(questionnaire_id, answer_value) values(34, '4 times/week');
insert into answer_values(questionnaire_id, answer_value) values(34, '5 times/week');
insert into answer_values(questionnaire_id, answer_value) values(34, '6 times/week');
insert into answer_values(questionnaire_id, answer_value) values(34, '7 times/week');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '35', 'From where and to where are the valuables carried ?',                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(35, 'Port Elizabeth to Bloemfontein');
insert into answer_values(questionnaire_id, answer_value) values(35, 'Johannesburg to Durban');
insert into answer_values(questionnaire_id, answer_value) values(35, 'Polokwane to Mbombela');
insert into answer_values(questionnaire_id, answer_value) values(35, 'Rustenburg to Kimberley');
insert into answer_values(questionnaire_id, answer_value) values(35, 'Kimberley to Cape Town');
insert into answer_values(questionnaire_id, answer_value) values(35, 'Cape Town to Johannesburg ');
insert into answer_values(questionnaire_id, answer_value) values(35, 'Durban to Bloemfontein');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '36', 'What is the approximate distant?'                 ,                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(36, '0km to 100km');
insert into answer_values(questionnaire_id, answer_value) values(36, '100km to 200km');
insert into answer_values(questionnaire_id, answer_value) values(36, '200km to 300km');
insert into answer_values(questionnaire_id, answer_value) values(36, '300km to 400km');
insert into answer_values(questionnaire_id, answer_value) values(36, '400km to 500km');
insert into answer_values(questionnaire_id, answer_value) values(36, '500km to 600km');
insert into answer_values(questionnaire_id, answer_value) values(36, '600km to 700km');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '37', 'Do you require SASRIA cover?'                     ,                    4,    null,  null);


/*product4*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '38', 'What is the maximum amount you wish to insure ?',                      2, 	null, 	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '39', 'Is the above amount the total full value of the goods being moved ?',  4,	null, 	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '40', 'What is the total full value of the goods being moved ?',              2,   2, 		'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '41', 'For Additional Premium, Do you want first loss cover ?',               4,   null,	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '42', 'Do you use the service of a professional valuables carriers ?',        4,   null,	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '43', 'What is the name of the professional valuables carriers ?',            3,   5,	  	'true');
insert into answer_values(questionnaire_id, answer_value) values(43, 'Protea Coin Service');
insert into answer_values(questionnaire_id, answer_value) values(43, 'G4S Service');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '44', 'By whom and how are the valuables carried ?',                           5,	5, 		'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '45', 'How many times per week are the valuables carried ?',                   3,  null,  	null);
insert into answer_values(questionnaire_id, answer_value) values(45, '1 time /week');
insert into answer_values(questionnaire_id, answer_value) values(45, '2 times/week');
insert into answer_values(questionnaire_id, answer_value) values(45, '3 times/week');
insert into answer_values(questionnaire_id, answer_value) values(45, '4 times/week');
insert into answer_values(questionnaire_id, answer_value) values(45, '5 times/week');
insert into answer_values(questionnaire_id, answer_value) values(45, '6 times/week');
insert into answer_values(questionnaire_id, answer_value) values(45, '7 times/week');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '46', 'From where and to where are the valuables carried ?',                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(46, 'Port Elizabeth to Bloemfontein');
insert into answer_values(questionnaire_id, answer_value) values(46, 'Johannesburg to Durban');
insert into answer_values(questionnaire_id, answer_value) values(46, 'Polokwane to Mbombela');
insert into answer_values(questionnaire_id, answer_value) values(46, 'Rustenburg to Kimberley');
insert into answer_values(questionnaire_id, answer_value) values(46, 'Kimberley to Cape Town');
insert into answer_values(questionnaire_id, answer_value) values(46, 'Cape Town to Johannesburg ');
insert into answer_values(questionnaire_id, answer_value) values(46, 'Durban to Bloemfontein');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '47', 'What is the approximate distant?'                 ,                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(47, '0km to 100km');
insert into answer_values(questionnaire_id, answer_value) values(47, '100km to 200km');
insert into answer_values(questionnaire_id, answer_value) values(47, '200km to 300km');
insert into answer_values(questionnaire_id, answer_value) values(47, '300km to 400km');
insert into answer_values(questionnaire_id, answer_value) values(47, '400km to 500km');
insert into answer_values(questionnaire_id, answer_value) values(47, '500km to 600km');
insert into answer_values(questionnaire_id, answer_value) values(47, '600km to 700km');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '48', 'Maximum amount you wish to insure ?', 				    2, 	null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '49', 'Is the above amount the Total Full Value of the goods being stored ?',  4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '50', 'What is the Total Full Value of the goods being stored ?',             2,  12,  'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '51', 'Please give a full description of the Goods to be insured ?',          2,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '52', 'Details of Vault:(If applicable) ?',                                      4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '53', 'What is the SABS safety category of the vault, if any ?',              3,  null,    null);
insert into answer_values(questionnaire_id, answer_value) values(53, 'No SABS grading');
insert into answer_values(questionnaire_id, answer_value) values(53, 'SABS Category I ');
insert into answer_values(questionnaire_id, answer_value) values(53, 'SABS Category II');
insert into answer_values(questionnaire_id, answer_value) values(53, 'SABS Category II HD grading');
insert into answer_values(questionnaire_id, answer_value) values(53, 'SABS Category II ADM');
insert into answer_values(questionnaire_id, answer_value) values(53, 'SABS Category II ADM Grading D3');
insert into answer_values(questionnaire_id, answer_value) values(53, 'SABS Category III grading');
insert into answer_values(questionnaire_id, answer_value) values(53, 'SABS Category IV grading');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '54', 'Will Goods be stored in Vault ?',                                       4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '55', 'Secured with re-enforced concrete on all sides as well as top and bottm ?',                 4,  17,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '56', 'Have seismic detectors fitted inside the Vault ?',                 4,  17,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '57', 'Have 24 hour inferred cameras and normal CCTV 24 hours recording ?',                 4,  17,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '58', 'Have an alarm and is it linked to an external response company ?',                 4,  17,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '59', 'Type of Storage ?',                 5,  17,    'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '60', 'Address ?',                 5,  17,    'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '61', 'If secured by any other means not noted, please provide details in the adjacent fields ?',                 5,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '62', 'Do you require SASRIA cover ?',                 4,  null,    null);

/*broker*/
insert into broker(broker_code, broker_name, broker_email) values ('00001', 'Coin Risk Management', 'risk@coin.co.za');
insert into broker(broker_code, broker_name, broker_email) values ('00002', 'Admin Focus (Pty) Ltd', 'admin@coin.co.za');


