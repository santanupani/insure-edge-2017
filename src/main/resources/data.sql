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

insert into answertypes(answertype)
values('text');

insert into answertypes(answertype)
values('number');

insert into answertypes(answertype)
values('select');

insert into answertypes(answertype)
values('checkbox');

insert into answertypes(answertype)
values('textarea');

insert into answervalues(question, answervalue)
values('Maximum amount you wish to insure:','1000000');

insert into answervalues(question, answervalue)
values('Is the above amount the Total Full Value of the goods being moved:','Yes');

insert into answervalues(question, answervalue)
values('Do you use the services of a professional valuables carrier:','No');

insert into questionnaires(productid, sequencenumber, questionid, answertypeid, dependson, ifanswer)
values('1','1','1','2','1','true');

insert into questionnaires(productid, sequencenumber, questionid, answertypeid, dependson, ifanswer)
values('1','2','2','3','2','true');

insert into questionnaires(productid, sequencenumber, questionid, answertypeid, dependson, ifanswer)
values('1','3','3','3','3','true');


