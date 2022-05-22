DROP DATABASE IF EXISTS faculty;
CREATE DATABASE faculty;
USE faculty;

CREATE TABLE user (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      login VARCHAR(45) UNIQUE,
                      password VARCHAR(200),
                      email VARCHAR(70),
                      role VARCHAR(45),
                      first_name VARCHAR(30) NOT NULL,
                      last_name VARCHAR (30) NOT NULL,
                      phone_number VARCHAR(45)
);

CREATE TABLE course (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR (40) NOT NULL UNIQUE,
                        theme VARCHAR(45),
                        duration_in_days INT,
                        id_lecturer INT,
                        FOREIGN KEY (id_lecturer) REFERENCES user(id) ON DELETE CASCADE
);

INSERT INTO user VALUES
                     (DEFAULT, 'timur33', '123', NULL, 'Student', 'Timur', 'Chudov','0678553312'),
                     (DEFAULT, 'kir3il', '456', NULL, 'Student', 'Kiril', 'Sherstov','0678853212'),
                     (DEFAULT, 'olga', '789', NULL, 'Student', 'Olga', 'Trubkina','0678551312'),
                     (DEFAULT, 'nika', '321', NULL, 'Student', 'Veronika', 'Suvorina','0678551342'),
                     (DEFAULT, 'victor_1', '789', NULL, 'Teacher', 'Victor', 'Goncharov','0678551313'),
                     (DEFAULT, 'tatyana', '654', NULL, 'Teacher', 'Veronika', 'Goncharova', '0678551313'),
                     (DEFAULT, 'admin', '123456789', NULL, 'Admin', 'Stanislav', 'Prokhorovych', '0678351318');


INSERT INTO course VALUES
                       (DEFAULT, 'SQL', 'Computer Science', 30, 5),
                       (DEFAULT, 'Java', 'Computer Science', 30, 6),
                       (DEFAULT, 'C++', 'Computer Science', 30, 5),
                       (DEFAULT, 'Social Skills', 'Business', 45, 6),
                       (DEFAULT, 'Data Analysis', 'Business', 45, 5),
                       (DEFAULT, 'Digital Marketing', 'Business', 45, 6),
                       (DEFAULT, 'Probability', 'Mathematics', 60, 5),
                       (DEFAULT, 'Linear Algebra', 'Mathematics', 60, 6),
                       (DEFAULT, 'Geometry', 'Mathematics', 60, 5);


