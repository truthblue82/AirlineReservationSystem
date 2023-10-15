INSERT IGNORE INTO roles (name) VALUES ('ROLE_CUSTOMER');
INSERT IGNORE INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT IGNORE INTO users (username,email,password, fullname) VALUES ('admin', 'tpm88941@gmail.com','$2a$10$AjAMVfhUkZ5v.HPCw5.nO.ss0dHFKNTKDEtocyXSZvX5TfnBDvtHq', 'Admin');

INSERT IGNORE INTO user_roles (user_id, role_id) VALUES (1, 2);

INSERT IGNORE INTO airport(code, location, name) VALUES ('CDA', '12 Brookhust Str, Cedar Rapids, IA', 'Cedar Rapids Airport');
INSERT IGNORE INTO airport(code, location, name) VALUES ('SNA', '111 First Str, Santa Anna, CA', 'John Wayne Airport');

INSERT IGNORE INTO flight(flight_no, carrier_name, flight_model, seat_capacity) VALUES ('AMA123', 'American Airline', 'Airbus 777', 150);
INSERT IGNORE INTO flight(flight_no, carrier_name, flight_model, seat_capacity) VALUES ('JPA123', 'Japanese Airline', 'Boeing 878', 300);
INSERT IGNORE INTO flight(flight_no, carrier_name, flight_model, seat_capacity) VALUES ('KRA123', 'Korean Airline', 'Airbus 777', 150);

