/* Loading initial station sample in db */
insert into station (name, latitude, longitude) values ('Αφετηρία', 37.123450, 383.123450);
insert into station (name, latitude, longitude) values ('Σίδερα', 37.123451, 383.123451);
insert into station (name, latitude, longitude) values ('Υγεία', 37.123452, 383.123452);
insert into station (name, latitude, longitude) values ('Σχολεία', 37.123453, 383.123453);
insert into station (name, latitude, longitude) values ('ΚΑΤ', 37.123454, 383.123454);
insert into station (name, latitude, longitude) values ('Αστυνομία', 37.123455, 383.123455);
insert into station (name, latitude, longitude) values ('Κολλέγιο', 37.123456, 383.123456);
insert into station (name, latitude, longitude) values ('Στάδιο', 37.123457, 383.123457);
insert into station (name, latitude, longitude) values ('Σκαλάκια', 37.123458, 383.123458);
insert into station (name, latitude, longitude) values ('Φάρος', 37.123459, 383.123459);
insert into station (name, latitude, longitude) values ('Τέρμα', 37.123460, 383.123460);

/* Loading initial leg sample in db */
insert into leg (source_id, destination_id, distance_cost, time_cost) values (1, 2, 155, 125);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (1, 3, 110, 70);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (2, 4, 96, 56);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (2, 5, 85, 45);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (2, 3, 70, 30);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (3, 5, 92, 52);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (4, 5, 163, 123);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (4, 6, 210, 170);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (5, 7, 128, 88);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (6, 5, 68, 28);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (6, 7, 170, 130);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (6, 8, 74, 34);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (7, 8, 46, 6);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (7, 9, 104, 64);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (7, 10, 128, 88);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (8, 9, 43, 3);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (8, 10, 95, 55);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (9, 10, 47, 7);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (9, 11, 77, 37);
insert into leg (source_id, destination_id, distance_cost, time_cost) values (10, 11, 66, 26);

/* Loading initial vehicle type sample in db */
insert into vehicle_type (name) values ('Bus');
insert into vehicle_type (name) values ('Trolley');
insert into vehicle_type (name) values ('Train');
insert into vehicle_type (name) values ('Subway');
insert into vehicle_type (name) values ('Car');

/* Loading initial vehicle sample in db */
insert into Vehicle (name, description, vehicle_type_id) values ('550', 'Π. Φάληρο - Κηφισιά', 1);
insert into Vehicle (name, description, vehicle_type_id) values ('A7', 'Στουρναρη - Κηφισιά (Πλατεία Πλατάνου)', 1);
insert into Vehicle (name, description, vehicle_type_id) values ('421', 'Αγ. Ανάργυροι - Αγ. Παρασκευή', 1);
insert into Vehicle (name, description, vehicle_type_id) values ('450', 'Στ. Χαλάνδρι - Χαλάνδρι - Ν. Πεντέλη', 1);
insert into Vehicle (name, description, vehicle_type_id) values ('10', 'Τζιτζιφιές - Χαλάνδρι', 2);
insert into Vehicle (name, description, vehicle_type_id) values ('18', 'Μουσείο - Χαλάνδρι (Μέσω Εθν. Αντιστάσεως)', 2);
insert into Vehicle (name, description, vehicle_type_id) values ('19', 'Μουσείο - Στ. Χαλανδρίου (Σίδερα)', 2);

