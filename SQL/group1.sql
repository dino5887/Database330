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
    phoneNumber VARCHAR(12) NOT NULL,
    PRIMARY KEY (facultyID, phoneType),
    CONSTRAINT facultyPhone_faculty_fk FOREIGN KEY (facultyID) REFERENCES Faculty (facultyID)
);

INSERT INTO FacultyPhone VALUES (100023456, "Cell", "585-746-9331"), (110122300, "Office", "585-475-5231"), (111789000, "Office", "585-475-7064"), (112023489, "Cell", "585-281-6162"), (122098777, "Cell", "585-454-9315"),
(220111345, "Office", "585-475-2507"), (232002892, "Office", "585-475-2991"), (251000123, "Cell", "585-230-4400"), (322045987, "Office", "585-475-6831"), (327723237, "Office", "585-475-2545"),
(334555500, "Office", "585-475-5528"), (350012300, "Office", "585-475-5602"), (400444000, "Office", "585-475-4738"), (401234567, "Office", "585-475-4117"), (429845600, "Office", "585-475-6067"), 
(435998760, "Office", "585-475-5259");

DROP TABLE IF EXISTS Abstract;
CREATE TABLE Abstract(
    abstractID INT UNSIGNED NOT NULL AUTO_INCREMENT,
    abstractTitle VARCHAR(150) NOT NULL,
    abstract MEDIUMTEXT NOT NULL,
    PRIMARY KEY (abstractID)
);

INSERT INTO Abstract VALUES (DEFAULT, "Distance Learning as a Levelling Tool for People with Disabilities", "Distance learning has brought phenomenal changes to the educational playing field. 
In higher education, variances of distance learning can mean blended learning, flipped classrooms, or video modules/components. While distance learning results in no physical in-person interaction, 
online supplements physical interpersonal interactions. This paper will focus on distance learning in relation to people with disabilities, demonstrating the challenges that are faced with providing access to learners."),
(DEFAULT, "Work In Progress: Tablet PC's as a Leveling Device!", "Hewlett Packard Tablet PC's were used to alter the accessibility of the academic learning environment within a core information technology course 
entitled 'Needs Assessment.' This class deals with the softer side of IT, as it relates to communication and information-gathering. Taught in a classroom, technology has not been a part of the course learning style. 
The implementation of wireless tablet PC's augmented the curriculum on several levels. Interactions between diverse students (RIT has 1500 Deaf and hard of hearing students, due to the presence of the National Technical 
Institute for the Deaf) were impacted positively. The ability for students to communicate online while working face to face was unique, and the collaborative learning experience was heightened due to the functionality of the Tablets. 
However, there were also interesting trade-offs. In addition, it was discovered that students with other disabilities benefited in ways that were not anticipated"), (DEFAULT, "Teaching game AI through Minecraft mods", 
"One of the issues with teaching artificial intelligence (AI) for games is that many AI algorithms work in theory, but have production consequences in terms of speed or memory when actually used in a game. We report on 
the benefits and drawbacks of modifying or “modding” the commercial game Minecraft for a course on Game AI. This was done to give students the experience of dealing with a commercial game environment where they would have to worry 
about production consequences with their algorithms. The course was run as an upper level undergraduate elective during the fall of 2011 and included assignments on dynamic terrain generation, character behavior, and world events."),
(DEFAULT, "Using a low-cost open source hardware development platform in teaching young students programming skills", "The teaching of programming skills to young students is often described by those educators involved as problematic 
at best. Student issues like mathematical maturity, readiness for complex thought, basic problem solving skills, short attention span especially related to the boredom of traditional programming teaching methodologies, and the lack 
of exciting problems and their solutions with respect to programming assignments contribute to the angst of many a programming instructor. A small fraction of students who 'were just made for programming' always seem to succeed 
at whatever programming problem is given to them. However, a majority of students, especially precollege and college freshmen tend to have difficulty in overcoming these issues. It is with that observation that something new, in terms 
of programming pedagogy, needed to be investigated by this paper's authors."), (DEFAULT, "Online Learning, the Educators Experiences", "Much has been researched and written about the trials and techniques of imparting education to students 
in technical fields like Information Technology via online learning. Even more work has been done about the students experiences in online courses. What this panel is hoping to engender is to start a discussion about online learning from 
the teachers' point of view, their trials and tribulations. It is our hope to start a discussion with the attendees and be able to share our triumphs, failures, theories, tools, approaches and fears. Attendees need no personal experience 
in online teaching, just a curiosity of what the endeavor might be like. Attendees with personal experience might find others with similar experiences, commiserate and hopefully find new pathways forward together."),
(DEFAULT, "Optimizing long short-term memory recurrent neural networks using ant colony optimization to predict turbine engine vibration", "This article expands on research that has been done to develop a recurrent neural network (RNN) capable 
of predicting aircraft engine vibrations using long short-term memory (LSTM) neurons. LSTM RNNs can provide a more generalizable and robust method for prediction over analytical calculations of engine vibration, as analytical calculations 
must be solved iteratively based on specific empirical engine parameters, making this approach ungeneralizable across multiple engines. In initial work, multiple LSTM RNN architectures were proposed, evaluated and compared. This research 
improves the performance of the most effective LSTM network design proposed in the previous work by using a promising neuroevolution method based on ant colony optimization (ACO) to develop and enhance the LSTM cell structure of the network. 
A parallelized version of the ACO neuroevolution algorithm has been developed and the evolved LSTM RNNs were compared to the previously used fixed topology. The evolved networks were trained on a large database of flight data records obtained 
from an airline containing flights that suffered from excessive vibration. Results were obtained using MPI (Message Passing Interface) on a high performance computing (HPC) cluster, evolving 1000 different LSTM cell structures using 208 cores 
over 21 days. The new evolved LSTM cells showed an improvement of 1.34%, reducing the mean prediction error from 5.61% to 4.27% when predicting excessive engine vibrations 10 s in the future, while at the same time dramatically reducing 
the number of weights from 21,170 to 13,150. The optimized LSTM also performed significantly better than traditional Nonlinear Output Error (NOE), Nonlinear AutoRegression with eXogenous (NARX) inputs, and Nonlinear Box-Jenkins (NBJ) models, 
which only reached error rates of 15.73%, 12.06% and 15.05%, respectively. Furthermore, the LSTM regularization method was used to validate the ACO. The ACO LSTM out performed the regularized LSTM by 3.35%. The NOE, NARX, and NBJ models 
were also regularized for cross validation, and the mean prediction errors were 8.70%, 9.40%, and 9.43% respectively, which gives credit for the ant colony optimized LSTM RNN."), (DEFAULT, "Cascading Ecological Impacts of Fullerenes in Freshwater Ecosystems", 
"Carbonaceous nanomaterials, such as fullerenes (C60, C70) and the derivative phenyl-C61-butyric acid methyl ester (PCBM), have promising application in solar energy technologies. Although the acute ecotoxicity of C60 has been reported widely in 
the literature, ecotoxicity assays for different fullerene forms and broader ecosystem impact studies remain scarce. To address these knowledge gaps, acute, chronic, and life stage exposure studies with freshwater zooplankton, Daphnia magna and Daphnia 
pulex, were performed for each material. Experimental results indicated that C60 and PCBM are not acutely toxic at estimated environmentally relevant concentrations; however, C70 had significant acute effects. All forms of fullerene caused a 
gradual elevation in heart rate over time and visual darkening of the Daphnia spp. carapace. The impact of fullerenes on susceptibility to predation was then assessed experimentally by presenting D. pulex to the visual predator Lepomis macrochirus 
(bluegill). Predation risk was significantly increased in fullerene-exposed D. pulex. The present study underscores the need to broaden the scope of traditional ecotoxicity for emerging materials: studies are required that evaluate portfolios of related 
nanomaterials and that capture chronic and cascading ecosystem-level effects."), (DEFAULT, "The role of ultraviolet radiation and fish in regulating the vertical distribution of Daphnia", "Previous studies have demonstrated independent effects 
of both solar ultraviolet radiation (UV; 280-400 nm) and planktivorous fish on the vertical distribution of Daphnia. We examined the behavioral response of adult and juvenile Daphnia to both UV and planktivorous fish simultaneously in a small 
temperate lake in eastern Pennsylvania by conducting a large-scale (15 m deep) in situ mesocosm experiment with full factorial treatments (± UV and ± fish). UV induced an avoidance of the surface waters in both juvenile and adult Daphnia. In contrast, 
the response of Daphnia to fish depended on the presence of UV, with a clear interactive effect. In the presence of both UV and fish, Daphnia were deeper in the water column than they were in the absence of either UV or fish. Sampling of the lake 
also revealed a rapid upward shift in the depth distribution of both juveniles and adults following a rare and intense early-summer storm that reduced the lake's transparency to both UV (for example, 380 nm UV-A) and photosynthetically active 
radiation (400-700 nm) by 44% and 39%, respectively. Evidence of a novel benefit to UV avoidance behavior was also observed: surface avoidance of UV reduces the hazards of Daphnia getting caught in the surface air-water interface and perishing. 
These results highlight the interactive effects of fish and UV on Daphnia vertical distribution under near-natural conditions in situ.");

DROP TABLE IF EXISTS FacultyAbstract;
CREATE TABLE FacultyAbstract(
    facultyID INT(9) UNSIGNED NOT NULL,
    abstractID INT(4) UNSIGNED NOT NULL,
    PRIMARY KEY (facultyID, abstractID),
    CONSTRAINT facultyAbstract_faculty_fk FOREIGN KEY (facultyID) REFERENCES Faculty (facultyID),
    CONSTRAINT facultyAbstract_abstract_fk FOREIGN KEY (abstractID) REFERENCES Abstract (abstractID)
);

INSERT INTO FacultyAbstract VALUES (112023489, 1), (112023489, 2), (220111345, 3), (111789000, 4), (110122300, 5), (232002892, 6), (350012300, 7), (350012300, 8);

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
    keyword VARCHAR(60) NOT NULL,
    PRIMARY KEY (facultyID, keyword),
    CONSTRAINT facultyInterests_faculty_fk FOREIGN KEY (facultyID) REFERENCES Faculty (facultyID)
);

INSERT INTO FacultyInterests VALUES (112023489, "Accessibility"), (112023489, "Human-Computer Interaction"), (220111345, "Accessibility"), (220111345, "Computing Education"),
(220111345, "Game Design and Development"), (111789000, "Computing Education"), (110122300, "Computing Education"), (232002892, "Artificial Intelligence"), (232002892, "Data Science"),
(322045987, "Polymer Chemistry"), (322045987, "Surface Modification by Grafting Polymerization"), (322045987, "Clean Energy/Hydrogen Fuel Cells"), (350012300, "Ecotoxicology"), 
(350012300, "UV-induced DNA Damage"), (350012300, "Freshwater Ecosystems"), (350012300, "Genomic Evolution"), (350012300, "Interdisciplinary Research"), (350012300, "Science Outreach"), 
(350012300, "Inclusive Education"), (350012300, "General Education Assessment");
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
    keyword VARCHAR(60) NOT NULL,
    PRIMARY KEY (studentID, keyword),
    CONSTRAINT studentInterests_student_fk FOREIGN KEY (studentID) REFERENCES Student (studentID)
);

-- STUDENT TABLES END