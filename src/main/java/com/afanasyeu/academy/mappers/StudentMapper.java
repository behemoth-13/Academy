package com.afanasyeu.academy.mappers;

import com.afanasyeu.academy.model.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    @Insert("INSERT INTO student(firstName,"
            + "lastName, dateOfBirth, emailAddress , teacher_id) VALUES"
            + "(#{firstName}, #{lastName},"
            + "#{dateOfBirth}, #{emailAddress}, #{teacherId})")
    @Options(useGeneratedKeys=true, keyProperty="id", flushCache=true, keyColumn="id")
    void insertStudent(Student student);

    @Select("SELECT * FROM student WHERE teacher_id = #{id}")
    @Results(value={
            @Result(property="teacherId", column ="teacher_id" )
    })
    List<Student> getStudentsByTeacherId(Long id);

    @Delete("DELETE FROM student WHERE id =#{studentId} AND teacher_id = #{teacherId}")
    void deleteStudentById(@Param("studentId")Long studentId, @Param("teacherId") Long teacherId);

    @Select("SELECT FIRSTNAME as firstName, LASTNAME as lastName, "
            + "DATEOFBIRTH as dateOfBirth, EMAILADDRESS as emailAddress "
            + "FROM student WHERE firstName = #{firstName} AND lastName = #{lastName} " +
            "AND dateOfBirth = #{dateOfBirth}")
    @Results(value={
            @Result(property="teacherId", column ="teacher_id" )
    })
    Student getStudentByFNameLNameDBirth(Student checkStudent);
}