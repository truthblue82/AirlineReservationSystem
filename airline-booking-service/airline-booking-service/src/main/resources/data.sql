--airport
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('CDA', '12 Brookhust Str, Cedar Rapids, IA', 'Cedar Rapids Airport');
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('SNA', '111 First Str, Santa Anna, CA', 'John Wayne Airport');
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('ATL', 'Atlanta, Georgia', 'Hartsfield–Jackson Atlanta International Airport');
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('CLT', 'Charlotte, NC', 'John Wayne Airport');
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('DEN', 'Denver, Colorado', 'Denver International Airport');
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('DFW', 'Dallas-Fort Worth, Texas', 'Dallas Fort Worth International Airport');
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('JFK', 'New York Metro, New York City', 'JJohn F. Kennedy International Airport');
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('LAS', 'Las Vegas, Nevada', 'Harry Reid International Airport');
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('MCO', 'Orlando, Florida', 'Orlando International Airport');
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('MIA', 'Miami Metro, FL', 'Miami International Airport');
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('ORD', 'Chicago, Illinois', 'Chicago O''Hare International Airport');
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('SEA', 'Seattle, WA', 'Seattle–Tacoma International Airport');
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('PHX', 'Phoenix, AZ', 'Phoenix Sky Harbor International Airport');
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('EWR', 'New Your Metro, NJ', 'Newark Liberty International Airport');
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('SFO', 'San Francisco Bay Area, CA', 'San Francisco International Airport');
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('IAH', 'Houston, TX', 'George Bush Intercontinental Airport');
INSERT IGNORE INTO fms.airport(code, location, name) VALUES ('BOS', 'Boston, TX', 'Logan International Airport');

--flight
INSERT IGNORE INTO fms.flight(flight_no, carrier_name, flight_model, seat_capacity) VALUES ('AMA123', 'American Airline', 'Airbus 777', 150);
INSERT IGNORE INTO fms.flight(flight_no, carrier_name, flight_model, seat_capacity) VALUES ('JPA123', 'Japanese Airline', 'Boeing 878', 300);
INSERT IGNORE INTO fms.flight(flight_no, carrier_name, flight_model, seat_capacity) VALUES ('KRA123', 'Korean Airline', 'Airbus 777', 150);
INSERT IGNORE INTO fms.flight(flight_no, carrier_name, flight_model, seat_capacity) VALUES ('ASA123', 'ALASKA Airlines', 'Boeing 737', 165);
INSERT IGNORE INTO fms.flight(flight_no, carrier_name, flight_model, seat_capacity) VALUES ('BE134', 'Delta Air Lines', 'Boeing 134', 145);
INSERT IGNORE INTO fms.flight(flight_no, carrier_name, flight_model, seat_capacity) VALUES ('EA100', 'Eastern Airline', 'Boeing 121', 150);
INSERT IGNORE INTO fms.flight(flight_no, carrier_name, flight_model, seat_capacity) VALUES ('MXY111', 'Breeze Airways', 'Boeing 122', 125);
INSERT IGNORE INTO fms.flight(flight_no, carrier_name, flight_model, seat_capacity) VALUES ('VNA123', 'Vietnam Airline', 'Airbus 999', 540);
INSERT IGNORE INTO fms.flight(flight_no, carrier_name, flight_model, seat_capacity) VALUES ('VXP012', 'Avelo Airlines', 'B737', 155);

--schedule
INSERT IGNORE INTO fms.schedule(arrival_date, departure_date, dstn_airport_code, source_airport_code) VALUES('2023-11-10 10:15:00.000000','2023-11-08 10:15:00.000000','SNA', 'CDA');
INSERT IGNORE INTO fms.schedule(arrival_date, departure_date, dstn_airport_code, source_airport_code) VALUES('2023-11-10 15:15:00.000000','2023-11-08 15:15:00.000000','SNA', 'CDA');
INSERT IGNORE INTO fms.schedule(arrival_date, departure_date, dstn_airport_code, source_airport_code) VALUES('2023-11-10 08:15:00.000000','2023-11-08 08:15:00.000000','SNA', 'CDA');
INSERT IGNORE INTO fms.schedule(arrival_date, departure_date, dstn_airport_code, source_airport_code) VALUES('2023-11-11 08:15:00.000000','2023-11-07 08:15:00.000000','ORD', 'ATL');
INSERT IGNORE INTO fms.schedule(arrival_date, departure_date, dstn_airport_code, source_airport_code) VALUES('2023-11-10 10:15:00.000000','2023-11-08 10:15:00.000000','SNA', 'CDA');


--scheduled_flight
INSERT IGNORE INTO fms.scheduled_flight(available_seats, economic_price, temporary_available_seats, flight_flight_no, schedule_schedule_id)
       VALUES (150, 300, 150, 'KRA123', 1);
INSERT IGNORE INTO fms.scheduled_flight(available_seats, economic_price, temporary_available_seats, flight_flight_no, schedule_schedule_id)
       VALUES (165, 374, 165, 'ASA123', 2);
INSERT IGNORE INTO fms.scheduled_flight(available_seats, economic_price, temporary_available_seats, flight_flight_no, schedule_schedule_id)
       VALUES (300, 255, 300, 'JPA123', 3);
INSERT IGNORE INTO fms.scheduled_flight(available_seats, economic_price, temporary_available_seats, flight_flight_no, schedule_schedule_id)
       VALUES (540, 355, 540, 'VNA123', 4);
INSERT IGNORE INTO fms.scheduled_flight(available_seats, economic_price, temporary_available_seats, flight_flight_no, schedule_schedule_id)
       VALUES (150, 300, 150, 'KRA123', 5);


INSERT IGNORE INTO fms.booking(booking_code, booking_date, no_of_passengers, scheduled_flight_schedule_flight_id)
       VALUES ('ZA4CD4WT','2023-11-11 00:00:00.000000', 2, 1);

INSERT IGNORE INTO fms.passenger(date_of_birth, luggage, passenger_name, booking_id)
       VALUES ('1982-12-31', 1, 'Kevin', null);
INSERT IGNORE INTO fms.passenger(date_of_birth, luggage, passenger_name, booking_id)
       VALUES ('1980-12-21', 1, 'Lee', null);

INSERT IGNORE INTO fms.payment(booking_user_email,booking_user_fullname,last4digit_payment_card,payment_code,total_price,booking_id)
       VALUES ('yt@gmail.com',null,1234,'TFXOYLSI',1000, 1);

