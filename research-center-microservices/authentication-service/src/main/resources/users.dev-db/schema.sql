drop table  if exists users_roles;
drop table if exists t_users;
drop table if exists roles;

create table t_users(
    user_id int primary key auto_increment,
    first_name varchar(40) not null,
    last_name varchar(40) not null,
    user_email varchar(255) unique not null,
    user_password varchar(60) not null,
    active boolean default true,
    state ENUM('ACTIVE', 'BLOCKED', 'REGISTRATION')

);
create table roles(
    role_id int primary key auto_increment,
    role_name varchar(20) not null
);

create table users_roles(
    user_id int not null,
    role_id int not null,
    foreign key (user_id) references t_users(user_id),
    foreign key (role_id) references roles(role_id)
);

create table register_token(
    id int primary key auto_increment,
    register_time datetime,
    token varchar(32),
    user_id int,
    foreign key (user_id) references t_users(user_id)
)