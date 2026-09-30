-- Task Management System SQL Setup Script
CREATE DATABASE IF NOT EXISTS task_db;
USE task_db;

-- 1. Create User Registration Table
CREATE TABLE IF NOT EXISTS register (
    regid INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    mobile VARCHAR(20),
    address VARCHAR(255)
);

-- 2. Create Task Table
CREATE TABLE IF NOT EXISTS task (
    taskid INT AUTO_INCREMENT PRIMARY KEY,
    taskdescription TEXT NOT NULL,
    prioritystatus VARCHAR(50) DEFAULT 'Pending',
    regid INT NOT NULL,
    FOREIGN KEY (regid) REFERENCES register(regid) ON DELETE CASCADE
);
