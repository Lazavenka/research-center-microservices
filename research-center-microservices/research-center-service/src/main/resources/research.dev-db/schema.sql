drop table if exists equipment_type;
drop table if exists equipment;
drop table if exists laboratory;
drop table if exists department;

create table department(
                           id integer primary key auto_increment,
                           name varchar(255) not null,
                           description mediumtext not null,
                           address varchar(255) not null
);

create table laboratory(
                           id integer primary key auto_increment,
                           name varchar(255) not null,
                           description mediumtext not null,
                           location varchar(255) not null,
                           image_file_path varchar(255),
                           department_id integer not null,

                           foreign key (department_id) references department(id)
);
create table equipment_type(
                               id integer primary key auto_increment,
                               name varchar(255) not null,
                               description mediumtext not null

);

create table equipment(
    id integer primary key auto_increment,
    name varchar(255) not null,
    description mediumtext not null,
    image_file_path varchar(255),
    equipment_type_id integer not null,
    laboratory_id integer not null,
    state ENUM('ACTIVE', 'INACTIVE') default 'ACTIVE',
    price_per_hour numeric(5,2) not null default 0.0,
    average_research_time time,

    foreign key (equipment_type_id) references equipment_type(id),
    foreign key (laboratory_id) references laboratory(id)
)