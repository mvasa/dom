CREATE TABLE Flight_Plan
                    (Route_ID                       integer PRIMARY KEY,
                     Airplane_Number                integer,
                     Destination_City               VARCHAR,
                     Origin_City                    VARCHAR,
                     date_of_departure              VARCHAR,
                     date_of_arrival                VARCHAR,
                     time_of_departure              VARCHAR,
                     time_of_arrival                VARCHAR, 
                     flight_length                  VARCHAR);

INSERT INTO Flight_Plan 
VALUES
(1, 6000, 'New York', 'London', '6H 55M', '5/5/2020', '5/5/2020', '6:00AM', '12:55PM'),
(2, 6001, 'New York', 'Toronto', '1H 35M', '5/5/2020', '5/5/2020', '8:00AM', '9:35AM'),
(3, 6002, 'Chicago', 'London', '7H 40M', '5/6/2020', '5/7/2020', '6:00 AM', '1:40 PM'),
(4, 6003, 'Chicago', 'Toronto', '1H 35M', '5/6/2020', '5/6/2020',	'12:00PM', '1:35PM'),
(5, 6001, 'Toronto', 'New York', '1H 30M', '5/5/2020', '5/6/2020', '11:00AM', '12:30PM'),
(6, 6003, 'Toronto', 'Chicago',	'1H 55M', '5/6/2020', '5/6/2020', '3:00PM',	'4:55PM'),
(7, 6000, 'London', 'New York',	'8H 10M', '5/5/2020', '5/6/2020',	'2:00PM',	'10:10 PM'),
(8, 6002, 'London',	'Chicago',	'9H', '5/7/2020', '5/8/2020', '4:00PM', '1:00AM'),
(9, 6004, 'Chicago', 'New York', '2H 10M', '5/6/2020', '5/6/2020', '9:10AM',	'11:20AM'),
(10, 6004, 'New York', 'Chicago', '2H 40M', '5/6/2020',	'5/6/2020',	'12:00PM',	'2:40PM'),
(11, 6010, 'London', 'Toronto',	'8H', '5/7/2020', '5/8/2020',	'6:00AM',	'2:00PM'),
(12, 6011, 'London', 'Paris', '1H 15M',	'5/7/2020',	'5/7/2020',	'8:00AM',	'9:15AM'),
(13, 6012, 'London', 'Nice', '2H',	'5/8/2020',	'5/8/2020',	'8:30AM',	'10:30AM'),

(15, 6011, 'Paris', 'London', '1H 15M',	'5/7/2020', '5/7/2020',	'11:30AM', '12:45PM'),
(16, 6012, 'Nice', 'London', '2H 5M', '5/8/2020', '5/8/2020',	'12:30PM'	,'2:35PM'),
(17, 6014, 'Eidenburgh', 'Toronto',	'10H 30M', '5/8/2020',	'5/9/2020',	'2:00PM',	'12:30AM'),
(18, 6015, 'Eidenburgh', 'Paris', '1H 50M',	'5/9/2020',	'5/9/2020',	'4:00PM',	'5:50PM'),
(19, 6016, 'Eidenburgh', 'Nice', '4H 5M', '5/10/2020', '5/10/2020',	'10:00AM', '2:05PM'),
(20, 6014, 'Toronto', 'Eidenburgh', '9H 15M', '5/9/2020', '5/9/2020', '8:00AM', '5:15PM'),
(21, 6015, 'Paris', 'Eidenburgh', '1H 50M',	'5/9/2020', '5/9/2020',	'7:00PM',	'8:50PM'),
(22, 6016, 'Nice', 'Eidenburgh', '4H 15M',	'5/10/2020', '5/10/2020', '4:00PM', '8:15PM'),
(23, 6017, 'London', 'Eidenburgh', '1H 25M', '5/10/2020', '5/10/2020', '9:15AM', '10:40AM'),
(24, 6017, 'Eidenburgh', 'London', '1H 30M', '5/10/2020', '5/10/2020', '1:00PM', '2:30PM'),
(25, 6020, 'Toronto', 'Montreal', '1H 15M',	'5/11/2020', '5/11/2020', '11:00AM', '12:15AM'),
(26, 6020, 'Montreal', 'Toronto', '1H 20M',	'5/11/2020', '5/11/2020', '2:00PM', '3:20PM'),
(27, 6021, 'Toronto', 'London',	'7H', '5/12/2020',	'5/12/2020',	'9:00AM',	'4:00PM'),
(28, 6022, 'Toronto', 'Eidenburgh',	'9H 15M', '5/12/2020', '5/12/2020',	'8:00AM',	'5:15PM'),
(29, 6023, 'Montreal', 'London', '6H 35M',	'5/12/2020', '5/12/2020', '7:00AM', '2:35PM'),
(30, 6024, 'Montreal', 'Eidenburgh', '9H 55M', '5/11/2020', '5/12/2020', '4:00PM', '1:55AM'),


(33, 6023, 'London', 'Montreal', '7H 25M', '5/12/2020', '5/12/2020', '4:00PM', '11:25PM'),
(34, 6024, 'Eidenburgh', 'Montreal', '9H 50M', '5/12/2020',	'5/12/2020', '10:00AM', '7:50PM');