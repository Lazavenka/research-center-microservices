drop table if exists orders;

create table orders(
                          id integer primary key auto_increment,
                          client_id integer not null,
                          equipment_id integer not null,
                          order_state ENUM('BOOKED', 'PAYED', 'CANCELLED', 'COMPLETED') default 'BOOKED',
                          total_cost numeric(5,2) not null default 0.0,
                          rent_start_time datetime,
                          rent_end_time datetime

)