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
    lastName VARCHAR(50) NOT NULL,
    filiereCode VARCHAR(10) NOT NULL,
    FOREIGN KEY (filiereCode) REFERENCES Filiere(code)
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
