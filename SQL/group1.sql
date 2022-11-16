-- Group 1: Brenden Becker, Sophia Castiglione, Hunter Ross
-- SQL for group project
-- ISTE330.02

DROP DATABASE IF EXISTS group1;

CREATE DATABASE group1;
USE group1;

-- FACULTY TABLES START
DROP TABLE IF EXISTS Departments;
CREATE TABLE Departments(
    departmentID INT(4) UNSIGNED NOT NULL,
    department VARCHAR(45) NOT NULL,
    PRIMARY KEY (departmentID)
);

DROP TABLE IF EXISTS Faculty;
CREATE TABLE Faculty(
    facultyID INT(9) UNSIGNED NOT NULL,
    departmentID INT(4) UNSIGNED NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    firstName VARCHAR(45) NOT NULL,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    email VARCHAR(60) NULL,
    PRIMARY KEY (facultyID),
    CONSTRAINT faculty_departments_fk FOREIGN KEY (departmentID) REFERENCES Departments (departmentID)
);

DROP TABLE IF EXISTS FacultyPhone;
CREATE TABLE FacultyPhone(
    facultyID INT(9) UNSIGNED NOT NULL,
    phoneType VARCHAR(20) NOT NULL,
    phoneNumber INT(10) UNSIGNED NOT NULL,
    PRIMARY KEY (facultyID, phoneType),
    CONSTRAINT facultyPhone_faculty_fk FOREIGN KEY (facultyID) REFERENCES Faculty (facultyID)
);

DROP TABLE IF EXISTS Abstract;
CREATE TABLE Abstract(
    abstractID INT(4) UNSIGNED NOT NULL,
    abstractTitle VARCHAR(45) NOT NULL,
    abstract MEDIUMTEXT NOT NULL,
    PRIMARY KEY (abstractID)
);

DROP TABLE IF EXISTS FacultyAbstract;
CREATE TABLE FacultyAbstract(
    facultyID INT(9) UNSIGNED NOT NULL,
    abstractID INT(4) UNSIGNED NOT NULL,
    PRIMARY KEY (facultyID, abstractID),
    CONSTRAINT facultyAbstract_faculty_fk FOREIGN KEY (facultyID) REFERENCES Faculty (facultyID),
    CONSTRAINT facultyAbstract_abstract_fk FOREIGN KEY (abstractID) REFERENCES Abstract (abstractID)
);

DROP TABLE IF EXISTS FacultyLocation;
CREATE TABLE FacultyLocation(
    facultyID INT(9) UNSIGNED NOT NULL,
    building VARCHAR(45) NOT NULL,
    officeNumber INT(4) UNSIGNED NOT NULL,
    PRIMARY KEY (facultyID),
    CONSTRAINT facultyLocation_faculty_fk FOREIGN KEY (facultyID) REFERENCES Faculty (facultyID)
);

DROP TABLE IF EXISTS FacultyInterests;
CREATE TABLE FacultyInterests(
    facultyID INT(9) UNSIGNED NOT NULL,
    keyword VARCHAR(40) NOT NULL,
    PRIMARY KEY (facultyID, keyword),
    CONSTRAINT facultyInterests_faculty_fk FOREIGN KEY (facultyID) REFERENCES Faculty (facultyID)
);
-- FACULTY TABLES END / STUDENT TABLES START

DROP TABLE IF EXISTS Student;
CREATE TABLE Student(
    studentID INT(9) UNSIGNED NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    firstName VARCHAR(45) NOT NULL,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    email VARCHAR(60) NULL,
    PRIMARY KEY (studentID)
);

DROP TABLE IF EXISTS Major;
CREATE TABLE Major(
    majorID INT(4) UNSIGNED NOT NULL,
    major VARCHAR(45),
    PRIMARY KEY (majorID)
);

DROP TABLE IF EXISTS StudentMajor;
CREATE TABLE StudentMajor(
    studentID INT(9) UNSIGNED NOT NULL,
    majorID INT(4) UNSIGNED NOT NULL,
    PRIMARY KEY (studentID, majorID),
    CONSTRAINT studentMajor_student_fk FOREIGN KEY (studentID) REFERENCES Student (studentID),
    CONSTRAINT studentMajor_major_fk FOREIGN KEY (majorID) REFERENCES Major (majorID)
);

DROP TABLE IF EXISTS StudentInterests;
CREATE TABLE StudentInterests(
    studentID INT(9) UNSIGNED NOT NULL,
    keyword VARCHAR(40) NOT NULL,
    PRIMARY KEY (studentID, keyword),
    CONSTRAINT studentInterests_student_fk FOREIGN KEY (studentID) REFERENCES Student (studentID)
);

-- STUDENT TABLES END