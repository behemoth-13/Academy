package com.afanasyeu.academy.service;


import com.afanasyeu.academy.mappers.StudentMapper;
import com.afanasyeu.academy.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Transactional
    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    public List<Student> getStudentsByTeacherId(Long id) {
        List<Student> students = studentMapper.getStudentsByTeacherId(id);
        return students;
    }

    @Transactional
    public void deleteStudentById(Long studentId, Long teacherId) {
        studentMapper.deleteStudentById(studentId, teacherId);
    }

    public boolean getStudentByFNameLNameDBirth(Student checkStudent) {
        Student student = studentMapper.getStudentByFNameLNameDBirth(checkStudent);
        if(student != null) {
            return true;
        }
        return false;
    }
}
