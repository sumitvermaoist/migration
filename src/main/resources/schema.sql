CREATE TABLE USER_DET (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    pwd VARCHAR(255) NOT NULL,
    age INT
);
INSERT INTO USER_DET (email, first_name, last_name, pwd, age)
VALUES
('john.doe@example.com', 'John', 'Doe', 'pass123', 28),
('jane.smith@example.com', 'Jane', 'Smith', 'secure456', 32),
('alex.jones@example.com', 'Alex', 'Jones', 'qwerty789', 24),
('maria.lee@example.com', 'Maria', 'Lee', 'welcome2025', 29),
('rohan.kapoor@example.com', 'Rohan', 'Kapoor', 'india123', 35);

CREATE TABLE hram_fulcom (
account_no    VARCHAR(50),
api_status    CHAR(1),
updated_by    VARCHAR(100),updated_time  TIMESTAMP
);

INSERT INTO hram_fulcom (account_no, api_status, updated_by, updated_time)
VALUES ('ACC1001', 'S', 'system', CURRENT_TIMESTAMP);

INSERT INTO hram_fulcom (account_no, api_status, updated_by, updated_time)
VALUES ('ACC1002', 'F', 'admin', TIMESTAMP '2025-08-21 10:15:00');

INSERT INTO hram_fulcom (account_no, api_status, updated_by, updated_time)
VALUES ('ACC1003', 'S', 'batch_job', TIMESTAMP '2025-08-20 18:30:00');

INSERT INTO hram_fulcom (account_no, api_status, updated_by, updated_time)
VALUES ('ACC1004', 'P', 'user1', TIMESTAMP '2025-08-19 09:00:00');
