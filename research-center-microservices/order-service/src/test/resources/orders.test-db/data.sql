CREATE SEQUENCE orders_seq START WITH 52 INCREMENT BY 50;
insert into orders (id,client_id,equipment_id, order_state, total_cost, rent_start_time, rent_end_time)
values (1, 2, 5, 'PAYED', 30.00, '2023-06-15 12:30:00', '2023-06-15 14:30:00');
insert into orders (id,client_id,equipment_id, order_state, total_cost, rent_start_time, rent_end_time)
values (2, 2, 5, 'COMPLETED', 100.00, '2023-05-31 12:30:00', '2023-05-31 18:00:00');
insert into orders (id,client_id,equipment_id, order_state, total_cost, rent_start_time, rent_end_time)
values (3, 3, 6, 'CANCELLED', 60.00, '2023-05-31 12:30:00', '2023-05-31 18:00:00');