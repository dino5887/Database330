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
    abstractTitle VARCHAR(150) NOT NULL,
    abstract MEDIUMTEXT NOT NULL,
    PRIMARY KEY (abstractID)
);

INSERT INTO Abstract VALUES (0001, "Distance Learning as a Levelling Tool for People with Disabilities", "Distance learning has brought phenomenal changes to the educational playing field. 
In higher education, variances of distance learning can mean blended learning, flipped classrooms, or video modules/components. While distance learning results in no physical in-person interaction, 
online supplements physical interpersonal interactions. This paper will focus on distance learning in relation to people with disabilities, demonstrating the challenges that are faced with providing access to learners."),
(0002, "Work In Progress: Tablet PC's as a Leveling Device!", "Hewlett Packard Tablet PC's were used to alter the accessibility of the academic learning environment within a core information technology course 
entitled 'Needs Assessment.' This class deals with the softer side of IT, as it relates to communication and information-gathering. Taught in a classroom, technology has not been a part of the course learning style. 
The implementation of wireless tablet PC's augmented the curriculum on several levels. Interactions between diverse students (RIT has 1500 Deaf and hard of hearing students, due to the presence of the National Technical 
Institute for the Deaf) were impacted positively. The ability for students to communicate online while working face to face was unique, and the collaborative learning experience was heightened due to the functionality of the Tablets. 
However, there were also interesting trade-offs. In addition, it was discovered that students with other disabilities benefited in ways that were not anticipated"), (0003, "Teaching game AI through Minecraft mods", 
"One of the issues with teaching artificial intelligence (AI) for games is that many AI algorithms work in theory, but have production consequences in terms of speed or memory when actually used in a game. We report on 
the benefits and drawbacks of modifying or “modding” the commercial game Minecraft for a course on Game AI. This was done to give students the experience of dealing with a commercial game environment where they would have to worry 
about production consequences with their algorithms. The course was run as an upper level undergraduate elective during the fall of 2011 and included assignments on dynamic terrain generation, character behavior, and world events."),
(0004, "Using a low-cost open source hardware development platform in teaching young students programming skills", "The teaching of programming skills to young students is often described by those educators involved as problematic 
at best. Student issues like mathematical maturity, readiness for complex thought, basic problem solving skills, short attention span especially related to the boredom of traditional programming teaching methodologies, and the lack 
of exciting problems and their solutions with respect to programming assignments contribute to the angst of many a programming instructor. A small fraction of students who 'were just made for programming' always seem to succeed 
at whatever programming problem is given to them. However, a majority of students, especially precollege and college freshmen tend to have difficulty in overcoming these issues. It is with that observation that something new, in terms 
of programming pedagogy, needed to be investigated by this paper's authors.");

DROP TABLE IF EXISTS FacultyAbstract;
CREATE TABLE FacultyAbstract(
    facultyID INT(9) UNSIGNED NOT NULL,
    abstractID INT(4) UNSIGNED NOT NULL,
    PRIMARY KEY (facultyID, abstractID),
    CONSTRAINT facultyAbstract_faculty_fk FOREIGN KEY (facultyID) REFERENCES Faculty (facultyID),
    CONSTRAINT facultyAbstract_abstract_fk FOREIGN KEY (abstractID) REFERENCES Abstract (abstractID)
);

INSERT INTO FacultyAbstract VALUES (112023489, 0001), (112023489, 0002), (220111345, 0003), (111789000, 0004);

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

INSERT INTO FacultyInterests VALUES (112023489, "Accessibility"), (112023489, "Human-Computer Interaction"), (220111345, "Accessibility"), (220111345, "Computing Education"),
(220111345, "Game Design and Development"), (111789000, "Computing Education");
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