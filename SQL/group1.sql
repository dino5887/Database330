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
    department VARCHAR(60) NOT NULL,
    PRIMARY KEY (departmentID)
);

INSERT INTO Departments VALUES (2000, "Computer Science"), (2005, "Computing Security"),(2010, "Information Technology"), 
(2015, "Interactive Games and Media"), (2020, "Software Engineering"), (3000, "Chemistry"), (3005, "Physics"), (3010, "Life Sciences"),
(4000, "Biomedical Engineering"), (4005, "Electrical Engineering"), (4010, "Mechanical Engineering");

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

INSERT INTO Faculty VALUES (100023456, 2010, "Habermas", "Jim", "jhabermas", "apple", "jim.habermas@rit.edu"), (110122300, 2010, "Bogaard", "Dan", "dbogaard", "orange", "dan.bogaard@rit.edu"),
(111789000, 2010, "Hill", "Lawrence", "lhill", "pear", "lwhfac@rit.edu"), (112023489, 2010, "Beaton", "Catherine", "cbeaton", "strawberry", "ciiics@rit.edu"), 
(122098777, 2010, "Patric", "Dave", "dpatric", "grape", "dkpvcs@rit.edu"), (220111345, 2015, "Bayliss", "Jessica", "jbayliss", "blueberry", "jdbics@rit.edu"), 
(232002892, 2020, "Desell", "Travis", "dtravis", "banana", "tjdvse@rit.edu"), (251000123, 2020, "Kiser", "Larry", "lkiser", "watermelon", "llkiee@rit.edu"), 
(322045987, 3000, "Bailey", "Alla", "abailey", "pineapple", "avbsch@rit.edu"), (327723237, 3000, "Cody", "Jeremy", "jcody", "peach", "jacsch@rit.edu"), 
(334555500, 3000, "Gleghorn", "Michael", "mgleghorn", "raspberry", "mlgsch@rit.edu"), (350012300, 3010, "Connelly", "Sandi", "sconnelly", "lemon", "sjcsbi@rit.edu"), 
(400444000, 4000, "Day", "Steven", "sday", "lime", "steven.day@rit.edu"), (401234567, 4000, "Gaborski", "Tom", "tgaborski", "mango", "trgbme@rit.edu"), 
(429845600, 4005, "Borkholder", "David", "dborkholder", "coconut", "david.borkholder@rit.edu"), (435998760, 4010, "Fly", "Gerald", "gfly", "kiwi", "gwfeme@rit.edu");


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
    major VARCHAR(60),
    PRIMARY KEY (majorID)
);

INSERT INTO Major VALUES (1000, "Computing Exploration"), (1002, "Computing and Information Technologies"), (1004, "Computer Science"), (1006, "Computing Security"),
(1008, "Game Design and Development"), (1010, "Humanities, Computing, and Design"), (1012, "Human-Centered Computing"), (1014, "New Media Interactive Development"), 
(1016, "Software Engineering"), (1018, "Web and Mobile Computing"), (2000, "Biomedical Engineering"), (2002, "Computing Engineering"), (2004, "Electrical Engineering"),
(2006, "Mechanical Engineering"), (2008, "Microelectronic Engineering"), (3000, "Biochemistry"), (3002, "Biology"), (3004, "Chemistry"), (3006, "Enviromental Science"),
(3008, "Imaging Science"), (3010, "Physics");

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