insert into user_details(birth_date, id,  name)
values(current_date(), 1001, 'Huzefa');

insert into user_details(birth_date, id,  name)
values(current_date(), 1002, 'Suresh');

insert into user_details(birth_date, id,  name)
values(current_date(), 1003, 'Mukesh');

insert into post(id, user_id, description)
values(2001, 1001, 'I want to learn AWS');
insert into post(id, user_id, description)
values(2002, 1002, 'I want to learn GCP');
insert into post(id, user_id, description)
values(2003, 1003, 'I want to learn Azure');


insert into todo (id, description, done, target_date,username)
values (10001, 'Learn AWS', false, CURRENT_DATE(), 'in28minutes');

insert into todo (id, description, done, target_date,username)
values (10002, 'Get AWS Certified', false, CURRENT_DATE(), 'in28minutes');

insert into todo (id, description, done, target_date,username)
values (10003, 'Learn DevOps', false, CURRENT_DATE(), 'in28minutes');