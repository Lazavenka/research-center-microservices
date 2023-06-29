CREATE SEQUENCE t_users_seq START WITH 53 INCREMENT BY 50;

insert into t_users (user_id, first_name, last_name, user_email, user_password, state)
values (1, 'Jack', 'Jackson', 'j.jackson@email.com', '$2a$11$SBQKkUWEj.MZ33arh8m0Pegj.e0XKCxSpTVXOLMYvp.ZsBDqSUFiS', 'ACTIVE');
insert into t_users (user_id, first_name, last_name, user_email, user_password, state)
values (2, 'Homer', 'Simpson', 'h.simpson@email.com', '$2a$11$zvwE/5rTM5.KmAsn6Dh/Y.Ek48TcRewCyyRrUL.UP2Qk1J5MFLuwi', 'ACTIVE');
insert into t_users (user_id, first_name, last_name, user_email, user_password, state)
values (3, 'Test', 'User', 't.user@email.com', '$2a$11$SDaDD.pg1eg80F44tUGUxu7xHU.KNlhYFXec6.NzCRUp4Ghg6cN.m', 'ACTIVE');

insert into roles(role_id, role_name) values ( 1, 'ROLE_USER' );
insert into roles(role_id, role_name) values ( 2, 'ROLE_MANAGER' );
insert into roles(role_id, role_name) values ( 3, 'ROLE_ADMIN' );

insert into users_roles(user_id, role_id) values ( 1,3 );
insert into users_roles(user_id, role_id) values ( 1,2 );
insert into users_roles(user_id, role_id) values ( 2,2 );
insert into users_roles(user_id, role_id) values ( 3,1 );
