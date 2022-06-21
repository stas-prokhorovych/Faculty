DROP
DATABASE IF EXISTS faculty;
CREATE
DATABASE faculty;
USE
faculty;

CREATE TABLE user
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    login        VARCHAR(45) NOT NULL UNIQUE,
    password     VARCHAR(200) NOT NULL,
    email        VARCHAR(70) NOT NULL,
    role         VARCHAR(45) NOT NULL,
    first_name   VARCHAR(30) NOT NULL,
    last_name    VARCHAR(30) NOT NULL,
    phone_number VARCHAR(45) DEFAULT('-'),
    user_access  BOOLEAN DEFAULT(true)

)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE course
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(40) NOT NULL UNIQUE,
    theme         VARCHAR(45) NOT NULL,
    start_date    DATETIME NOT NULL,
    end_date      DATETIME NOT NULL,
    id_lecturer   INT,
    course_status VARCHAR(45) NOT NULL,
    FOREIGN KEY (id_lecturer) REFERENCES user (id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE course_student
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    id_user   INT NOT NULL,
    id_course INT NOT NULL,
    UNIQUE(id_user, id_course),
    FOREIGN KEY (id_user) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (id_course) REFERENCES course (id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE journal
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    id_student_course INT NOT NULL,
    mark_points       INT NOT NULL,
    mark_code         VARCHAR(1),
    mark_explanation  VARCHAR(10),
    UNIQUE(id_student_course),
    FOREIGN KEY (id_student_course) REFERENCES course_student (id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*******************************************************************************************************************************************************************************/

INSERT INTO user
VALUES (DEFAULT, 'timur33', '123', 'stas.prokhorovych@gmail.com', 'Student', 'Timur', 'Chudov', '0678553312', true),
       (DEFAULT, 'kir3il', '456', 'stas.prokhorovych@gmail.com', 'Student', 'Kiril', 'Sherstov', '0678853212', true),
       (DEFAULT, 'olga', '789', 'stas.prokhorovych@gmail.com', 'Student', 'Olga', 'Trubkina', '0678551312', true),
       (DEFAULT, 'nika', '321', 'stas.prokhorovych@gmail.com', 'Student', 'Veronika', 'Suvorina', '0678551342', true),
       (DEFAULT, 'nikita33', '7891', 'stas.prokhorovych@gmail.com', 'Student', 'Nikita', 'Trubkin', '0678551312', true),
       (DEFAULT, 'vika_1', '3211', 'stas.prokhorovych@gmail.com', 'Student', 'Victoriia', 'Eurofimova', '0678551342', true),
       (DEFAULT, 'victor_1', '789', 'stas.prokhorovych@gmail.com', 'Teacher', 'Victor', 'Goncharov', '0678551313', true),
       (DEFAULT, 'tatyana', '654', 'stas.prokhorovych@gmail.com', 'Teacher', 'Veronika', 'Goncharova', '0678551313', true),
       (DEFAULT, 'vlad_teacher', '876', 'stas.prokhorovych@gmail.com', 'Teacher', 'Vladislav', 'Sergeev', '0678551313', true),
       (DEFAULT, 'boris33', '654', 'stas.prokhorovych@gmail.com', 'Teacher', 'Boris', 'Zakharov', '0678551313', true),
       (DEFAULT, 'leon', '789', 'stas.prokhorovych@gmail.com', 'Teacher', 'Leonid', 'Kozlov', '0678551313', true),
       (DEFAULT, 'makar', '654', 'stas.prokhorovych@gmail.com', 'Teacher', 'Makar', 'Borisov', '0678551313', true),
       (DEFAULT, 'admin', '123456789', 'stas.prokhorovych@gmail.com', 'Admin', 'Stanislav', 'Prokhorovych', '0678351318', true) ;

INSERT INTO course
VALUES (DEFAULT, 'SQL', 'Computer Science', '2022-5-10 00:00:00', '2022-9-21 23:59:59', 7, 'In progress'),
       (DEFAULT, 'Java', 'Computer Science', '2022-6-1 00:00:00', '2022-10-26 23:59:59', 8, 'Opened for registration'),
       (DEFAULT, 'C++', 'Computer Science', '2022-5-28 00:00:00', '2022-10-28 23:59:59', 9, 'Opened for registration'),
       (DEFAULT, 'Python', 'Computer Science', '2022-4-1 00:00:00', '2022-10-28 23:59:59', 10, 'Opened for registration'),
       (DEFAULT, 'Javascript', 'Computer Science', '2022-3-1 00:00:00', '2022-10-28 23:59:59', 11, 'Opened for registration'),
       (DEFAULT, 'HTML', 'Computer Science', '2022-5-9 00:00:00', '2022-10-28 23:59:59', 12, 'Opened for registration'),
       (DEFAULT, 'CSS', 'Computer Science', '2022-3-1 00:00:00', '2022-10-28 23:59:59', 7, 'Opened for registration'),
       (DEFAULT, 'Algorithms', 'Computer Science', '2022-5-1 00:00:00', '2022-10-28 23:59:59', 7, 'Finished'),
       (DEFAULT, 'Data Structures', 'Computer Science', '2022-5-1 00:00:00', '2022-10-28 23:59:59', 9, 'Opened for registration'),
       (DEFAULT, 'C/C++', 'Computer Science', '2022-5-1 00:00:00', '2022-10-28 23:59:59', 10, 'Opened for registration'),
       (DEFAULT, 'AI', 'Computer Science', '2022-5-1 00:00:00', '2022-10-28 23:59:59', 11, 'Opened for registration'),
       (DEFAULT, 'Scala', 'Computer Science', '2022-5-1 00:00:00', '2022-10-28 23:59:59', NULL, 'Closed, no teacher assigned yet'),
       (DEFAULT, 'Big Data', 'Computer Science', '2022-5-1 00:00:00', '2022-10-28 23:59:59', NULL, 'Closed, no teacher assigned yet'),
       (DEFAULT, 'Arduino', 'Computer Science', '2022-5-1 00:00:00', '2022-10-28 23:59:59', NULL, 'Closed, no teacher assigned yet'),
       (DEFAULT, 'Social Skills', 'Business', '2022-5-1 00:00:00', '2022-6-19 23:59:59', 12, 'Opened for registration'),
       (DEFAULT, 'Data Analysis', 'Business', '2022-5-1 00:00:00', '2022-10-25 23:59:59', 7, 'Opened for registration'),
       (DEFAULT, 'Digital Marketing', 'Business', '2022-5-1 00:00:00', '2022-10-31 23:59:59', 8, 'Opened for registration'),
       (DEFAULT, 'Marketing Analytics', 'Business', '2022-5-1 00:00:00', '2022-10-31 23:59:59', 9, 'Opened for registration'),
       (DEFAULT, 'Brand Management', 'Business', '2022-5-1 00:00:00', '2022-10-31 23:59:59', 10, 'Opened for registration'),
       (DEFAULT, 'Social Media Management', 'Business', '2022-5-1 00:00:00', '2022-10-31 23:59:59', 11, 'Opened for registration'),
       (DEFAULT, 'Fundamentals of math', 'Mathematics', '2022-5-1 00:00:00', '2022-11-24 23:59:59', 12, 'Opened for registration'),
       (DEFAULT, 'Probability', 'Mathematics', '2022-5-1 00:00:00', '2022-11-24 23:59:59', 7, 'Opened for registration'),
       (DEFAULT, 'Linear Algebra', 'Mathematics', '2022-5-1 00:00:00', '2022-10-31 23:59:59', 8, 'Opened for registration'),
       (DEFAULT, 'Trigonometry', 'Mathematics', '2022-5-1 00:00:00', '2022-10-31 23:59:59', 9, 'Opened for registration'),
       (DEFAULT, 'Geometry', 'Mathematics', '2022-5-1 00:00:00', '2022-10-22 23:59:59', 10, 'Opened for registration');

INSERT INTO course_student
VALUES (DEFAULT, 1, 8),
       (DEFAULT, 2, 8),
       (DEFAULT, 1, 1),
       (DEFAULT, 2, 1),
       (DEFAULT, 3, 1),
       (DEFAULT, 1, 2),
       (DEFAULT, 2, 2),
       (DEFAULT, 3, 2),
       (DEFAULT, 4, 2),
       (DEFAULT, 1, 7),
       (DEFAULT, 1, 10),
       (DEFAULT, 1, 22);

INSERT INTO journal
VALUES (DEFAULT, 1, 81, 'B', 'Very Good'),
       (DEFAULT, 2, 75, 'C', 'Good');



