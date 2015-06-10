insert into login (user_name, password, enabled, role) values('admin', 'secret', true, 'ROLE_ADMIN');

insert into products(name, description, image)  
values
('Cash and Valuables in Transit', 
'Fire, Accidental damage, Hijacking, Theft & Armed Robbery – as per standard policy wording.',
'/img/products/Cash and Valuables in Transit1.jpg');

insert into products(name, description, image) 
values
('Static Cover Cash and Valuables', 
'Fire, Accidental damage, Hijacking, Theft & Armed Robbery – as per standard policy wording.'
,'/img/products/Static Cover Cash and Valuables.jpg');

insert into products(name, description, image)  
values('Fine Art and Collectables', 
'Fire, Accidental damage, Hijacking, Theft & Armed Robbery – as per standard policy wording.'
,'/img/products/Fine Art and Collectables1.jpg');
 
insert into products(name, description, image)  
values('Static and In Transit Cover Cash and Valuables', 
'Fire, Accidental damage, Hijacking, Theft & Armed Robbery – as per standard policy wording.'
,'/img/products/Static and In Transit Cover Cash and Valuables1.jpg'); 

insert into answer_types(answer_type)
values('number');

insert into answer_types(answer_type)
values('text');

insert into answer_types(answer_type)
values('select');

insert into answer_types(answer_type)
values('checkbox');

insert into answer_types(answer_type)
values('textarea');

insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, if_answer)
values('1',
'1',
'Maximum amount you wish to insure:',
'1',
'1',
'null'
);

insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, if_answer)
values('1',
'2',
'Is the above amount the Total Full Value of the goods being moved:',
'3',
'1',
'text'
);

insert into questionnaires(product_id, sequence_number, question, answer_type_id, depends_on, if_answer)
values('1',
'3',
'Do you use the services of a professional valuables carrier:',
'3',
'1',
'text'
);


