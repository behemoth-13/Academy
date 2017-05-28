package com.afanasyeu.academy.service;

import com.afanasyeu.academy.model.Student;

import java.util.List;

public interface StudentService {
    void insertStudent(Student student);
    List<Student> getStudentsByTeacherId(Long id);
    void deleteStudentById(Long studentId, Long teacherId);
    boolean getStudentByFNameLNameDBirth(Student checkStudent);
}
