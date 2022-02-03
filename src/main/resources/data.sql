INSERT INTO Users (position_, forename, surname, dob, email, phone_num, passcode)
VALUES
	('staff', 'don', 'brand', DATE '1991-09-15', 'don@youmail.com', '+4475649589', 132156654),
	('staff', 'don', 'brand', DATE '1991-09-15', 'harry@youmail.com', '+4475649589', 123465),
	('staff', 'paris', 'lorem', DATE '1991-07-21', 'paris@youmail.com', '+4475649589', 79846545),
	('admin', 'don', 'isiko', DATE '1991-09-15', 'don@youmail.com', '+4475649589', 654821658);


INSERT INTO Jobs (title, description_, location, start_date, end_date, user_id)
VALUES
	('topjob', 'best job in the world', 'London', DATE '2022-03-04', DATE '2022-03-04', 1),
	('topjob', 'best job in the world', 'London', DATE '2022-03-04', DATE '2022-03-04', 1),
	('topjob3', 'best job in the world', 'London', DATE '2022-02-04', DATE '2022-02-06', null),
	('topjob4', 'best job in the world', 'London', DATE '2022-02-04', DATE '2022-02-12', null);


INSERT INTO Availability (start_date, end_date, user_id)
VALUES
	( DATE '2022-01-01', DATE '2022-01-07', null),
	( DATE '2022-01-08', DATE '2022-01-14', 1),
	( DATE '2022-01-15', DATE '2022-01-21', 1),
	( DATE '2022-01-22', DATE '2022-01-28', null);
