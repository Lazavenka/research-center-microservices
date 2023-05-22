insert into department (name, description, address)
values ('Physics of condensed matter', 'Researching in sphere of condensed matter', 'Minsk, Brovki str, 19');
insert into department (name, description, address)
values ('Micro- and nanoelectronics', 'Researching in micro- and nano scales in electronics', 'Minsk, Brovki str, 6');
insert into department (name, description, address)
values ('Mechanics and tribology', 'Researching of hardness, elastic deformation of materials', 'Minsk, Akademicheskaya str, 15');
INSERT INTO department (name, description, address)
VALUES ('Department of Physics', 'Research in theoretical and experimental physics', '123 Main Street');
INSERT INTO department (name, description, address)
VALUES ('Department of Chemistry', 'Exploring chemical reactions and properties', '456 Elm Street');

insert into laboratory(name, description, location, department_id) VALUES ('Condensed Matter Physics Lab', 'Research on properties of condensed matter', 'Building E, Room 501', 1);
insert into laboratory(name, description, location, department_id) VALUES ('Nanomaterials','Research of electrical properties of new nanostructured materials','Building A, Room 115',2);
insert into laboratory(name, description, location, department_id) VALUES ('Micro- and Nanoelectronics Lab', 'Research on micro- and nanoelectronic devices', 'Building F, Room 601', 2);
insert into laboratory(name, description, location, department_id) VALUES ('Microhardness research lab','Research of microhardness of nanofilm materials','Building C, Room 228',3);
INSERT INTO laboratory(name, description, location, department_id) VALUES ('Quantum Physics Research Lab', 'Experimental research on quantum physics', 'Building A, Room 101', 4);
INSERT INTO laboratory(name, description, location, department_id) VALUES ('Chemical Analysis Lab', 'Analyzing chemical compounds', 'Building B, Room 201', 5);

insert into equipment_type (name, description)
values ('Optical', 'All equipment for researching optical properties of material');
insert into equipment_type (name, description)
values ('Mechanical', 'Research of materials using mechanical principles');
insert into equipment_type (name, description)
values ('Electrical', 'Equipment for research electrophysical properties');
insert into equipment_type (name, description)
values ('Microscopes', 'Equipment for research microstructure and morphology');

insert into equipment (name, description, image_file_path, equipment_type_id, laboratory_id, is_need_assistant, state,
                       price_per_hour, average_research_time)
values ('Multimeter V7-27','Voltmeter with wide range measurements voltage, current and resistance', null, 3, 2, false,'ACTIVE', 10.00, '00:15:00');
insert into equipment (name, description, image_file_path, equipment_type_id, laboratory_id, is_need_assistant, state,
                       price_per_hour, average_research_time)
values ('Microhardness tester','1-50 nN force apply for microhardness tester with nanoindentor', null, 2, 4, false,'ACTIVE', 25.00, '00:25:00');
insert into equipment (name, description, image_file_path, equipment_type_id, laboratory_id, is_need_assistant, state,
                       price_per_hour, average_research_time)
values ('Spectrophotometer SFM 2000','Visible and near IR range spectrephotometer', null, 1, 1, true,'ACTIVE', 30.00, '00:30:00');
insert into equipment (name, description, image_file_path, equipment_type_id, laboratory_id, is_need_assistant, state,
                       price_per_hour, average_research_time)
values ('Oscilloscope Belvar 2021','Signal analyser in 10 Hz - 10 MHz range', null, 3, 3, false,'ACTIVE', 15.00, '00:20:00');
insert into equipment (name, description, image_file_path, equipment_type_id, laboratory_id, is_need_assistant, state,
                       price_per_hour, average_research_time)
values ('Atomic-force microscope Carl-Zeiss AFM1','AFM with 100 pm resolution', null, 4, 2, true,'ACTIVE', 100.00, '01:30:00');
insert into equipment (name, description, image_file_path, equipment_type_id, laboratory_id, is_need_assistant, state,
                       price_per_hour, average_research_time)
values ('Scanning electron microscope JEOL-9000','Microscope with 2 nm resolution', null, 4, 3, true,'ACTIVE', 50.00, '02:30:00');
insert into equipment (name, description, image_file_path, equipment_type_id, laboratory_id, is_need_assistant, state,
                       price_per_hour, average_research_time)
values ('Deformation measurement stand','Set of equipment for elastic deformation measurement', null, 2, 4, false,'ACTIVE', 25.00, '00:45:00');
insert into equipment (name, description, image_file_path, equipment_type_id, laboratory_id, is_need_assistant, state,
                       price_per_hour, average_research_time)
values ('Spectrophotometer SFM 3000', 'Visible and near IR range spectrephotometer', null, 1, 6, true, 'ACTIVE', 30.00, '00:30:00');
