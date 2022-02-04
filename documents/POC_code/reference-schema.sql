CREATE DATABASE IF NOT EXISTS senpai;

USE senpai;


DROP TABLE IF EXISTS availability CASCADE;
DROP TABLE IF EXISTS jobs CASCADE;
DROP TABLE IF EXISTS users CASCADE;


CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    dob DATE NOT NULL,
    email VARCHAR(255) NOT NULL,
    forename VARCHAR(50) NOT NULL,
    passcode INTEGER NOT NULL,
    phone_num VARCHAR(255) NOT NULL,
    position_ VARCHAR(255) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE jobs (
    id BIGINT NOT NULL AUTO_INCREMENT,
	title VARCHAR(255) NOT NULL,
    description_ VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    user_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE availability (
    id BIGINT NOT NULL AUTO_INCREMENT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    user_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
