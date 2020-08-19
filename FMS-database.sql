CREATE DATABASE feedback_system;
USE feedback_system;

CREATE DATABASE login_credentials_db;
USE login_credentials_db;
CREATE TABLE login_credentials(
    user_id varchar(10) PRIMARY KEY,
    password varchar(20) NOT NULL,
    role varchar(10) NOT NULL
);
INSERT INTO login_credentials VALUES('ADM101', 'Admin@123', 'admin'),
('STD101','Student@101','student'),('STD102','Student@102','student'),('STD103','Student@103','student'),('STD104','Student@104','student'),
('TRN101','Trainer@101','trainer'),('TRN102','Trainer@102','trainer'),('TRN103','Trainer@103','trainer'),('TRN104','Trainer@104','trainer');

CREATE DATABASE trainer_db;
USE trainer_db;

CREATE TABLE trainers(
    trainer_id varchar(10) PRIMARY KEY,
    trainer_name varchar(30) NOT NULL,
    skills varchar(200) NOT NULL,
    trainer_email varchar(30) UNIQUE NOT NULL
);

INSERT INTO trainers VALUES
    ("TRN101", "Rahul Verma","Java, Spring, Servlet" , "rahulverma@gmail.com"),
    ("TRN102", "Amit Deshpande","Java, JDBC, JPA" , "amitdeshpande@gmail.com"),
    ("TRN103", "Aman Jadhav", "Node.js, Angular JS",  "amanjadhav@gmail.com"),
    ("TRN104", "Raj Kashyap", "Angular, Typescript, Javascript", "rajkashyap@gmail.com");


CREATE DATABASE student_db;
USE student_db;

CREATE TABLE students(
    student_id varchar(10) PRIMARY KEY,
    student_name varchar(30) NOT NULL,
    student_email varchar(30) UNIQUE NOT NULL,
    batch varchar(3) REFERENCES batch_courses.batch
);

INSERT INTO students VALUES
    ("STD101", "Sahil Gupta", "sahilupta261@gmail.com", "A"),
    ("STD102", "Raju Deka", "raju95@gmail.com", "A"),
    ("STD103", "Amit Yadav",  "amit067@gmail.com", "B"),
    ("STD104", "Abhishek Yadav", "abhishek2002@gmail.com", "B");


CREATE DATABASE program_db;
USE program_db;

CREATE TABLE programs(
    program_id varchar(10) PRIMARY KEY,
    program_name varchar(50) NOT NULL
);

INSERT INTO programs VALUES
    ("PRG101", "DevOps"),
    ("PRG102", "Oracle"),
    ("PRG103", "MVC"),
    ("PRG104", "Spring");

CREATE DATABASE course_db;
USE course_db;

CREATE TABLE courses(
    course_id varchar(10) PRIMARY KEY,
    course_name varchar(50) UNIQUE NOT NULL
);

INSERT INTO courses VALUES
    ("CRS101", "Java Cloud"),
    ("CRS102", "Java Big Data"),
    ("CRS103", "Java"),
    ("CRS104", "Salesforce");


CREATE DATABASE program_trainer_db;
USE program_trainer_db;

CREATE TABLE program_trainers(
    trainer_id varchar(10) REFERENCES trainers.trainer_id,
    program_id varchar(10) REFERENCES programs.program_id,
    batch varchar(3) REFERENCES batch_courses.batch,
    PRIMARY KEY(trainer_id, program_id, batch)
);

INSERT INTO program_trainers VALUES
    ("TRN101", "PRG101", "A"),
    ("TRN102", "PRG103", "A"),
    ("TRN103", "PRG102", "B"),
    ("TRN104", "PRG104", "B"),
    ("TRN101", "PRG103", "B"),
    ("TRN102", "PRG101", "B");


CREATE DATABASE course_program_db;
USE course_program_db;

CREATE TABLE course_programs(
    course_id varchar(10) REFERENCES courses.course_id,
    program_id varchar(10) REFERENCES programs.program_id,
    start_date varchar(10) NOT NULL,
    end_date varchar(10) NOT NULL,
    PRIMARY KEY(course_id, program_id)
);

INSERT INTO course_programs VALUES
    ("CRS101","PRG101", "2020-04-10", "2020-04-17"),
    ("CRS101","PRG103", "2020-04-18", "2020-04-25"),
    ("CRS102","PRG101", "2020-04-10", "2020-04-17"),
    ("CRS102","PRG102", "2020-04-18", "2020-04-25"),
    ("CRS102","PRG104", "2020-04-26", "2020-05-05"),
    ("CRS103","PRG101", "2020-05-20", "2020-05-27"),
    ("CRS103","PRG102", "2020-03-27", "2020-04-05"),
    ("CRS103","PRG103", "2020-04-05", "2020-04-12"),
    ("CRS103","PRG104", "2020-04-15", "2020-04-25");

CREATE DATABASE batch_course_db;
USE batch_course_db;

CREATE TABLE batch_courses(
    batch varchar(3) PRIMARY KEY,
    course_id varchar(10) REFERENCES courses.course_id
);
INSERT INTO batch_courses VALUES("A","CRS101"), ("B","CRS103"), ("C","CRS102");


CREATE DATABASE admin_db;
USE admin_db;

CREATE TABLE admin(
    admin_id varchar(10) PRIMARY KEY,
    admin_name varchar(30) NOT NULL
);

INSERT INTO admin VALUES("ADM101","Aman Goel");


CREATE DATABASE feedback_db;
USE feedback_db;

CREATE TABLE feedbacks(
    feedback_id INT(10) AUTO_INCREMENT PRIMARY KEY,
    student_id varchar(10) REFERENCES students.student_id,
    trainer_id varchar(10) REFERENCES trainers.trainer_id,
    program_id varchar(10) REFERENCES programs.program_id,
    q1 int,
    q2 int,
    q3 int,
    q4 int,
    q5 int,
    comments varchar(50),
    suggestions varchar(50)
);

INSERT INTO feedbacks(student_id,trainer_id,program_id,q1,q2,q3,q4,q5,comments,suggestions) VALUES
    ("STD101", "TRN101", "PRG101", 3, 4, 3, 4, 4, "Great.", "The course was great"),
    ("STD102", "TRN101", "PRG101", 5, 4, 3, 3, 4, "Good.", "The course was good"),
    ("STD102", "TRN102", "PRG103", 2, 3, 2, 2, 1, "BAD.", "The course was bad"),
    ("STD103", "TRN104", "PRG102", 5, 3, 4, 3, 5, "Fine.", "The course was Fine");

login_credentials_db.*, trainer_db.*, student_db.*, program_db.*, program_trainer_db.*, course_db.*, course_program_db.*, batch_course_db.*, feedback_db.*, admin_db.*


GRANT ALL PRIVILEGES ON database_name.* TO 'username'@'localhost';

GRANT ALL PRIVILEGES ON database_name.* TO 'aman_developer'@'%';

GRANT CREATE, DELETE, UPDATE, INSERT, SELECT on login_credentials_db.* to 'aman_developer';
GRANT CREATE, DELETE, UPDATE, INSERT, SELECT on trainer_db.* to 'aman_developer';
GRANT CREATE, DELETE, UPDATE, INSERT, SELECT on student_db.* to 'aman_developer';
GRANT CREATE, DELETE, UPDATE, INSERT, SELECT on program_db.* to 'aman_developer';
GRANT CREATE, DELETE, UPDATE, INSERT, SELECT on program_trainer_db.* to 'aman_developer';
GRANT CREATE, DELETE, UPDATE, INSERT, SELECT on course_db.* to 'aman_developer';
GRANT CREATE, DELETE, UPDATE, INSERT, SELECT on course_program_db.* to 'aman_developer';
GRANT CREATE, DELETE, UPDATE, INSERT, SELECT on batch_course_db.* to 'aman_developer';
GRANT CREATE, DELETE, UPDATE, INSERT, SELECT on feedback_db.* to 'aman_developer';
GRANT CREATE, DELETE, UPDATE, INSERT, SELECT on admin_db.* to 'aman_developer';

select p.program_id from students s, batch_courses b left join course_programs p USING(course_id) where b.batch=s.batch AND s.student_id='STD104';

select p.program_id, p.program_name, t.trainer_id, t.trainer_name, cp.start_date, cp.end_date from students s inner join batch_courses bc USING(batch) inner join course_programs cp USING(course_id) inner join program_trainers pt USING(program_id,batch) inner join programs p USING(program_id) inner Join trainers t USING(trainer_id) WHERE student_id='STD104';

SELECT p.program_id AS programId, p.program_name AS programName, t.trainer_id AS trainerId, t.trainer_name AS trainerName, cp.start_date AS startDate, cp.end_date AS endDate from students INNER JOIN batch_courses USING(batch) INNER JOIN course_programs cp USING(course_id) INNER JOIN program_trainers USING(program_id,batch) INNER JOIN programs p USING(program_id) INNER JOIN trainers t USING(trainer_id) WHERE student_id=:id

SELECT * from students INNER JOIN batch_courses USING(batch) INNER JOIN course_programs cp USING(course_id) INNER JOIN program_trainers USING(program_id,batch) INNER JOIN programs p USING(program_id) INNER JOIN trainers t USING(trainer_id);

SELECT batch, program_id, student_name, student_id, student_email, start_date, end_date FROM students INNER JOIN batch_courses USING(batch) INNER JOIN course_programs USING(course_id) INNER JOIN program_trainers USING(program_id, batch) left join feedbacks USING(student_id,program_id, trainer_id) WHERE feedback_id IS NULL AND CURDATE()>DATE_ADD(end_date, INTERVAL 20 DAY) AND trainer_id=:id

SELECT * FROM students INNER JOIN batch_courses USING(batch) INNER JOIN course_programs USING(course_id) INNER JOIN program_trainers USING(program_id, batch) WHERE CURDATE()>DATE_ADD(end_date, INTERVAL 20 DAY) AND trainer_id=:id

SELECT batch, student_id, student_name,student_email, start_date, end_date FROM students INNER JOIN batch_courses USING(batch) INNER JOIN course_programs cp USING(course_id) WHERE CURDATE()>DATE_ADD(end_date, INTERVAL 20 DAY) AND program_id=:id


SELECT batch, student_id, student_name,student_email, start_date, end_date FROM students INNER JOIN batch_courses USING(batch) INNER JOIN course_programs cp USING(course_id) left join feedbacks USING(student_id,program_id) WHERE feedback_id IS NULL AND CURDATE()>DATE_ADD(end_date, INTERVAL 20 DAY) AND program_id=:id