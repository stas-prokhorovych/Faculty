DROP
DATABASE IF EXISTS faculty;
CREATE
DATABASE faculty;
USE
faculty;

CREATE TABLE user
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    login        VARCHAR(45) UNIQUE,
    password     VARCHAR(200),
    email        VARCHAR(70),
    role         VARCHAR(45),
    first_name   VARCHAR(30) NOT NULL,
    last_name    VARCHAR(30) NOT NULL,
    phone_number VARCHAR(45),
    user_access  BOOLEAN
);

CREATE TABLE course
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(40) NOT NULL UNIQUE,
    theme         VARCHAR(45),
    start_date    DATETIME,
    end_date      DATETIME,
    id_lecturer   INT,
    course_status VARCHAR(45),
    FOREIGN KEY (id_lecturer) REFERENCES user (id) ON DELETE CASCADE
);

CREATE TABLE course_student
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    id_user   INT,
    id_course INT,
    FOREIGN KEY (id_user) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (id_course) REFERENCES course (id) ON DELETE CASCADE
);

CREATE TABLE journal
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    id_student_course INT,
    mark              INT NOT NULL,
    FOREIGN KEY (id_student_course) REFERENCES course_student (id) ON DELETE CASCADE
);

/*******************************************************************************************************************************************************************************/

INSERT INTO user
VALUES (DEFAULT, 'timur33', '123', NULL, 'Student', 'Timur', 'Chudov', '0678553312', true),
       (DEFAULT, 'kir3il', '456', NULL, 'Student', 'Kiril', 'Sherstov', '0678853212', true),
       (DEFAULT, 'olga', '789', NULL, 'Student', 'Olga', 'Trubkina', '0678551312', true),
       (DEFAULT, 'nika', '321', NULL, 'Student', 'Veronika', 'Suvorina', '0678551342', true),
       (DEFAULT, 'victor_1', '789', NULL, 'Teacher', 'Victor', 'Goncharov', '0678551313', true),
       (DEFAULT, 'tatyana', '654', NULL, 'Teacher', 'Veronika', 'Goncharova', '0678551313', true),
       (DEFAULT, 'admin', '123456789', NULL, 'Admin', 'Stanislav', 'Prokhorovych', '0678351318', true);

INSERT INTO course
VALUES (DEFAULT, 'SQL', 'Computer Science', '2022-5-1 00:00:00', '2022-9-21 23:59:59', 5, 'Opened'),
       (DEFAULT, 'Java', 'Computer Science', '2022-5-1 00:00:00', '2022-10-26 23:59:59', 6, 'Opened'),
       (DEFAULT, 'C++', 'Computer Science', '2022-5-1 00:00:00', '2022-10-28 23:59:59', 5, 'Opened'),
       (DEFAULT, 'Social Skills', 'Business', '2022-5-1 00:00:00', '2022-6-19 23:59:59', 6, 'Opened'),
       (DEFAULT, 'Data Analysis', 'Business', '2022-5-1 00:00:00', '2022-10-31 23:59:59', 5, 'Opened'),
       (DEFAULT, 'Digital Marketing', 'Business', '2022-5-1 00:00:00', '2022-10-25 23:59:59', 6, 'Opened'),
       (DEFAULT, 'Probability', 'Mathematics', '2022-5-1 00:00:00', '2022-11-24 23:59:59', 5, 'Opened'),
       (DEFAULT, 'Linear Algebra', 'Mathematics', '2022-5-1 00:00:00', '2022-10-22 23:59:59', 6, 'Opened'),
       (DEFAULT, 'Geometry', 'Mathematics', '2022-5-1 00:00:00', '2022-10-22 23:59:59', 5, 'Opened');


