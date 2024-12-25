package org.example.models.note;

import org.example.models.academique.ModuleElement;
import org.example.models.user.Student.Student;

public class StudentGradeBuilder {
    private Double grade;
    private StudentGradeId studentGradeId;

    public StudentGradeBuilder grade(Double grade) {
        this.grade = grade;
        return this;
    }

    public StudentGradeBuilder studentGradeId(StudentGradeId studentGradeId) {
        this.studentGradeId = studentGradeId;
        return this;
    }

    public  StudentGrade build() {
        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setGrade(this.grade);
        studentGrade.setStudentGradeId(this.studentGradeId);
        return studentGrade;
    }
}