-- Step 1: Create Database
CREATE DATABASE IF NOT EXISTS jdbc_tasks;
USE jdbc_tasks;

-- Step 2: Create Table
CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    course VARCHAR(100) NOT NULL
);

-- Step 3: Insert Sample Data
INSERT INTO students (name, age, course) VALUES 
('Alice Johnson', 21, 'Computer Science'),
('Bob Williams', 23, 'Artificial Intelligence'),
('Charlie Brown', 22, 'Data Science');
