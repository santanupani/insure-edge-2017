/*login*/
insert into login (user_name, password, enabled, role) values('admin', 'secret', true, 'ROLE_ADMIN');

/*products*/
insert into products(name, description, image) values('Cash and Valuables in Transit', 'All risk cover for your cash and valuables whilst in transit.', '/img/products/Cash and Valuables in Transit.jpg');
insert into products(name, description, image) values('Static Cover Cash and Valuables', 'All risk cover for your cash and valuables whilst static/on-site in vault or safe.','/img/products/Static Cover Cash and Valuables1.jpg');
insert into products(name, description, image) values('Fine Art and Collectables', 'Tailored cover for your treasured possessions.', '/img/products/Fine Art and Collectables.jpg');
insert into products(name, description, image) values('Static and In Transit Cover Cash and Valuables', 'All risk cover for cash/valuables whilst in transit and static/on-site in vault or safe.', '/img/products/Static and In Transit Cover Cash and Valuables1.jpeg');


/*broker*/
insert into brokers(code, name, email) values ('00001', 'Coin Risk Management', 'binod.sethi@reverside.co.za');
insert into brokers(code, name, email) values ('00002', 'Admin Focus (Pty) Ltd', 'thabo.thulare@reverside.co.za');
insert into brokers(code, name, email) values ('00003', 'Optimum Financial Services Group', 'lesetja.konaite@reverside.co.za');
insert into brokers(code, name, email) values ('00004', 'Status Insurance Brokers (Pty) Ltd', 'info@statusib.co.za');
insert into brokers(code, name, email) values ('00005', 'Van Zyl Conradie Makelaars', 'elana@vzcon.co.za');
insert into brokers(code, name, email) values ('00006', 'Safari & Tourism Insurance Brokers (Pty) Ltd', 'yvonne@satib.co.za');
insert into brokers(code, name, email) values ('00007', 'Rens Kontant in Transito', 'renscit@vodamail.co.za');
insert into brokers(code, name, email) values ('00008', 'Multi Risk Admin (Pty) Ltd', 'queries@multirisk.co.za');
insert into brokers(code, name, email) values ('00009', 'Lazarus Dash and Associates (Pty) Ltd', 'Brian@lazdash.co.za');
insert into brokers(code, name, email) values ('00010', 'Willem Jacobus Nienaber', 'wjnienaber@telkomsa.net');
insert into brokers(code, name, email) values ('00011', 'Deposita Systems (Pty) Ltd', 'lizaan.botha@reverside.co.za');


/*answer_type*/
insert into answer_types(answer_type) values('text');
insert into answer_types(answer_type) values('number');
insert into answer_types(answer_type) values('select');
insert into answer_types(answer_type) values('checkbox');
insert into answer_types(answer_type) values('textarea');
insert into answer_types(answer_type) values('date');
insert into answer_types(answer_type) values('other');


/*Product1*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '1', 'What do you wish to insure ?',                 3,  null,    null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Cash');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Fine Art');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Bullion');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Precious Stones');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Other');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '2', 'Please specify what you wish to insure ?',                 7,  1,    'Other', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '3', 'What is the maximum amount you wish to insure ?',                      2, 	null, 	null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '4', 'Is the above amount the total full value of the goods being moved ?',  4,	null, 	null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '5', 'What is the total full value of the goods being moved ?',              2,   4, 		'false', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '6', 'Do you want first loss cover for additional premium ?',               4,   null,	null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '7', 'Do you use the service of a professional valuables carriers ?',        4,   null,	null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '8', 'What is the name of the professional valuables carriers ?',            3,   7,	  	'true', 'true');
insert into answer_values(questionnaire_id, answer_value) values(8, 'Protea Coin Service');
insert into answer_values(questionnaire_id, answer_value) values(8, 'G4S Service');
insert into answer_values(questionnaire_id, answer_value) values(8, 'Fidelity Service');
insert into answer_values(questionnaire_id, answer_value) values(8, 'Imeprial Logistics');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '9', 'Please specify by whom and how are the valuables carried :',                           1,	7, 		'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '10', 'How many times per week are the valuables carried ?',                   3,  null,  	null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(10, '1 time /week');
insert into answer_values(questionnaire_id, answer_value) values(10, '2 times/week');
insert into answer_values(questionnaire_id, answer_value) values(10, '3 times/week');
insert into answer_values(questionnaire_id, answer_value) values(10, '4 times/week');
insert into answer_values(questionnaire_id, answer_value) values(10, '5 times/week');
insert into answer_values(questionnaire_id, answer_value) values(10, '6 times/week');
insert into answer_values(questionnaire_id, answer_value) values(10, '7 times/week');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '11', 'Please provide from where the valuables carried ?',                    1,    null,  null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '12', 'Please provide to where are the valuables carried ?',                    1,    null,  null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '13', 'What is the approximate distance ?'                 ,                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(13, '0km to 100km');
insert into answer_values(questionnaire_id, answer_value) values(13, '100km to 200km');
insert into answer_values(questionnaire_id, answer_value) values(13, '200km to 300km');
insert into answer_values(questionnaire_id, answer_value) values(13, '300km to 400km');
insert into answer_values(questionnaire_id, answer_value) values(13, '400km to 500km');
insert into answer_values(questionnaire_id, answer_value) values(13, '500km to 600km');
insert into answer_values(questionnaire_id, answer_value) values(13, '600km to 700km');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '14', 'Please select the duration for your cover  ?'                     ,                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(14, 'Annually');
insert into answer_values(questionnaire_id, answer_value) values(14, 'Monthly');
insert into answer_values(questionnaire_id, answer_value) values(14, 'Once-Off');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '15', 'Please specify policy inception date for annual cover :'                     ,                    6,    14,  'Annually', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '16', 'Please specify policy inception date for monthly cover :'                     ,                    6,    14,  'Monthly', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '17', 'Please specify from date for once-off:'                     ,                    6,    14,  'Once-Off', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '18', 'Please specify end date for once-off :'                     ,                    6,    14,  'Once-Off', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '19', 'Are you currently insured ?'                     ,                      4,    null,  null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '20', 'Please provide details of your previous insurance company :',           1,    19,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '21', 'Please provide details of losses of valuable goods of insured/uninsured the past 5 years ?'                     ,                    4,    null,  null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '22', 'DATE :'                     ,                    6,    21,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '23', 'VALUE :'                     ,                    2,    21,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '24', 'TYPE OF LOSS :'                     ,                    1,    21,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '25', 'Do you have more history details of insured/uninsured goods :'                     ,                    4,    21,  'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '26', 'DATE :'                     ,                    6,    25,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '27', 'VALUE :'                     ,                    2,    25,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '28', 'TYPE OF LOSS :'                     ,                    1,    25,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '29', 'Do you have more history details of insured/uninsured goods :'                    ,                    4,    25,  'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '30', 'DATE :'                     ,                    6,    29,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '31', 'VALUE :'                     ,                    2,    29,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '32', 'TYPE OF LOSS :'                     ,                    1,    29,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '33', 'Do you have more history details of insured/uninsured goods :'                    ,                    4,    29,  'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '34', 'DATE :'                     ,                    6,    33,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '35', 'VALUE :'                     ,                    2,    33,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '36', 'TYPE OF LOSS :'                     ,                    1,    33,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '37', 'Do you require SASRIA cover ?'                     ,                    4,    null,  null, 'false');




/*Product2*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '38', 'What do you wish to insure ?',                 3,  null,    null, true);
insert into answer_values(questionnaire_id, answer_value) values(38, 'Cash');
insert into answer_values(questionnaire_id, answer_value) values(38, 'Fine Art');
insert into answer_values(questionnaire_id, answer_value) values(38, 'Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(38, 'Bullion');
insert into answer_values(questionnaire_id, answer_value) values(38, 'Precious Stones');
insert into answer_values(questionnaire_id, answer_value) values(38, 'Other');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '39', 'Please specify what you wish to insure ?',                 7,  1,    'Other', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '40', 'What is the maximum amount you wish to insure ?', 				    2, 	null,    null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '41', 'Is the above amount the total full value of the goods being stored ?',  4,  null,    null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '42', 'What is the total full value of the goods being stored ?',             2,  4,  'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '43', 'Please give a full description of the goods to be insured :',          1,  null,    null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '44', 'Please provide details of vault/safe :',                                      4,  null,    null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '45', 'Are the goods going to be stored in vault ?',                                       4,  null,    null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '46', 'Is the vault secured with reinforced concrete on all sides as well as top and bottom ?',                 4,  8,    'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '47', 'Is vault fitted with interior seismic detectors inside the vault ?',                 4,  8,    'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '48', 'Is vault equipped with 24 hour infra-red cameras and normal CCTV 24 hours recording ?',                 4,  8,    'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '49', 'Does the vault have an alarm and is it linked to an external response company ?',                 4,  8,    'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '50', 'What is the type of storage of vault ?',                1,  8,    'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '51', 'What is the address of the stored vault ?',                 1,  8,    'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '52', 'Please select the SABS safety category of the vault :',              3,  null,   null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(52, 'No SABS grading');
insert into answer_values(questionnaire_id, answer_value) values(52, 'SABS Category I ');
insert into answer_values(questionnaire_id, answer_value) values(52, 'SABS Category II');
insert into answer_values(questionnaire_id, answer_value) values(52, 'SABS Category II HD grading');
insert into answer_values(questionnaire_id, answer_value) values(52, 'SABS Category II ADM');
insert into answer_values(questionnaire_id, answer_value) values(52, 'SABS Category II ADM Grading D3');
insert into answer_values(questionnaire_id, answer_value) values(52, 'SABS Category III grading');
insert into answer_values(questionnaire_id, answer_value) values(52, 'SABS Category IV grading');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '53', 'Please select the duration for your cover  ?'                     ,                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(53, 'Annually');
insert into answer_values(questionnaire_id, answer_value) values(53, 'Monthly');
insert into answer_values(questionnaire_id, answer_value) values(53, 'Once-Off');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '54', 'Please specify policy inception date for annual cover :'                     ,                    6,    16,  'Annually', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '55', 'Please specify policy inception date for monthly cover :'                     ,                    6,    16,  'Monthly', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '56', 'Please specify from date for once-off:'                     ,                    6,    16,  'Once-Off', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '57', 'Please specify end date for once-off :'                     ,                    6,    16,  'Once-Off', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '58', 'Please specify if secured by any other means :',                 4,  null,    null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '59', 'Please provide details of how it is secured :',                 1,    21,    'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '60', 'Are you currently insured ?'                     ,                    4,    null,  null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '61', 'Please provide details of your previous insurance company ?'                     ,                    1,    23,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '62', 'Please provide details of losses of valuable goods of insured/uninsured the past 5 years ?'                     ,                    4,    null,  null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '63', 'DATE :'                    ,                    6,    25,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '64', 'VALUE :'                     ,                    2,    25,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '65', 'TYPE OF LOSS :'                     ,                    1,    25,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '66', 'Do you have more history details of insured/uninsured goods :'                     ,                    4,    25,  'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '67', 'DATE :'                     ,                    6,    29,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '68', 'VALUE :'                     ,                    2,    29,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '69', 'TYPE OF LOSS :'                     ,                    1,    29,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '70', 'Do you have more history details of insured/uninsured goods :'                     ,                    4,    29,  'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '71', 'DATE :'                     ,                    6,    33,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '72', 'VALUE :'                     ,                    2,    33,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '73', 'TYPE OF LOSS :'                     ,                    1,    33,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '74', 'Do you have more history details of insured/uninsured goods :'                     ,                    4,    33,  'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '75', 'DATE :'                     ,                    6,    37,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '76', 'VALUE :'                     ,                    2,    37,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '77', 'TYPE OF LOSS :'                     ,                    1,    37,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '78', 'Do you require SASRIA cover ?',                 4,  null,    null, 'false');


/*Product3*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '79', 'What do you wish to insure ?',                 3,  null,    null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(79, 'Cash');
insert into answer_values(questionnaire_id, answer_value) values(79, 'Fine Art');
insert into answer_values(questionnaire_id, answer_value) values(79, 'Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(79, 'Bullion');
insert into answer_values(questionnaire_id, answer_value) values(79, 'Precious Stones');
insert into answer_values(questionnaire_id, answer_value) values(79, 'Other');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '80', 'Please specify what you wish to insure ?',                 7,  1,    'Other', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '81', 'Please select the duration for your cover  ?'                     ,                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(81, 'Annually');
insert into answer_values(questionnaire_id, answer_value) values(81, 'Monthly');
insert into answer_values(questionnaire_id, answer_value) values(81, 'Once-Off');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '82', 'Please specify policy inception date for annual cover :'                     ,                    6,    3,  'Annually', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '83', 'Please specify policy inception date for monthly cover :'                     ,                    6,    3,  'Monthly', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '84', 'Please specify from date for once-off:'                     ,                    6,    3,  'Once-Off', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '85', 'Please specify end date for once-off :'                     ,                    6,    3,  'Once-Off', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '86', 'What is the maximum amount you wish to insure ?',                      2, 	null, 	null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '87', 'Is the above amount the total full value of the goods being moved ?',  4,	null, 	null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '88', 'What is the total full value of the goods being moved ?',              2,   9, 		'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '89', 'Do you want first loss cover for additional premium ?',               4,   null,	null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '90', 'Do you use the service of a professional valuables carriers ?',        4,   null,	null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '91', 'What is the name of the professional valuables carriers ?',            3,   12,	  	'true', 'true');
insert into answer_values(questionnaire_id, answer_value) values(91, 'Protea Coin Service');
insert into answer_values(questionnaire_id, answer_value) values(91, 'G4S Service');
insert into answer_values(questionnaire_id, answer_value) values(91, 'Fidelity Service');
insert into answer_values(questionnaire_id, answer_value) values(91, 'Imperial Logistics');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '92', 'Please specify by whom and how are the valuables carried :',                           1,	12, 		'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '93', 'How many times per week are the valuables carried ?',                   3,  null,  	null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(93, '1 time /week');
insert into answer_values(questionnaire_id, answer_value) values(93, '2 times/week');
insert into answer_values(questionnaire_id, answer_value) values(93, '3 times/week');
insert into answer_values(questionnaire_id, answer_value) values(93, '4 times/week');
insert into answer_values(questionnaire_id, answer_value) values(93, '5 times/week');
insert into answer_values(questionnaire_id, answer_value) values(93, '6 times/week');
insert into answer_values(questionnaire_id, answer_value) values(93, '7 times/week');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '94', 'Please provide from where the valuables carried ?',                    1,    null,  null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '95', 'Please provide to where are the valuables carried ?',                    1,    null,  null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '96', 'What is the approximate distance ?'                 ,                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(96, '0km to 100km');
insert into answer_values(questionnaire_id, answer_value) values(96, '100km to 200km');
insert into answer_values(questionnaire_id, answer_value) values(96, '200km to 300km');
insert into answer_values(questionnaire_id, answer_value) values(96, '300km to 400km');
insert into answer_values(questionnaire_id, answer_value) values(96, '400km to 500km');
insert into answer_values(questionnaire_id, answer_value) values(96, '500km to 600km');
insert into answer_values(questionnaire_id, answer_value) values(96, '600km to 700km');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '97', 'Are you currently insured ?'                     ,                    4,    null,  null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '98', 'Please provide details of your previous insurance company :'                     ,                    1,   19,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '99', 'Please provide details of losses of valuable goods of insured/uninsured the past 5 years ?'                     ,                    4,    null,  null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '100', 'DATE :'                    ,                    6,    21,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '101', 'VALUE :'                     ,                    2,    21,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '102', 'TYPE OF LOSS :'                     ,                    1,    21,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '103', 'Do you have more history details of insured/uninsured goods :'                     ,                    4,    21,  'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '104', 'DATE :'                     ,                    6,    25,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '105', 'VALUE :'                     ,                    2,    25,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '106', 'TYPE OF LOSS :'                     ,                    1,    25,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '107', 'Do you have more history details of insured/uninsured goods :'                     ,                    4,    25,  'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '108', 'DATE :'                     ,                    6,    29,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '109', 'VALUE :'                     ,                    2,    29,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '110', 'TYPE OF LOSS :'                     ,                    1,    29,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '111', 'Do you have more history details of insured/uninsured goods :'                     ,                    4,    29,  'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '112', 'DATE :'                     ,                    6,    33,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '113', 'VALUE :'                     ,                    2,    33,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '114', 'TYPE OF LOSS :'                     ,                    1,    33,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '115', 'Do you require SASRIA cover ?'                     ,                    4,    null,  null, 'false');


/*product4*/
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '116', 'What do you wish to insure ?',                 3,  null,    null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(116, 'Cash');
insert into answer_values(questionnaire_id, answer_value) values(116, 'Fine Art');
insert into answer_values(questionnaire_id, answer_value) values(116, 'Diamonds');
insert into answer_values(questionnaire_id, answer_value) values(116, 'Bullion');
insert into answer_values(questionnaire_id, answer_value) values(116, 'Precious Stones');
insert into answer_values(questionnaire_id, answer_value) values(116, 'Other');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '117', 'Please specify what you wish to insure ?',                 7,  1,    'Other', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '118', 'What is the maximum amount you wish to insure in Cash in transit ?',                      2, 	null, 	null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '119', 'Is the above amount the total full value of the goods being moved in  Cash in transit ?',  4,	null, 	null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '120', 'What is the total full value of the goods being moved in Cash in transit ?',              2,   4, 		'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '121', 'Do you want first loss cover for additional premium ?',               4,   null,	null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '122', 'Do you use the service of a professional valuables carriers ?',        4,   null,	null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '123', 'What is the name of the professional valuables carriers ?',            3,   7,	  	'true', 'true');
insert into answer_values(questionnaire_id, answer_value) values(123, 'Protea Coin Service');
insert into answer_values(questionnaire_id, answer_value) values(123, 'G4S Service');
insert into answer_values(questionnaire_id, answer_value) values(123, 'Fidelity Service');
insert into answer_values(questionnaire_id, answer_value) values(123, 'Imperial Logistics');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '124', 'Please specify by whom and how are the valuables carried :',                           1,	7, 		'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '125', 'Please select the duration for your cover  ?'                     ,                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(125, 'Annually');
insert into answer_values(questionnaire_id, answer_value) values(125, 'Monthly');
insert into answer_values(questionnaire_id, answer_value) values(125, 'Once-Off');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '126', 'Please specify policy inception date for annual cover :'                     ,                    6,    10,  'Annually', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '127', 'Please specify policy inception date for monthly cover :'                     ,                    6,    10,  'Monthly', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '128', 'Please specify from date for once-off:'                     ,                    6,    10,  'Once-Off', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '129', 'Please specify end date for once-off :'                     ,                    6,    10,  'Once-Off', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '130', 'How many times per week are the valuables carried ?',                   3,  null,  	null, true);
insert into answer_values(questionnaire_id, answer_value) values(130, '1 time /week');
insert into answer_values(questionnaire_id, answer_value) values(130, '2 times/week');
insert into answer_values(questionnaire_id, answer_value) values(130, '3 times/week');
insert into answer_values(questionnaire_id, answer_value) values(130, '4 times/week');
insert into answer_values(questionnaire_id, answer_value) values(130, '5 times/week');
insert into answer_values(questionnaire_id, answer_value) values(130, '6 times/week');
insert into answer_values(questionnaire_id, answer_value) values(130, '7 times/week');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '131', 'Please provide from where the valuables carried ?',                    1,    null,  null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '132', 'Please provide to where are the valuables carried ?',                    1,    null,  null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '133', 'What is the approximate distance ?'                 ,                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(133, '0km to 100km');
insert into answer_values(questionnaire_id, answer_value) values(133, '100km to 200km');
insert into answer_values(questionnaire_id, answer_value) values(133, '200km to 300km');
insert into answer_values(questionnaire_id, answer_value) values(133, '300km to 400km');
insert into answer_values(questionnaire_id, answer_value) values(133, '400km to 500km');
insert into answer_values(questionnaire_id, answer_value) values(133, '500km to 600km');
insert into answer_values(questionnaire_id, answer_value) values(133, '600km to 700km');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '134', 'What is the maximum amount you wish to insure in Static ?', 				    2, 	null,    null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '135', 'Is the above amount the total full value of the goods being stored in Static ?',  4,  null,    null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '136', 'What is the total full value of the goods being stored in Static ?',             2,  20,  false, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '137', 'Please give a full description of the goods to be insured ?',          1,  null,    null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '138', 'Please provide details of vault/safe ?',                                      4,  null,    null, 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '139', 'Please select the SABS safety category of the vault:',              3,  null,    null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(139, 'No SABS grading');
insert into answer_values(questionnaire_id, answer_value) values(139, 'SABS Category I ');
insert into answer_values(questionnaire_id, answer_value) values(139, 'SABS Category II');
insert into answer_values(questionnaire_id, answer_value) values(139, 'SABS Category II HD grading');
insert into answer_values(questionnaire_id, answer_value) values(139, 'SABS Category II ADM');
insert into answer_values(questionnaire_id, answer_value) values(139, 'SABS Category II ADM Grading D3');
insert into answer_values(questionnaire_id, answer_value) values(139, 'SABS Category III grading');
insert into answer_values(questionnaire_id, answer_value) values(139, 'SABS Category IV grading');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '140', 'Are the goods going to be stored in vault ?',                                       4,  null,    null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '141', 'Is the vault secured with reinforced concrete on all sides as well as top and bottom ?',                 4,  25,    'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '142', 'Is vault fitted with interior seismic detectors inside the vault ?',                 4,  25,    'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '143', 'Is vault equipped with 24 hour infra-red cameras and normal CCTV 24 hours recording ?',                 4,  25,    'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '144', 'Does the vault have an alarm and is it linked to an external response company ?',                 4,  25,    'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '145', 'What is the type of storage of vault ?',                 1,  25,    'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '146', 'What is the address of the stored vault?',                 1,  25,    'false', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '147', 'Please specify if secured by any other means :',                 4,  null,    null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '148', 'Please provide details of how it is secured :',                 1,    32,    'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '149', 'Are you currently insured ?'                     ,                    4,    null,  null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '150', 'Please provide details of your previous insurance company :'                     ,                    1,    34,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '151', 'Please provide details of losses of valuable goods of insured/uninsured the past 5 years ?'                     ,                    4,    null,  null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '152', 'DATE :'                     ,                    6,    36,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '153', 'VALUE :'                     ,                    2,    36,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '154', 'TYPE OF LOSS :'                     ,                    1,    36,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '155', 'Do you have more history details of insured/uninsured goods :'                     ,                    4,    36,  'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '156', 'DATE :'                     ,                    6,    40,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '157', 'VALUE :'                     ,                    2,    40,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '158', 'TYPE OF LOSS :'                     ,                    1,    40,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '159', 'Do you have more history details of insured/uninsured goods :'                     ,                    4,    40,  'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '160', 'DATE :'                     ,                    6,    44,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '161', 'VALUE :'                     ,                    2,    44,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '162', 'TYPE OF LOSS :'                     ,                    1,    44,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '163', 'Do you have more history details of insured/uninsured goods :'                     ,                    4,    44,  'true', 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '164', 'DATE :'                     ,                    6,    48,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '165', 'VALUE :'                     ,                    2,    48,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '166', 'TYPE OF LOSS :'                     ,                    1,    48,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '167', 'Do you require SASRIA cover ?',                 4,  null,    null, 'false');


insert into sub_agents(broker_id,first_name,middle_name,last_name,email) values(1,'Hentie','Hentie','Synder','hentie.synder@polygongroup.co.za');
insert into sub_agents(broker_id,first_name,middle_name,last_name,email) values(1,'Susan','Susan','Otto','susan.otto@polygongroup.co.za');

insert into underwriters(first_name,middle_name,last_name,email) values('Lessely','Lessley','Kruger','lessely.kruger@genric.co.za');
insert into underwriters(first_name,middle_name,last_name,email) values('Marissa','Marissa','Lehman','marissa@lehman@genric.co.za');

insert into bank_accounts(account_number, account_name, branch, bank_name, account_type)values('236777262','Thabo','5678','Standard Bank', 'Savings');
insert into bank_accounts(account_number, account_name, branch, bank_name, account_type)values('3425672872','Lenox','6756','ABSA', 'Transmission');
insert into bank_accounts(account_number, account_name, branch, bank_name, account_type)values('234565656','Binod','7889','FNB', 'Savings');


insert into contacts(street, code, suburb, work_tel_number, fax_number, email, contact_person) values('121 Fire Street','8909','Midrand','011 991 0000','086 575 7876','thabo@gmail.com','Thabo Thulare');
insert into contacts(street, code, suburb, work_tel_number, fax_number, email, contact_person) values('Pretoria Main Road','6765','Oliven','015 787 9788','086 671 7887','Lenox@reverside.co.za','Lenox');
insert into contacts(street, code, suburb, work_tel_number, fax_number, email, contact_person) values('Marshall Street','9866','Johanesburg CDB','012 876 7876','012 655 8987','binod@gmail.com','Binod Sethi');

insert into clients(bank_account_id, contact_id, company_name,reg_no, income_tax_number, designation, vat_number) values('1','1','Satyam Solutions','5TTDTWPY','234599','Owner','CAFRWHYAQ');
insert into clients(bank_account_id, contact_id, company_name,reg_no, income_tax_number, designation, vat_number) values('2','2','Pinacle Soluitons','567DC18BS','2345678','Proprieter','VAGFTEEEI');
insert into clients(bank_account_id, contact_id, company_name,reg_no, income_tax_number, designation, vat_number) values('3','3','Discovery Health','GFTER456DS','234599','Insurer','VAGFTWRDA');

insert into policies(reference,anniversary_date,sub_agent_id,client_id,underwriter_id,policy_inception_date,product_name,inception_date,renewal_date,underwriting_year,status,frequency,sasria_frequency,device,re_instatement,collect_by_debit_order,exclude_sasria,underwriter_fee,broker_fee,sum_insured,max_sum_insured,broker_commission,ua_commission,premium,sasria_premium,schedule_attaching,type_of_cover,subject_matter,excess_structure,special_condition,conveyances,geographical_duration,notes) 
values('2015-1202','2015-09-17',1,1,2,'2015-09-17','Cash and Valuables in Transit','2015-09-17','2015-09-17',2015,'Active','Monthly','N/A','Nedbank Cameo','',true,false,12.5,20.00,35000,45000,0.00,20.00,350.00,0.00,'1) SPECIALISED VALUABLES INSURANCE POLICY WORDING-GENERAL TERMS AND CONDITIONS\n2) POLYGON GENERAL COMPUTER NUCLEAR EXCEPTIONS\n3) POLYGON CASH AND VALUABLES IN TRANSIT WORDING\n4) VAULT AND STATIC RISK COVER WORDING','Theft, armed robbery, hijacking and accidental damage or damage as a result of any attempt theft of cash insured.','Cash','By product','As discussed','Voltage','Discussion Value','This policy has qualified for cover, but pending documentation');

insert into policies(reference,anniversary_date,sub_agent_id,client_id,underwriter_id,policy_inception_date,product_name,inception_date,renewal_date,underwriting_year,status,frequency,sasria_frequency,device,re_instatement,collect_by_debit_order,exclude_sasria,underwriter_fee,broker_fee,sum_insured,max_sum_insured,broker_commission,ua_commission,premium,sasria_premium,schedule_attaching,type_of_cover,subject_matter,excess_structure,special_condition,conveyances,geographical_duration,notes) 
values('2015-1203','2015-09-17',2,1,1,'2015-09-17','Cash and Valuables in Transit','2015-09-18','2015-09-18',2015,'Acive','Annually','N/A','Nedbank Cameo','',false,false,12.5,20.00,55000,65000,0.00,20.00,450.00,0.00,'1) SPECIALISED VALUABLES INSURANCE POLICY \n2) WORDING-GENERAL TERMS AND CONDITIONS','Theft, armed robbery, hijacking and accidental damage or damage as a result of any attempt theft of cash insured.','Art','By Artery','As discussed','Limit','Discussion Value','This policy has qualified for cover, await approval from Manager');

insert into policies(reference,anniversary_date,sub_agent_id,client_id,underwriter_id,policy_inception_date,product_name,inception_date,renewal_date,underwriting_year,status,frequency,sasria_frequency,device,re_instatement,collect_by_debit_order,exclude_sasria,underwriter_fee,broker_fee,sum_insured,max_sum_insured,broker_commission,ua_commission,premium,sasria_premium,schedule_attaching,type_of_cover,subject_matter,excess_structure,special_condition,conveyances,geographical_duration,notes) 
values('2015-1204','2015-09-17',1,1,1,'2015-09-17','Cash and Valuables in Transit','2015-09-19','2015-09-19',2015,'Acive','Declaration','N/A','Nedbank Cameo','',false,false,12.5,20.00,65000,75000,0.00,20.00,550.00,0.00,'1) SPECIALISED VALUABLES INSURANCE POLICY WORDING-GENERAL TERMS AND CONDITIONS','Theft, armed robbery, hijacking and accidental damage or damage as a result of any attempt theft of cash insured.','Art','By Artery','As discussed','Limit','Discussion Value','This policy has qualified for cover, await approval from Manager');

insert into policies(reference,anniversary_date,sub_agent_id,client_id,underwriter_id,policy_inception_date,product_name,inception_date,renewal_date,underwriting_year,status,frequency,sasria_frequency,device,re_instatement,collect_by_debit_order,exclude_sasria,underwriter_fee,broker_fee,sum_insured,max_sum_insured,broker_commission,ua_commission,premium,sasria_premium,schedule_attaching,type_of_cover,subject_matter,excess_structure,special_condition,conveyances,geographical_duration,notes) 
values('2015-1205','2015-09-17',2,1,1,'2015-09-17','Cash and Valuables in Transit','2015-09-20','2015-09-20',2015,'Acive','Short Term','N/A','Nedbank Cameo','',false,false,12.5,20.00,85000,95000,0.00,20.00,650.00,0.00,'1) SPECIALISED VALUABLES INSURANCE POLICY WORDING-GENERAL TERMS AND CONDITIONS','Theft, armed robbery, hijacking and accidental damage or damage as a result of any attempt theft of cash insured.','Art','By Artery','As discussed','Limit','Discussion Value','This policy has qualified for cover, await approval from Manager');

insert into policies(reference,anniversary_date,sub_agent_id,client_id,underwriter_id,policy_inception_date,product_name,inception_date,renewal_date,underwriting_year,status,frequency,sasria_frequency,device,re_instatement,collect_by_debit_order,exclude_sasria,underwriter_fee,broker_fee,sum_insured,max_sum_insured,broker_commission,ua_commission,premium,sasria_premium,schedule_attaching,type_of_cover,subject_matter,excess_structure,special_condition,conveyances,geographical_duration,notes) 
values('2015-1206','2015-09-17',1,2,1,'2015-09-17','Cash and Valuables in Transit','2015-09-21','2015-09-21',2015,'Acive','Once Off','N/A','Nedbank Cameo','',false,false,12.5,20.00,55000,25000,0.00,20.00,750.00,0.00,'1) SPECIALISED VALUABLES INSURANCE POLICY WORDING-GENERAL TERMS AND CONDITIONS','Theft, armed robbery, hijacking and accidental damage or damage as a result of any attempt theft of cash insured.','Art','By Artery','As discussed','Limit','Discussion Value','This policy has qualified for cover, await approval from Manager');

insert into policies(reference,anniversary_date,sub_agent_id,client_id,underwriter_id,policy_inception_date,product_name,inception_date,renewal_date,underwriting_year,status,frequency,sasria_frequency,device,re_instatement,collect_by_debit_order,exclude_sasria,underwriter_fee,broker_fee,sum_insured,max_sum_insured,broker_commission,ua_commission,premium,sasria_premium,schedule_attaching,type_of_cover,subject_matter,excess_structure,special_condition,conveyances,geographical_duration,notes) 
values('2015-1207','2015-09-17',2,2,1,'2015-09-17','Cash and Valuables in Transit','2015-09-22','2015-09-22',2015,'Acive','Monthly','N/A','Nedbank Cameo','',false,false,12.5,20.00,67000,87000,0.00,20.00,850.00,0.00,'1) SPECIALISED VALUABLES INSURANCE POLICY WORDING-GENERAL TERMS AND CONDITIONS','Theft, armed robbery, hijacking and accidental damage or damage as a result of any attempt theft of cash insured.','Art','By Artery','As discussed','Limit','Discussion Value','This policy has qualified for cover, await approval from Manager');

insert into policies(reference,anniversary_date,sub_agent_id,client_id,underwriter_id,policy_inception_date,product_name,inception_date,renewal_date,underwriting_year,status,frequency,sasria_frequency,device,re_instatement,collect_by_debit_order,exclude_sasria,underwriter_fee,broker_fee,sum_insured,max_sum_insured,broker_commission,ua_commission,premium,sasria_premium,schedule_attaching,type_of_cover,subject_matter,excess_structure,special_condition,conveyances,geographical_duration,notes) 
values('2015-1208','2015-09-17',1,2,1,'2015-09-17','Cash and Valuables in Transit','2015-09-23','2015-09-23',2015,'Acive','Annually','N/A','Nedbank Cameo','',false,false,12.5,20.00,23000,45000,0.00,20.00,950.00,0.00,'1) SPECIALISED VALUABLES INSURANCE POLICY WORDING-GENERAL TERMS AND CONDITIONS','Theft, armed robbery, hijacking and accidental damage or damage as a result of any attempt theft of cash insured.','Art','By Artery','As discussed','Limit','Discussion Value','This policy has qualified for cover, await approval from Manager');

insert into policies(reference,anniversary_date,sub_agent_id,client_id,underwriter_id,policy_inception_date,product_name,inception_date,renewal_date,underwriting_year,status,frequency,sasria_frequency,device,re_instatement,collect_by_debit_order,exclude_sasria,underwriter_fee,broker_fee,sum_insured,max_sum_insured,broker_commission,ua_commission,premium,sasria_premium,schedule_attaching,type_of_cover,subject_matter,excess_structure,special_condition,conveyances,geographical_duration,notes) 
values('2015-1209','2015-09-17',2,3,1,'2015-09-17','Cash and Valuables in Transit','2015-09-24','2015-09-24',2015,'Acive','Short Term','N/A','Nedbank Cameo','',false,false,12.5,20.00,12000,89000,0.00,20.00,250.00,0.00,'1) SPECIALISED VALUABLES INSURANCE POLICY WORDING-GENERAL TERMS AND CONDITIONS','Theft, armed robbery, hijacking and accidental damage or damage as a result of any attempt theft of cash insured.','Art','By Artery','As discussed','Limit','Discussion Value','This policy has qualified for cover, await approval from Manager');

insert into policies(reference,anniversary_date,sub_agent_id,client_id,underwriter_id,policy_inception_date,product_name,inception_date,renewal_date,underwriting_year,status,frequency,sasria_frequency,device,re_instatement,collect_by_debit_order,exclude_sasria,underwriter_fee,broker_fee,sum_insured,max_sum_insured,broker_commission,ua_commission,premium,sasria_premium,schedule_attaching,type_of_cover,subject_matter,excess_structure,special_condition,conveyances,geographical_duration,notes) 
values('2015-1210','2015-09-17',1,3,1,'2015-09-17','Cash and Valuables in Transit','2015-09-25','2015-09-25',2015,'Acive','Monthly','N/A','Nedbank Cameo','',false,false,12.5,20.00,65000,34000,0.00,20.00,350.00,0.00,'1) SPECIALISED VALUABLES INSURANCE POLICY WORDING-GENERAL TERMS AND CONDITIONS','Theft, armed robbery, hijacking and accidental damage or damage as a result of any attempt theft of cash insured.','Art','By Artery','As discussed','Limit','Discussion Value','This policy has qualified for cover, await approval from Manager');

insert into indemnity_options(policy_id,indemity_item_option,indemnity_value,sum_insured,premium)
values(1,'Policy Vault','Per vehicle per transit (6* weekly)',650000,2310);

insert into indemnity_options(policy_id,indemity_item_option,indemnity_value,sum_insured,premium)
values(2,'Policy Vault','Per vehicle(3* weekly)',750000,3070);

insert into indemnity_options(policy_id,indemity_item_option,indemnity_value,sum_insured,premium)
values(1,'Policy Limit','Per Cash per transit (4* weekly)',120000,4050);


insert into claim_types (claim_type) values('ONSITE ROBBERY');
insert into claim_types (claim_type) values('CROSS PAVEMENT ROBBERY');
insert into claim_types (claim_type) values('VEHICLE ROBBERY');
insert into claim_types (claim_type) values('SHORTAGES');

insert into claim_answer_types(claim_answer_type) values('text');
insert into claim_answer_types(claim_answer_type) values('number');
insert into claim_answer_types(claim_answer_type) values('select');
insert into claim_answer_types(claim_answer_type) values('checkbox');
insert into claim_answer_types(claim_answer_type) values('textarea');
insert into claim_answer_types(claim_answer_type) values('date');
insert into claim_answer_types(claim_answer_type) values('other');
insert into claim_answer_types(claim_answer_type) values('blob');



insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('1','1', 'Event', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('1','2', 'Discovery', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('1','3', 'Place where Loss/Damage occurred', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('1','4', 'Did the incident happen on a premises or in Transit', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(4, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(4, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('1','5', 'If on prenises, was it occupied at the time of the incident', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(5, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(5, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('1','6', 'Type of a loss', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(6, 'Theft');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(6, 'Robbery');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(6, 'Accidental');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(6, 'Other');
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','7', 'Describe fully how the loss or damage occured stating how (if appicaple) entry was gained to premises?', 5, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','8', 'If Loss/Damage caused by another party give a name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','9', 'If Loss/Damage caused by another party give a contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','10', 'If Loss/Damage caused by another party give a insurers name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','11', 'Security Company name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','12', 'Date Reported :', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','13', 'Security co case number', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','14', 'Investigation officer name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','15', 'Investigation officer contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','16', 'Police Station name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','17', 'Place : ', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','18', 'Date Reported ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','19', 'Case number ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','20', 'Valuables?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','21', 'Cash?', 2, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','22', 'When last valued ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','23', 'I/We hereby decrare the foregoing particulars to be true in every resperct?', 4, true);

insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','23', 'Please upload Investigation Report ', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','23', 'Please upload Confirmation Of Amount ', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','23', 'Please upload Quote', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','23', 'Please upload Photos', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','23', 'Please upload Armed Response / Alarm Report', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','23', 'Please upload Affidavit', 8, true);





insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','24', 'Event', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','25', 'Discovery', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','26', 'Place where Loss/Damage occurred', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','27', 'Did the incident happen on a premises or in Transit', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(27, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(27, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','28', 'If on prenises, was it occupied at the time of the incident', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(28, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(28, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','29', 'Type of a loss', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(29, 'Theft');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(29, 'Robbery');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(29, 'Accidental');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(29, 'Other');
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','30', 'Describe fully how the loss or damage occured stating how (if appicaple) entry was gained to premises?', 5, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','31', 'If Loss/Damage caused by another party give a name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','32', 'If Loss/Damage caused by another party give a contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','33', 'If Loss/Damage caused by another party give a insurers name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','34', 'Security Company name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','35', 'Date Reported :', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','36', 'Security co case number', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','37', 'Investigation officer name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','38', 'Investigation officer contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','39', 'Police Station name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','40', 'Place : ', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','41', 'Date Reported ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','42', 'Case number ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','43', 'Valuables?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','44', 'Cash?', 2, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','45', 'When last valued ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','46', 'I/We hereby decrare the foregoing particulars to be true in every resperct?', 4, true);

insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','47', 'Please upload Investigation Report ', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','48', 'Please upload Confirmation Of Amount ', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','49', 'Please upload Proof of pickup', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','50', 'Please upload Affidavit', 8, true);




insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','51', 'Event', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','52', 'Discovery', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','53', 'Place where Loss/Damage occurred', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','54', 'Did the incident happen on a premises or in Transit', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(54, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(54, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','55', 'If on prenises, was it occupied at the time of the incident', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(55, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(55, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','56', 'Type of a loss', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(56, 'Theft');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(56, 'Robbery');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(56, 'Accidental');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(56, 'Other');
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','57', 'Describe fully how the loss or damage occured stating how (if appicaple) entry was gained to premises?', 5, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','58', 'If Loss/Damage caused by another party give a name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','59', 'If Loss/Damage caused by another party give a contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','60', 'If Loss/Damage caused by another party give a insurers name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','61', 'Security Company name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','62', 'Date Reported :', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','63', 'Security co case number', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','64', 'Investigation officer name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','65', 'Investigation officer contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','66', 'Police Station name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','67', 'Place : ', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','68', 'Date Reported ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','69', 'Case number ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','70', 'Valuables?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','71', 'Cash?', 2, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','72', 'When last valued ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','73', 'I/We hereby decrare the foregoing particulars to be true in every resperct?', 4, true);

insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','74', 'Please upload Investigation Report ', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','75', 'Confirmation Of Amount ', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','76', 'Proof Of Pick up', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','77', 'Trans track Report', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','78', 'Affidavit', 8, true);




insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','79', 'Event', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','80', 'Discovery', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','81', 'Place where Loss/Damage occurred', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','82', 'Did the incident happen on a premises or in Transit', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(82, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(82, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','83', 'If on prenises, was it occupied at the time of the incident', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(83, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(83, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','84', 'Type of a loss', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(84, 'Theft');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(84, 'Robbery');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(84, 'Accidental');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(84, 'Other');
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','85', 'Describe fully how the loss or damage occured stating how (if appicaple) entry was gained to premises?', 5, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','86', 'If Loss/Damage caused by another party give a name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','87', 'If Loss/Damage caused by another party give a contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','88', 'If Loss/Damage caused by another party give a insurers name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','89', 'Security Company name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','90', 'Date Reported :', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','91', 'Security co case number', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','92', 'Investigation officer name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','93', 'Investigation officer contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','94', 'Police Station name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','95', 'Place : ', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','96', 'Date Reported ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','97', 'Case number ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','98', 'Valuables?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','99', 'Cash?', 2, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','100', 'When last valued ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','101', 'I/We hereby decrare the foregoing particulars to be true in every resperct?', 4, true);

insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','102', 'Please upload Investigation Report ', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','103', 'Please upload Confirmation Of Amount ', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','104', 'Please upload Proof of Pickup', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','105', 'Please upload Actual amont banked/counted: VMS or Teller Report', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','106', 'Please upload Trans track document (if possible) ', 8, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','107', 'Please upload Affidavit', 8, true);
