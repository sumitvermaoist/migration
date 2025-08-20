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