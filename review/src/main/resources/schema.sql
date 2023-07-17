CREATE TABLE IF NOT EXISTS USERS (
    userid INT PRIMARY KEY auto_increment,
    username VARCHAR(20),
    firstName VARCHAR,
    lastName VARCHAR,
    password VARCHAR,
    salt VARCHAR
);

CREATE TABLE IF NOT EXISTS MESSAGES (
    messageid INT PRIMARY KEY auto_increment,
    username VARCHAR NOT NULL,
    message VARCHAR NOT NULL
);