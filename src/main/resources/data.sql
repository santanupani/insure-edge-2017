insert into login (user_name, password, enabled, role) values('admin', 'secret', true, 'ROLE_ADMIN');

insert into products(name, description, image) values('Cash and Valuables in Transit', 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery – as per standard policy wording.', '/img/products/Cash and Valuables in Transit1.jpg');
insert into products(name, description, image) values('Static Cover Cash and Valuables', 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery – as per standard policy wording.','/img/products/Static Cover Cash and Valuables.jpg');
insert into products(name, description, image) values('Fine Art and Collectables', 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery – as per standard policy wording.', '/img/products/Fine Art and Collectables1.jpg');
insert into products(name, description, image) values('Static and In Transit Cover Cash and Valuables', 'Fire, Accidental damage, Hijacking, Theft & Armed Robbery – as per standard policy wording.', '/img/products/Static and In Transit Cover Cash and Valuables1.jpg'); 


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
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '10', 'What is the approximate distant ?'                 ,                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(10, '0km to 100km');
insert into answer_values(questionnaire_id, answer_value) values(10, '100km to 200km');
insert into answer_values(questionnaire_id, answer_value) values(10, '200km to 300km');
insert into answer_values(questionnaire_id, answer_value) values(10, '300km to 400km');
insert into answer_values(questionnaire_id, answer_value) values(10, '400km to 500km');
insert into answer_values(questionnaire_id, answer_value) values(10, '500km to 600km');
insert into answer_values(questionnaire_id, answer_value) values(10, '600km to 700km');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '11', 'Do you wish to insure ?',                 3,  null,    null);
insert into answer_values(questionnaire_id, answer_value) values(11, 'Cash');
insert into answer_values(questionnaire_id, answer_value) values(11, 'Cash and Coins ');
insert into answer_values(questionnaire_id, answer_value) values(11, 'Cash Coins and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(11, 'Cash Belonging to Standard Bank');
insert into answer_values(questionnaire_id, answer_value) values(11, 'Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(11, 'Cash Gold And Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(11, 'Cash and Cameos Managed Units');
insert into answer_values(questionnaire_id, answer_value) values(11, 'Cash and Gold Bullion');
insert into answer_values(questionnaire_id, answer_value) values(11, 'Cash and Bullion');
insert into answer_values(questionnaire_id, answer_value) values(11, 'Cash and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(11, 'Gold and Cash');
insert into answer_values(questionnaire_id, answer_value) values(11, 'Bullion');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('1', '12', 'Do you require SASRIA cover ?'                     ,                    4,    null,  null);



/*Product2*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '13', 'Maximum amount you wish to insure ?', 				    2, 	null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '14', 'Is the above amount the Total Full Value of the goods being stored ?',  4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '15', 'What is the Total Full Value of the goods being stored ?',             2,  2,  'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '16', 'Please give a full description of the Goods to be insured ?',          2,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '17', 'Details of Vault:(If applicable) ?',                                      4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '18', 'Will Goods be stored in Vault ?',                                       4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '19', 'Secured with re-enforced concrete on all sides as well as top and bottm ?',                 4,  6,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '20', 'Have seismic detectors fitted inside the Vault ?',                 4,  6,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '21', 'Have 24 hour inferred cameras and normal CCTV 24 hours recording ?',                 4,  6,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '22', 'Have an alarm and is it linked to an external response company ?',                 4,  6,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '23', 'Type of Storage ?',                 5,  6,    'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '24', 'Address ?',                 5,  6,    'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '25', 'What is the SABS safety category of the vault, if any ?',              3,  null,   null);
insert into answer_values(questionnaire_id, answer_value) values(25, 'No SABS grading');
insert into answer_values(questionnaire_id, answer_value) values(25, 'SABS Category I ');
insert into answer_values(questionnaire_id, answer_value) values(25, 'SABS Category II');
insert into answer_values(questionnaire_id, answer_value) values(25, 'SABS Category II HD grading');
insert into answer_values(questionnaire_id, answer_value) values(25, 'SABS Category II ADM');
insert into answer_values(questionnaire_id, answer_value) values(25, 'SABS Category II ADM Grading D3');
insert into answer_values(questionnaire_id, answer_value) values(25, 'SABS Category III grading');
insert into answer_values(questionnaire_id, answer_value) values(25, 'SABS Category IV grading');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '26', 'If secured by any other means not noted, please provide details in the adjacent fields ?',                 5,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '27', 'Do you wish to insure ?',                 3,  null,    null);
insert into answer_values(questionnaire_id, answer_value) values(27, 'Cash');
insert into answer_values(questionnaire_id, answer_value) values(27, 'Cash and Coins ');
insert into answer_values(questionnaire_id, answer_value) values(27, 'Cash Coins and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(27, 'Cash Belonging to Standard Bank');
insert into answer_values(questionnaire_id, answer_value) values(27, 'Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(27, 'Cash Gold And Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(27, 'Cash and Cameos Managed Units');
insert into answer_values(questionnaire_id, answer_value) values(27, 'Cash and Gold Bullion');
insert into answer_values(questionnaire_id, answer_value) values(27, 'Cash and Bullion');
insert into answer_values(questionnaire_id, answer_value) values(27, 'Cash and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(27, 'Gold and Cash');
insert into answer_values(questionnaire_id, answer_value) values(27, 'Bullion');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('2', '28', 'Do you require SASRIA cover ?',                 4,  null,    null);


/*Product3*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '29', 'What is the maximum amount you wish to insure ?',                      2, 	null, 	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '30', 'Is the above amount the total full value of the goods being moved ?',  4,	null, 	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '31', 'What is the total full value of the goods being moved ?',              2,   2, 		'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '32', 'For Additional Premium, Do you want first loss cover ?',               4,   null,	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '33', 'Do you use the service of a professional valuables carriers ?',        4,   null,	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '34', 'What is the name of the professional valuables carriers ?',            3,   5,	  	'true');
insert into answer_values(questionnaire_id, answer_value) values(34, 'Protea Coin Service');
insert into answer_values(questionnaire_id, answer_value) values(34, 'G4S Service');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '35', 'By whom and how are the valuables carried ?',                           5,	5, 		'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '36', 'How many times per week are the valuables carried ?',                   3,  null,  	null);
insert into answer_values(questionnaire_id, answer_value) values(36, '1 time /week');
insert into answer_values(questionnaire_id, answer_value) values(36, '2 times/week');
insert into answer_values(questionnaire_id, answer_value) values(36, '3 times/week');
insert into answer_values(questionnaire_id, answer_value) values(36, '4 times/week');
insert into answer_values(questionnaire_id, answer_value) values(36, '5 times/week');
insert into answer_values(questionnaire_id, answer_value) values(36, '6 times/week');
insert into answer_values(questionnaire_id, answer_value) values(36, '7 times/week');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '37', 'From where and to where are the valuables carried ?',                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(37, 'Port Elizabeth to Bloemfontein');
insert into answer_values(questionnaire_id, answer_value) values(37, 'Johannesburg to Durban');
insert into answer_values(questionnaire_id, answer_value) values(37, 'Polokwane to Mbombela');
insert into answer_values(questionnaire_id, answer_value) values(37, 'Rustenburg to Kimberley');
insert into answer_values(questionnaire_id, answer_value) values(37, 'Kimberley to Cape Town');
insert into answer_values(questionnaire_id, answer_value) values(37, 'Cape Town to Johannesburg ');
insert into answer_values(questionnaire_id, answer_value) values(37, 'Durban to Bloemfontein');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '38', 'What is the approximate distant ?'                 ,                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(38, '0km to 100km');
insert into answer_values(questionnaire_id, answer_value) values(38, '100km to 200km');
insert into answer_values(questionnaire_id, answer_value) values(38, '200km to 300km');
insert into answer_values(questionnaire_id, answer_value) values(38, '300km to 400km');
insert into answer_values(questionnaire_id, answer_value) values(38, '400km to 500km');
insert into answer_values(questionnaire_id, answer_value) values(38, '500km to 600km');
insert into answer_values(questionnaire_id, answer_value) values(38, '600km to 700km');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '39', 'Do you wish to insure ?',                 3,  null,    null);
insert into answer_values(questionnaire_id, answer_value) values(39, 'Cash');
insert into answer_values(questionnaire_id, answer_value) values(39, 'Cash and Coins ');
insert into answer_values(questionnaire_id, answer_value) values(39, 'Cash Coins and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(39, 'Cash Belonging to Standard Bank');
insert into answer_values(questionnaire_id, answer_value) values(39, 'Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(39, 'Cash Gold And Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(39, 'Cash and Cameos Managed Units');
insert into answer_values(questionnaire_id, answer_value) values(39, 'Cash and Gold Bullion');
insert into answer_values(questionnaire_id, answer_value) values(39, 'Cash and Bullion');
insert into answer_values(questionnaire_id, answer_value) values(39, 'Cash and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(39, 'Gold and Cash');
insert into answer_values(questionnaire_id, answer_value) values(39, 'Bullion');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('3', '40', 'Do you require SASRIA cover ?'                     ,                    4,    null,  null);


/*product4*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '41', 'What is the maximum amount you wish to insure ?',                      2, 	null, 	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '42', 'Is the above amount the total full value of the goods being moved ?',  4,	null, 	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '43', 'What is the total full value of the goods being moved ?',              2,   2, 		'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '44', 'For Additional Premium, Do you want first loss cover ?',               4,   null,	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '45', 'Do you use the service of a professional valuables carriers ?',        4,   null,	null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '46', 'What is the name of the professional valuables carriers ?',            3,   5,	  	'true');
insert into answer_values(questionnaire_id, answer_value) values(46, 'Protea Coin Service');
insert into answer_values(questionnaire_id, answer_value) values(46, 'G4S Service');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '47', 'By whom and how are the valuables carried ?',                           5,	5, 		'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '48', 'How many times per week are the valuables carried ?',                   3,  null,  	null);
insert into answer_values(questionnaire_id, answer_value) values(48, '1 time /week');
insert into answer_values(questionnaire_id, answer_value) values(48, '2 times/week');
insert into answer_values(questionnaire_id, answer_value) values(48, '3 times/week');
insert into answer_values(questionnaire_id, answer_value) values(48, '4 times/week');
insert into answer_values(questionnaire_id, answer_value) values(48, '5 times/week');
insert into answer_values(questionnaire_id, answer_value) values(48, '6 times/week');
insert into answer_values(questionnaire_id, answer_value) values(48, '7 times/week');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '49', 'From where and to where are the valuables carried ?',                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(49, 'Port Elizabeth to Bloemfontein');
insert into answer_values(questionnaire_id, answer_value) values(49, 'Johannesburg to Durban');
insert into answer_values(questionnaire_id, answer_value) values(49, 'Polokwane to Mbombela');
insert into answer_values(questionnaire_id, answer_value) values(49, 'Rustenburg to Kimberley');
insert into answer_values(questionnaire_id, answer_value) values(49, 'Kimberley to Cape Town');
insert into answer_values(questionnaire_id, answer_value) values(49, 'Cape Town to Johannesburg ');
insert into answer_values(questionnaire_id, answer_value) values(49, 'Durban to Bloemfontein');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '50', 'What is the approximate distant ?'                 ,                    3,    null,  null);
insert into answer_values(questionnaire_id, answer_value) values(50, '0km to 100km');
insert into answer_values(questionnaire_id, answer_value) values(50, '100km to 200km');
insert into answer_values(questionnaire_id, answer_value) values(50, '200km to 300km');
insert into answer_values(questionnaire_id, answer_value) values(50, '300km to 400km');
insert into answer_values(questionnaire_id, answer_value) values(50, '400km to 500km');
insert into answer_values(questionnaire_id, answer_value) values(50, '500km to 600km');
insert into answer_values(questionnaire_id, answer_value) values(50, '600km to 700km');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '51', 'Maximum amount you wish to insure ?', 				    2, 	null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '52', 'Is the above amount the Total Full Value of the goods being stored ?',  4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '53', 'What is the Total Full Value of the goods being stored ?',             2,  12,  'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '54', 'Please give a full description of the Goods to be insured ?',          2,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '55', 'Details of Vault:(If applicable) ?',                                      4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '56', 'What is the SABS safety category of the vault, if any ?',              3,  null,    null);
insert into answer_values(questionnaire_id, answer_value) values(56, 'No SABS grading');
insert into answer_values(questionnaire_id, answer_value) values(56, 'SABS Category I ');
insert into answer_values(questionnaire_id, answer_value) values(56, 'SABS Category II');
insert into answer_values(questionnaire_id, answer_value) values(56, 'SABS Category II HD grading');
insert into answer_values(questionnaire_id, answer_value) values(56, 'SABS Category II ADM');
insert into answer_values(questionnaire_id, answer_value) values(56, 'SABS Category II ADM Grading D3');
insert into answer_values(questionnaire_id, answer_value) values(56, 'SABS Category III grading');
insert into answer_values(questionnaire_id, answer_value) values(55, 'SABS Category IV grading');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '57', 'Will Goods be stored in Vault ?',                                       4,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '58', 'Secured with re-enforced concrete on all sides as well as top and bottm ?',                 4,  17,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '59', 'Have seismic detectors fitted inside the Vault ?',                 4,  17,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '60', 'Have 24 hour inferred cameras and normal CCTV 24 hours recording ?',                 4,  17,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '61', 'Have an alarm and is it linked to an external response company ?',                 4,  17,    'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '62', 'Type of Storage ?',                 5,  17,    'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '63', 'Address ?',                 5,  17,    'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '64', 'If secured by any other means not noted, please provide details in the adjacent fields ?',                 5,  null,    null);
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '65', 'Do you wish to insure ?',                 3,  null,    null);
insert into answer_values(questionnaire_id, answer_value) values(65, 'Cash');
insert into answer_values(questionnaire_id, answer_value) values(65, 'Cash and Coins ');
insert into answer_values(questionnaire_id, answer_value) values(65, 'Cash Coins and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(65, 'Cash Belonging to Standard Bank');
insert into answer_values(questionnaire_id, answer_value) values(65, 'Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(65, 'Cash Gold And Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(65, 'Cash and Cameos Managed Units');
insert into answer_values(questionnaire_id, answer_value) values(65, 'Cash and Gold Bullion');
insert into answer_values(questionnaire_id, answer_value) values(65, 'Cash and Bullion');
insert into answer_values(questionnaire_id, answer_value) values(65, 'Cash and Valuables');
insert into answer_values(questionnaire_id, answer_value) values(65, 'Gold and Cash');
insert into answer_values(questionnaire_id, answer_value) values(65, 'Bullion');;
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer)  values('4', '66', 'Do you require SASRIA cover ?',                 4,  null,    null);




