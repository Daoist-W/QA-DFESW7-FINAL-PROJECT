drop table if exists availability CASCADE;
drop table if exists job CASCADE;
drop table if exists user CASCADE;

CREATE TABLE availability (
    id BIGINT NOT NULL,
    end_date DATE NOT NULL,
    start_date DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE job (
    id BIGINT NOT NULL,
    description VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE user (
    id BIGINT NOT NULL,
    dob DATE NOT NULL,
    email VARCHAR(255) NOT NULL,
    forename VARCHAR(50) NOT NULL,
    passcode INTEGER NOT NULL,
    phone_num VARCHAR(255) NOT NULL,
    position_ VARCHAR(255) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);