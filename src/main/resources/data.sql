--use polygon;

/*login*/
insert into login (user_name, password, first_name, last_name, enabled, role) values('admin', 'secret', 'admin','admin', true, 'ROLE_ADMIN');
insert into login (user_name, password, first_name, last_name, enabled, role) values('broker', 'secret', 'Hentie','Snyder', true, 'BROKER');
insert into login (user_name, password, first_name, last_name, enabled, role) values('underwritter', 'secret', 'Eric','Lehmann', true, 'UNDERWRITTER');

/*products*/
insert into products(name, description, image) values('Cash and Valuables in Transit', 'All risk cover for your cash and valuables whilst in transit.', '/img/products/Cash and Valuables in Transit.jpg');
insert into products(name, description, image) values('Static Cover Cash and Valuables', 'All risk cover for your cash and valuables whilst static/on-site in vault or safe.','/img/products/Static Cover Cash and Valuables1.jpg');
insert into products(name, description, image) values('Fine Art and Collectables', 'Tailored cover for your treasured possessions.', '/img/products/Fine Art and Collectables.jpg');
insert into products(name, description, image) values('Static and In Transit Cover Cash and Valuables', 'All risk cover for cash/valuables whilst in transit and static/on-site in vault or safe.', '/img/products/Static and In Transit Cover Cash and Valuables1.jpeg');
/*jaspers*/
insert into jasper_images(name, image) values('polygon-logo', 'reports/polygon-logo.jpg');
insert into jasper_images(name, image) values('polygon-footer','reports/polygon-footer.png');
insert into jasper_images(name, image) values('genric-logo', 'reports/genric-logo.jpg');
insert into jasper_images(name, image) values('polygon-sched', 'reports/polygon-sched.jpg');

/*broker*/
insert into brokers(code, name, email) values ('00001', 'Blue Quanta', 'polygon.broker@gmail.com');
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

insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '1', 'Please select the duration for your cover  ?'                     ,                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(1, 'On-going, paid monthly');
insert into answer_values(questionnaire_id, answer_value) values(1, 'On-going, paid annually');
insert into answer_values(questionnaire_id, answer_value) values(1, 'Specific Period (Once-Off)');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '2', 'Please specify policy inception date for annual cover :'                     ,                    6,    1,  'On-going, paid annually', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '3', 'Please specify policy inception date for monthly cover :'                     ,                    6,    1,  'On-going, paid monthly', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '4', 'Please specify from date for once-off:'                     ,                    6,    1,  'Specific Period (Once-Off)', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '5', 'Please specify end date for once-off :'                     ,                    6,    1,  'Specific Period (Once-Off)', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '6', 'Are you currently insured ?'                     ,                      4,    null,  null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '7', 'Please provide details of your previous insurance company :',           1,    6,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('1', '8', 'Do you require SASRIA cover ?'                     ,                    4,    null,  null, 'false');

/*Product2*/

insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '9', 'Please select the duration for your cover  ?'                     ,                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(9, 'On-going, paid monthly');
insert into answer_values(questionnaire_id, answer_value) values(9, 'On-going, paid annually');
insert into answer_values(questionnaire_id, answer_value) values(9, 'Specific Period (Once-Off)');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '10', 'Please specify policy inception date for annual cover :'                     ,                    6,    1,  'On-going, paid annually', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '11', 'Please specify policy inception date for monthly cover :'                     ,                    6,    1,  'On-going, paid monthly', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '12', 'Please specify from date for once-off:'                     ,                    6,    1,  'Specific Period (Once-Off)', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '13', 'Please specify end date for once-off :'                     ,                    6,    1,  'Specific Period (Once-Off)', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '14', 'Are you currently insured ?'                     ,                    4,    null,  null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '15', 'Please provide details of your previous insurance company :',           1,    6,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('2', '16', 'Do you require SASRIA cover ?',                 4,  null,    null, 'false');


/*Product3*/

insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '17', 'Please select the duration for your cover  ?'                     ,                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(17, 'On-going, paid monthly');
insert into answer_values(questionnaire_id, answer_value) values(17, 'On-going, paid annually');
insert into answer_values(questionnaire_id, answer_value) values(17, 'Specific Period (Once-Off)');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '18', 'Please specify policy inception date for annual cover :'                     ,                    6,    1,  'On-going, paid annually', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '19', 'Please specify policy inception date for monthly cover :'                     ,                    6,    1,  'On-going, paid monthly', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '20', 'Please specify from date for once-off:'                     ,                    6,    1,  'Specific Period (Once-Off)', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '21', 'Please specify end date for once-off :'                     ,                    6,    1,  'Specific Period (Once-Off)', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '22', 'Are you currently insured ?'                     ,                    4,    null,  null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '23', 'Please provide details of your previous insurance company :'                     ,                    1,   6,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('3', '24', 'Do you require SASRIA cover ?'                     ,                    4,    null,  null, 'false');

/*product 4*/

insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '25', 'Please select the duration for your cover  ?'                     ,                    3,    null,  null, 'true');
insert into answer_values(questionnaire_id, answer_value) values(25, 'On-going, paid monthly');
insert into answer_values(questionnaire_id, answer_value) values(25, 'On-going, paid annually');
insert into answer_values(questionnaire_id, answer_value) values(25, 'Specific Period (Once-Off)');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '26', 'Please specify policy inception date for annual cover :'                     ,                    6,    1,  'On-going, paid annually', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '27', 'Please specify policy inception date for monthly cover :'                     ,                    6,    1,  'On-going, paid monthly', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '28', 'Please specify from date for once-off:'                     ,                    6,    1,  'Specific Period (Once-Off)', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '29', 'Please specify end date for once-off :'                     ,                    6,    1,  'Specific Period (Once-Off)', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '30', 'Are you currently insured ?'                     ,                    4,    null,  null, 'false');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '31', 'Please provide details of your previous insurance company :'                     ,                    1,    6,  'true', 'true');
insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, on_answer, is_required)  values('4', '32', 'Do you require SASRIA cover ?',                 4,  null,    null, 'false');

insert into sub_agents(broker_id,first_name,middle_name,last_name,email) values(1,'Hentie','Hentie','Synder','hentie.synder@polygongroup.co.za');
insert into sub_agents(broker_id,first_name,middle_name,last_name,email) values(1,'Susan','Susan','Otto','susan.otto@polygongroup.co.za');

insert into underwriters(first_name,middle_name,last_name,email) values('Lessely','Lessley','Kruger','lessely.kruger@genric.co.za');
insert into underwriters(first_name,middle_name,last_name,email) values('Marissa','Marissa','Lehman','marissa@lehman@genric.co.za');

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
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','18', 'Date Reported ?', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','19', 'Case number ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','20', 'Valuables?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','21', 'Cash?', 2, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','22', 'When last valued ?', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','23', 'I/We hereby decrare the foregoing particulars to be true in every resperct?', 4, true);

insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','24', 'Please upload Investigation Report ', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','25', 'Please upload Confirmation Of Amount ', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','26', 'Please upload Quote', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','27', 'Please upload Photos', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','28', 'Please upload Armed Response / Alarm Report', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('1','29', 'Please upload Affidavit', 8, false);





insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','30', 'Event', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','32', 'Discovery', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','32', 'Place where Loss/Damage occurred', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','33', 'Did the incident happen on a premises or in Transit', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(33, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(33, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','34', 'If on prenises, was it occupied at the time of the incident', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(34, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(34, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('2','35', 'Type of a loss', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(35, 'Theft');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(35, 'Robbery');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(35, 'Accidental');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(35, 'Other');
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','36', 'Describe fully how the loss or damage occured stating how (if appicaple) entry was gained to premises?', 5, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','37', 'If Loss/Damage caused by another party give a name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','38', 'If Loss/Damage caused by another party give a contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','39', 'If Loss/Damage caused by another party give a insurers name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','40', 'Security Company name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','41', 'Date Reported :', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','42', 'Security co case number', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','43', 'Investigation officer name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','44', 'Investigation officer contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','45', 'Police Station name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','46', 'Place : ', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','47', 'Date Reported ?', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','48', 'Case number ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','49', 'Valuables?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','50', 'Cash?', 2, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','51', 'When last valued ?', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','52', 'I/We hereby decrare the foregoing particulars to be true in every resperct?', 4, true);

insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','53', 'Please upload Investigation Report ', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','54', 'Please upload Confirmation Of Amount ', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','55', 'Please upload Proof of pickup', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('2','56', 'Please upload Affidavit', 8, false);




insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','57', 'Event', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','58', 'Discovery', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','59', 'Place where Loss/Damage occurred', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','60', 'Did the incident happen on a premises or in Transit', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(60, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(60, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','61', 'If on prenises, was it occupied at the time of the incident', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(61, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(61, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('3','62', 'Type of a loss', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(62, 'Theft');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(62, 'Robbery');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(62, 'Accidental');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(62, 'Other');
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','63', 'Describe fully how the loss or damage occured stating how (if appicaple) entry was gained to premises?', 5, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','64', 'If Loss/Damage caused by another party give a name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','65', 'If Loss/Damage caused by another party give a contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','66', 'If Loss/Damage caused by another party give a insurers name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','67', 'Security Company name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','68', 'Date Reported :', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','69', 'Security co case number', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','70', 'Investigation officer name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','71', 'Investigation officer contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','72', 'Police Station name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','73', 'Place : ', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','74', 'Date Reported ?', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','75', 'Case number ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','76', 'Valuables?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','77', 'Cash?', 2, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','78', 'When last valued ?', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','79', 'I/We hereby decrare the foregoing particulars to be true in every resperct?', 4, true);

insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','80', 'Please upload Investigation Report ', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','81', 'Confirmation Of Amount ', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','82', 'Proof Of Pick up', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','83', 'Trans track Report', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('3','84', 'Affidavit', 8, false);




insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','85', 'Event', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','86', 'Discovery', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','87', 'Place where Loss/Damage occurred', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','88', 'Did the incident happen on a premises or in Transit', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(88, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(88, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','89', 'If on prenises, was it occupied at the time of the incident', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(89, 'Yes');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(89, 'No');
insert into claim_questionnaires(claim_type_id, sequence_number,question, claim_answer_type_id, is_required)values('4','90', 'Type of a loss', 3, true);
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(90, 'Theft');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(90, 'Robbery');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(90, 'Accidental');
insert into claim_answer_values(claim_questionnaire_id, claim_answer_value) values(90, 'Other');
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','91', 'Describe fully how the loss or damage occured stating how (if appicaple) entry was gained to premises?', 5, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','92', 'If Loss/Damage caused by another party give a name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','93', 'If Loss/Damage caused by another party give a contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','94', 'If Loss/Damage caused by another party give a insurers name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','95', 'Security Company name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','96', 'Date Reported :', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','97', 'Security co case number', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','98', 'Investigation officer name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','99', 'Investigation officer contact details?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','100', 'Police Station name?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','101', 'Place : ', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','102', 'Date Reported ?', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','103', 'Case number ?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','104', 'Valuables?', 1, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','105', 'Cash?', 2, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','106', 'When last valued ?', 6, true);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','107', 'I/We hereby decrare the foregoing particulars to be true in every resperct?', 4, true);

insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','108', 'Please upload Investigation Report ', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','109', 'Please upload Confirmation Of Amount ', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','110', 'Please upload Proof of Pickup', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','111', 'Please upload Actual amont banked/counted: VMS or Teller Report', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','112', 'Please upload Trans track document (if possible) ', 8, false);
insert into claim_questionnaires(claim_type_id, sequence_number, question, claim_answer_type_id, is_required)values('4','113', 'Please upload Affidavit', 8, false);


insert into request_types (request_type) values('CANCELLATION');
insert into request_types (request_type) values('REINSTATEMENT');
insert into request_types (request_type) values('TERMINATION');
insert into request_types (request_type) values('ENDORSEMENT');

insert into request_answer_types(request_answer_type) values('text');
insert into request_answer_types(request_answer_type) values('number');
insert into request_answer_types(request_answer_type) values('select');
insert into request_answer_types(request_answer_type) values('checkbox');
insert into request_answer_types(request_answer_type) values('textarea');
insert into request_answer_types(request_answer_type) values('date');
insert into request_answer_types(request_answer_type) values('blob');

insert into request_questionnaires(request_type_id, sequence_number,question, request_answer_type_id, is_required)values('1','1', 'Reason For Cancellation', 1, true);
insert into request_questionnaires(request_type_id, sequence_number,question, request_answer_type_id, is_required)values('1','2', 'Effective Date', 6, true);


