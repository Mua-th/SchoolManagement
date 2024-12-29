-- Database schema for the provided class diagram
use schoolmanagement ;
-- Table: Users
CREATE TABLE Users (
    id VARCHAR(36) PRIMARY KEY,
    login VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    role ENUM('Administrator', 'Professor') NOT NULL
);

-- Table: Filiere
CREATE TABLE Filiere (
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Table: Module
CREATE TABLE Module (
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    semester ENUM('semestre 1', 'semestre 2') NOT NULL,
    filiereCode VARCHAR(10),
    FOREIGN KEY (filiereCode) REFERENCES Filiere(code) ON DELETE CASCADE
);

-- Table: ModuleElement
CREATE TABLE ModuleElement (
    code VARCHAR(10) PRIMARY KEY,
    coefficient DOUBLE NOT NULL,
    isValidated BOOLEAN NOT NULL DEFAULT FALSE,
    moduleCode VARCHAR(10),
    FOREIGN KEY (moduleCode) REFERENCES Module(code) ON DELETE CASCADE
);

-- Table: Students
CREATE TABLE Students (
    id VARCHAR(36) PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL
);

-- Table: StudentGrade
CREATE TABLE StudentGrade (
    studentId VARCHAR(36),
    moduleElementCode VARCHAR(10),
    modality ENUM('Exam', 'Project', 'Assignment') NOT NULL,
    grade DOUBLE,
    isAbsent BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (studentId, moduleElementCode, modality),
    FOREIGN KEY (studentId) REFERENCES Students(id) ON DELETE CASCADE,
    FOREIGN KEY (moduleElementCode) REFERENCES ModuleElement(code) ON DELETE CASCADE
);
-- Table: ProfessorFiliere
CREATE TABLE ProfessorFiliere (
    professorId VARCHAR(36),
    filiereCode VARCHAR(10),
    PRIMARY KEY (professorId, filiereCode),
    FOREIGN KEY (professorId) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (filiereCode) REFERENCES Filiere(code) ON DELETE CASCADE
);

-- Table: ProfessorModuleElement
CREATE TABLE ProfessorModuleElement (
    professorId VARCHAR(36),
    moduleElementCode VARCHAR(10),
    PRIMARY KEY (professorId, moduleElementCode),
    FOREIGN KEY (professorId) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (moduleElementCode) REFERENCES ModuleElement(code) ON DELETE CASCADE
);

-- Seed data

-- Users
INSERT INTO Users (id, login, password, firstName, lastName, role) VALUES
('admin-1', 'admin', 'admin123', 'Alice', 'Smith', 'Administrator'),
('prof-1', 'prof1', 'prof123', 'Bob', 'Jones', 'Professor');

-- Filiere
INSERT INTO Filiere (code, name) VALUES
('CS', 'Computer Science'),
('EE', 'Electrical Engineering');

-- Module
INSERT INTO Module (code, name, semester, filiereCode) VALUES
('CS101', 'Programming Basics', 'semestre 1', 'CS'),
('EE101', 'Circuits', 'semestre 2', 'EE');

-- ModuleElement
INSERT INTO ModuleElement (code, coefficient, isValidated, moduleCode) VALUES
('CS101-1', 1.0, FALSE, 'CS101'),
('CS101-2', 0.5, FALSE, 'CS101'),
('EE101-1', 1.5, FALSE, 'EE101');

-- Students
INSERT INTO Students (id, firstName, lastName) VALUES
('stu-1', 'Charlie', 'Brown'),
('stu-2', 'Dana', 'White');

-- StudentGrade
INSERT INTO StudentGrade (studentId, moduleElementCode, modality, grade, isAbsent) VALUES
('stu-1', 'CS101-1', 'Exam', 85.0, FALSE),
('stu-1', 'CS101-2', 'Project', 90.0, FALSE),
('stu-2', 'EE101-1', 'Exam', NULL, TRUE);

-- ProfessorFiliere
INSERT INTO ProfessorFiliere (professorId, filiereCode) VALUES
('prof-1', 'CS');

-- ProfessorModuleElement
INSERT INTO ProfessorModuleElement (professorId, moduleElementCode) VALUES
('prof-1', 'CS101-1'),
('prof-1', 'CS101-2');
