insert into login (user_name, password, enabled, role) values('admin', 'secret', true, 'ROLE_ADMIN');

insert into products(name, description, image) values('Cash and Valuables in Transit', 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery – as per standard policy wording.', '/img/products/Cash and Valuables in Transit.jpg');
insert into products(name, description, image) values('Static Cover Cash and Valuables', 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery – as per standard policy wording.','/img/products/Static Cover Cash and Valuables1.jpg');
insert into products(name, description, image) values('Fine Art and Collectables', 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery – as per standard policy wording.', '/img/products/Fine Art and Collectables.jpg');
insert into products(name, description, image) values('Static and In Transit Cover Cash and Valuables', 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery – as per standard policy wording.', '/img/products/Static and In Transit Cover Cash and Valuables.jpg'); 


/*broker*/
insert into brokers(code, name, email) values ('00001', 'Coin Risk Management', 'risk@coin.co.za');
insert into brokers(code, name, email) values ('00002', 'Admin Focus (Pty) Ltd', 'fanie@adminfocus.co.za');
insert into brokers(code, name, email) values ('00003', 'Optimum Financial Services Group', 'bertus@optimum-inc.co.za');
insert into brokers(code, name, email) values ('00004', 'Status Insurance Brokers (Pty) Ltd', 'info@statusib.co.za');
insert into brokers(code, name, email) values ('00005', 'Van Zyl Conradie Makelaars', 'elana@vzcon.co.za');
insert into brokers(code, name, email) values ('00006', 'Safari & Tourism Insurance Brokers (Pty) Ltd', 'yvonne@satib.co.za');
insert into brokers(code, name, email) values ('00007', 'Rens Kontant in Transito', 'renscit@vodamail.co.za');
insert into brokers(code, name, email) values ('00008', 'Multi Risk Admin (Pty) Ltd', 'queries@multirisk.co.za');
insert into brokers(code, name, email) values ('00009', 'Lazarus Dash and Associates (Pty) Ltd', 'Brian@lazdash.co.za');
insert into brokers(code, name, email) values ('00010', 'Willem Jacobus Nienaber', 'wjnienaber@telkomsa.net');
insert into brokers(code, name, email) values ('00011', 'Deposita Systems (Pty) Ltd', 'jennifer@bib.co.za');


/*answer_type*/
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
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '1', 'What do you wish to insure ?',                 3,  null,    null, 'false');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash and Coins ');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash Coins and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash Belonging to Standard Bank');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash Gold And Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash and Cameos Managed Units');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash and Gold Bullion');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash and Bullion');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Gold and Cash');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Bullion');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '2', 'What is the maximum amount you wish to insure ?',                      2, 	null, 	null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '3', 'Is the above amount the total full value of the goods being moved ?',  4,	null, 	null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '4', 'What is the total full value of the goods being moved ?',              2,   3, 		'false', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '5', 'For additional premium, do you want first loss cover ?',               4,   null,	null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '6', 'Do you use the service of a professional valuables carriers ?',        4,   null,	null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '7', 'What is the name of the professional valuables carriers ?',            3,   6,	  	'true', 'true');
insert into answer_values(questionnaire_id, answer_value) values(7, 'Protea Coin Service');
insert into answer_values(questionnaire_id, answer_value) values(7, 'G4S Service');
insert into answer_values(questionnaire_id, answer_value) values(7, 'Fidelity Service');
insert into answer_values(questionnaire_id, answer_value) values(7, 'Imeprial Logistics');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '8', 'By whom and how are the valuables carried ?',                           5,	6, 		'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '9', 'How many times per week are the valuables carried ?',                   3,  null,  	null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(9, '1 time /week');
insert into answer_values(questionnaire_id, answer_value) values(9, '2 times/week');
insert into answer_values(questionnaire_id, answer_value) values(9, '3 times/week');
insert into answer_values(questionnaire_id, answer_value) values(9, '4 times/week');
insert into answer_values(questionnaire_id, answer_value) values(9, '5 times/week');
insert into answer_values(questionnaire_id, answer_value) values(9, '6 times/week');
insert into answer_values(questionnaire_id, answer_value) values(9, '7 times/week');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '10', 'From where and to where are the valuables carried.',                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(10, 'Port Elizabeth to Bloemfontein');
insert into answer_values(questionnaire_id, answer_value) values(10, 'Johannesburg to Durban');
insert into answer_values(questionnaire_id, answer_value) values(10, 'Polokwane to Mbombela');
insert into answer_values(questionnaire_id, answer_value) values(10, 'Rustenburg to Kimberley');
insert into answer_values(questionnaire_id, answer_value) values(10, 'Kimberley to Cape Town');
insert into answer_values(questionnaire_id, answer_value) values(10, 'Cape Town to Johannesburg ');
insert into answer_values(questionnaire_id, answer_value) values(10, 'Durban to Bloemfontein');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '11', 'What is the approximate distance ?'                 ,                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(11, '0km to 100km');
insert into answer_values(questionnaire_id, answer_value) values(11, '100km to 200km');
insert into answer_values(questionnaire_id, answer_value) values(11, '200km to 300km');
insert into answer_values(questionnaire_id, answer_value) values(11, '300km to 400km');
insert into answer_values(questionnaire_id, answer_value) values(11, '400km to 500km');
insert into answer_values(questionnaire_id, answer_value) values(11, '500km to 600km');
insert into answer_values(questionnaire_id, answer_value) values(11, '600km to 700km');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '12', 'Do you require SASRIA cover ?'                     ,                    4,    null,  null, 'false');



/*Product2*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '13', 'What do you wish to insure ?',                 3,  null,    null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(13, 'Cash');
insert into answer_values(questionnaire_id, answer_value) values(13, 'Cash and Coins ');
insert into answer_values(questionnaire_id, answer_value) values(13, 'Cash Coins and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(13, 'Cash Belonging to Standard Bank');
insert into answer_values(questionnaire_id, answer_value) values(13, 'Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(13, 'Cash Gold And Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(13, 'Cash and Cameos Managed Units');
insert into answer_values(questionnaire_id, answer_value) values(13, 'Cash and Gold Bullion');
insert into answer_values(questionnaire_id, answer_value) values(13, 'Cash and Bullion');
insert into answer_values(questionnaire_id, answer_value) values(13, 'Cash and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(13, 'Gold and Cash');
insert into answer_values(questionnaire_id, answer_value) values(13, 'Bullion');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '14', 'What is the maximum amount you wish to insure ?', 				    2, 	null,    null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '15', 'Is the above amount the total full value of the goods being stored ?',  4,  null,    null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '16', 'What is the total full value of the goods being stored ?',             2,  3,  'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '17', 'Please give a full description of the goods to be insured ?',          2,  null,    null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '18', 'Please provide details of vault:(If applicable) ?',                                      4,  null,    null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '19', 'Is the goods going to be stored in vault ?',                                       4,  null,    null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '20', 'Is the vault secured with re-enforced concrete on all sides as well as top and bottm ?',                 4,  7,    'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '21', 'Is the vault having seismic detectors fitted inside the vault ?',                 4,  7,    'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '22', 'Is the vault having 24 hour inferred cameras and normal CCTV 24 hours recording ?',                 4,  7,    'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '23', 'Is the vault having an alarm and is it linked to an external response company ?',                 4,  7,    'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '24', 'What is the type of storage of vault ?',                 5,  7,    'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '25', 'What is the address of the stored vault ?',                 5,  7,    'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '26', 'What is the SABS safety category of the vault, if any ?',              3,  null,   null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(26, 'No SABS grading');
insert into answer_values(questionnaire_id, answer_value) values(26, 'SABS Category I ');
insert into answer_values(questionnaire_id, answer_value) values(26, 'SABS Category II');
insert into answer_values(questionnaire_id, answer_value) values(26, 'SABS Category II HD grading');
insert into answer_values(questionnaire_id, answer_value) values(26, 'SABS Category II ADM');
insert into answer_values(questionnaire_id, answer_value) values(26, 'SABS Category II ADM Grading D3');
insert into answer_values(questionnaire_id, answer_value) values(26, 'SABS Category III grading');
insert into answer_values(questionnaire_id, answer_value) values(26, 'SABS Category IV grading');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '27', 'If secured by any other means not noted, please provide details in the adjacent fields ?',                 5,  null,    null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '28', 'Do you require SASRIA cover ?',                 4,  null,    null, 'true');


/*Product3*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '29', 'What do you wish to insure ?',                 3,  null,    null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(29, 'Cash');
insert into answer_values(questionnaire_id, answer_value) values(29, 'Cash and Coins ');
insert into answer_values(questionnaire_id, answer_value) values(29, 'Cash Coins and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(29, 'Cash Belonging to Standard Bank');
insert into answer_values(questionnaire_id, answer_value) values(29, 'Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(29, 'Cash Gold And Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(29, 'Cash and Cameos Managed Units');
insert into answer_values(questionnaire_id, answer_value) values(29, 'Cash and Gold Bullion');
insert into answer_values(questionnaire_id, answer_value) values(29, 'Cash and Bullion');
insert into answer_values(questionnaire_id, answer_value) values(29, 'Cash and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(29, 'Gold and Cash');
insert into answer_values(questionnaire_id, answer_value) values(29, 'Bullion');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '30', 'What is the maximum amount you wish to insure ?',                      2, 	null, 	null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '31', 'Is the above amount the total full value of the goods being moved ?',  4,	null, 	null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '32', 'What is the total full value of the goods being moved ?',              2,   3, 		'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '33', 'For additional premium, do you want first loss cover ?',               4,   null,	null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '34', 'Do you use the service of a professional valuables carriers ?',        4,   null,	null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '35', 'What is the name of the professional valuables carriers ?',            3,   6,	  	'true', 'true');
insert into answer_values(questionnaire_id, answer_value) values(35, 'Protea Coin Service');
insert into answer_values(questionnaire_id, answer_value) values(35, 'G4S Service');
insert into answer_values(questionnaire_id, answer_value) values(35, 'Fidelity Service');
insert into answer_values(questionnaire_id, answer_value) values(35, 'Imperial Logistics');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '36', 'By whom and how are the valuables carried ?',                           5,	6, 		'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '37', 'How many times per week are the valuables carried ?',                   3,  null,  	null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(37, '1 time /week');
insert into answer_values(questionnaire_id, answer_value) values(37, '2 times/week');
insert into answer_values(questionnaire_id, answer_value) values(37, '3 times/week');
insert into answer_values(questionnaire_id, answer_value) values(37, '4 times/week');
insert into answer_values(questionnaire_id, answer_value) values(37, '5 times/week');
insert into answer_values(questionnaire_id, answer_value) values(37, '6 times/week');
insert into answer_values(questionnaire_id, answer_value) values(37, '7 times/week');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '38', 'From where and to where are the valuables carried.',                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(38, 'Port Elizabeth to Bloemfontein');
insert into answer_values(questionnaire_id, answer_value) values(38, 'Johannesburg to Durban');
insert into answer_values(questionnaire_id, answer_value) values(38, 'Polokwane to Mbombela');
insert into answer_values(questionnaire_id, answer_value) values(38, 'Rustenburg to Kimberley');
insert into answer_values(questionnaire_id, answer_value) values(38, 'Kimberley to Cape Town');
insert into answer_values(questionnaire_id, answer_value) values(38, 'Cape Town to Johannesburg ');
insert into answer_values(questionnaire_id, answer_value) values(38, 'Durban to Bloemfontein');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '39', 'What is the approximate distance ?'                 ,                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(39, '0km to 100km');
insert into answer_values(questionnaire_id, answer_value) values(39, '100km to 200km');
insert into answer_values(questionnaire_id, answer_value) values(39, '200km to 300km');
insert into answer_values(questionnaire_id, answer_value) values(39, '300km to 400km');
insert into answer_values(questionnaire_id, answer_value) values(39, '400km to 500km');
insert into answer_values(questionnaire_id, answer_value) values(39, '500km to 600km');
insert into answer_values(questionnaire_id, answer_value) values(39, '600km to 700km');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '40', 'Do you require SASRIA cover ?'                     ,                    4,    null,  null, 'true');


/*product4*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '41', 'What do you wish to insure ?',                 3,  null,    null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(41, 'Cash');
insert into answer_values(questionnaire_id, answer_value) values(41, 'Cash and Coins ');
insert into answer_values(questionnaire_id, answer_value) values(41, 'Cash Coins and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(41, 'Cash Belonging to Standard Bank');
insert into answer_values(questionnaire_id, answer_value) values(41, 'Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(41, 'Cash Gold And Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(41, 'Cash and Cameos Managed Units');
insert into answer_values(questionnaire_id, answer_value) values(41, 'Cash and Gold Bullion');
insert into answer_values(questionnaire_id, answer_value) values(41, 'Cash and Bullion');
insert into answer_values(questionnaire_id, answer_value) values(41, 'Cash and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(41, 'Gold and Cash');
insert into answer_values(questionnaire_id, answer_value) values(41, 'Bullion');;
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '42', 'What is the maximum amount you wish to insure in Cash in transit ?',                      2, 	null, 	null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '43', 'Is the above amount the total full value of the goods being moved in  Cash in transit ?',  4,	null, 	null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '44', 'What is the total full value of the goods being moved in Cash in transit ?',              2,   3, 		'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '45', 'For additional premium, do you want first loss cover ?',               4,   null,	null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '46', 'Do you use the service of a professional valuables carriers ?',        4,   null,	null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '47', 'What is the name of the professional valuables carriers ?',            3,   6,	  	'true', 'true');
insert into answer_values(questionnaire_id, answer_value) values(47, 'Protea Coin Service');
insert into answer_values(questionnaire_id, answer_value) values(47, 'G4S Service');
insert into answer_values(questionnaire_id, answer_value) values(47, 'Fidelity Service');
insert into answer_values(questionnaire_id, answer_value) values(47, 'Imperial Logistics');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '48', 'By whom and how are the valuables carried ?',                           5,	6, 		'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '49', 'How many times per week are the valuables carried ?',                   3,  null,  	null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(49, '1 time /week');
insert into answer_values(questionnaire_id, answer_value) values(49, '2 times/week');
insert into answer_values(questionnaire_id, answer_value) values(49, '3 times/week');
insert into answer_values(questionnaire_id, answer_value) values(49, '4 times/week');
insert into answer_values(questionnaire_id, answer_value) values(49, '5 times/week');
insert into answer_values(questionnaire_id, answer_value) values(49, '6 times/week');
insert into answer_values(questionnaire_id, answer_value) values(49, '7 times/week');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '50', 'From where and to where are the valuables carried.',                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(50, 'Port Elizabeth to Bloemfontein');
insert into answer_values(questionnaire_id, answer_value) values(50, 'Johannesburg to Durban');
insert into answer_values(questionnaire_id, answer_value) values(50, 'Polokwane to Mbombela');
insert into answer_values(questionnaire_id, answer_value) values(50, 'Rustenburg to Kimberley');
insert into answer_values(questionnaire_id, answer_value) values(50, 'Kimberley to Cape Town');
insert into answer_values(questionnaire_id, answer_value) values(50, 'Cape Town to Johannesburg ');
insert into answer_values(questionnaire_id, answer_value) values(50, 'Durban to Bloemfontein');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '51', 'What is the approximate distance ?'                 ,                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(51, '0km to 100km');
insert into answer_values(questionnaire_id, answer_value) values(51, '100km to 200km');
insert into answer_values(questionnaire_id, answer_value) values(51, '200km to 300km');
insert into answer_values(questionnaire_id, answer_value) values(51, '300km to 400km');
insert into answer_values(questionnaire_id, answer_value) values(51, '400km to 500km');
insert into answer_values(questionnaire_id, answer_value) values(51, '500km to 600km');
insert into answer_values(questionnaire_id, answer_value) values(51, '600km to 700km');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '52', 'What is the maximum amount you wish to insure in Static ?', 				    2, 	null,    null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '53', 'Is the above amount the total full value of the goods being stored in Static ?',  4,  null,    null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '54', 'What is the Total Full Value of the goods being stored in Static ?',             2,  13,  'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '55', 'Please give a full description of the goods to be insured ?',          2,  null,    null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '56', 'Please provide details of vault:(If applicable) ?',                                      4,  null,    null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '57', 'What is the SABS safety category of the vault, if any ?',              3,  null,    null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(57, 'No SABS grading');
insert into answer_values(questionnaire_id, answer_value) values(57, 'SABS Category I ');
insert into answer_values(questionnaire_id, answer_value) values(57, 'SABS Category II');
insert into answer_values(questionnaire_id, answer_value) values(57, 'SABS Category II HD grading');
insert into answer_values(questionnaire_id, answer_value) values(57, 'SABS Category II ADM');
insert into answer_values(questionnaire_id, answer_value) values(57, 'SABS Category II ADM Grading D3');
insert into answer_values(questionnaire_id, answer_value) values(57, 'SABS Category III grading');
insert into answer_values(questionnaire_id, answer_value) values(57, 'SABS Category IV grading');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '58', 'Is the goods going to be stored in vault ?',                                       4,  null,    null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '59', 'Is the vault secured with re-enforced concrete on all sides as well as top and bottm ?',                 4,  18,    'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '60', 'Is the vault having seismic detectors fitted inside the vault ?',                 4,  18,    'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '61', 'Is the vault having 24 hour inferred cameras and normal CCTV 24 hours recording ?',                 4,  18,    'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '62', 'Is the vault having an alarm and is it linked to an external response company ?',                 4,  18,    'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '63', 'What is the type of storage of vault ?',                 5,  18,    'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '64', 'What is the address of the stored vault?',                 5,  18,    'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '65', 'If secured by any other means not noted, please provide details in the adjacent fields ?',                 5,  null,    null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '66', 'Do you require SASRIA cover ?',                 4,  null,    null, 'true');




