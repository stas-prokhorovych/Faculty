DROP DATABASE IF EXISTS faculty;
CREATE DATABASE faculty;
USE faculty;

CREATE TABLE roles (
    id_role INT PRIMARY KEY,
    name_role VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(45) UNIQUE,
    password VARCHAR(200),
    email VARCHAR(70),
    id_role INT,
    FOREIGN KEY (id_role) REFERENCES roles(id_role) ON DELETE CASCADE
);

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR (30) NOT NULL,
    patronymic VARCHAR (20) NOT NULL,
    student_access BOOLEAN,
    id_user INT,
    FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE teachers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR (30) NOT NULL,
    patronymic VARCHAR (20) NOT NULL,
    id_user INT,
    FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE admins (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR (30) NOT NULL,
    patronymic VARCHAR (20) NOT NULL,
    id_user INT,
    FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE themes (
    id_theme INT PRIMARY KEY AUTO_INCREMENT,
    name_theme VARCHAR (30) NOT NULL UNIQUE
);

CREATE TABLE courses (
    id_course INT PRIMARY KEY AUTO_INCREMENT,
    name_course VARCHAR (40) NOT NULL UNIQUE,
    id_theme INT,
    id_lecturer INT,
    FOREIGN KEY (id_theme) REFERENCES themes(id_theme) ON DELETE CASCADE,
    FOREIGN KEY (id_lecturer) REFERENCES teachers(id) ON DELETE CASCADE
);

CREATE TABLE course_student (
    id_student_course INT PRIMARY KEY AUTO_INCREMENT,
    course_state VARCHAR(20),
    id_student INT,
    id_course INT,
    FOREIGN KEY (id_student) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (id_course) REFERENCES courses(id_course) ON DELETE CASCADE
);

CREATE TABLE journal (
    id_student_course INT PRIMARY KEY,
    mark INT NOT NULL,
    FOREIGN KEY (id_student_course) REFERENCES course_student(id_student_course) ON DELETE CASCADE
);


/**********************************************************************************************************************/

INSERT INTO roles VALUES
(1, 'Student'),
(2, 'Teacher'),
(3, 'Admin');

INSERT INTO users VALUES
(DEFAULT, 'timur33', '123', NULL, 1),
(DEFAULT, 'kir3il', '456', NULL, 1),
(DEFAULT, 'olga', '789', NULL, 1),
(DEFAULT, 'nika', '321', NULL, 1),
(DEFAULT, 'victor_1', '789', NULL, 2),
(DEFAULT, 'tatyana', '654', NULL, 2),
(DEFAULT, 'admin', '123456789', NULL, 3);

INSERT INTO students VALUES
(DEFAULT, 'Timur', 'Chudov', 'Daniilovich', true, 1),
(DEFAULT, 'Kiril', 'Sherstov', 'Dementievich',true, 2),
(DEFAULT, 'Olga', 'Trubkina', 'Vsevolodovna', true, 3),
(DEFAULT, 'Veronika', 'Suvorina', 'Anisievna', true, 4);

INSERT INTO teachers VALUES
(DEFAULT, 'Victor', 'Goncharov', 'Samsonovich', 5),
(DEFAULT, 'Veronika', 'Goncharova', 'Vsevolodovna', 6);

INSERT INTO admins VALUES
(DEFAULT, 'Stanislav', 'Prokhorovych', 'Valerievich',7);

INSERT INTO themes VALUES
(1, 'Computer Science'),
(2, 'Business'),
(3, 'Mathematics');

INSERT INTO courses VALUES
(1, 'SQL', 1,1),
(2, 'Java', 1,2),
(3, 'C++', 1,1),
(4, 'Social Skills', 2,2),
(5, 'Data Analysis', 2,1),
(6, 'Digital Marketing', 2,2),
(7, 'Probability', 3,1),
(8, 'Linear Algebra', 3,2),
(9, 'Geometry', 3,1);


