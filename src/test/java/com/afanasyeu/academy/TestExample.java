package com.afanasyeu.academy;

import com.afanasyeu.academy.model.Student;
import com.afanasyeu.academy.model.Teacher;
import com.afanasyeu.academy.service.StudentService;
import com.afanasyeu.academy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static org.springframework.test.jdbc.JdbcTestUtils.executeSqlScript;
import static org.testng.Assert.*;

@Test
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
public class TestExample extends AbstractTestNGSpringContextTests {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @BeforeMethod
    public void beforeTransaction() {
        Resource createDdl = this.applicationContext.getResource("insTeachers.sql");
        //noinspection deprecation
        executeSqlScript(jdbcTemplate,createDdl, false);
    }

    @Test
    public void tran() {
        assertNotNull(jdbcTemplate);
    }

    @Test
    void test() {
        Teacher testTeacher = new Teacher();
        testTeacher.setUserName("Teacher");
        testTeacher.setPassword("Password");
        try {
            teacherService.insertTeacher(testTeacher);
        } catch (Exception e) {
            fail("fail insert Teacher");
        }
        Teacher dbTeacher = null;
        try {
            dbTeacher = teacherService.getTeacherByLogin("Teacher", "Password");
        } catch (Exception e) {
            fail("fail getTeacherByLogin");
        }

        assertEquals(dbTeacher, testTeacher);
        Student testStudent = new Student();
        testStudent.setFirstName("Not existed firstName");
        testStudent.setLastName("Not existing lastName");
        testStudent.setDateOfBirth(new Date());
        testStudent.setEmailAddress("not@ExistedEMail.com");
        testStudent.setTeacherId(dbTeacher.getId());

        try {
            studentService.insertStudent(testStudent);
        } catch (Exception e) {
            fail("fail insertStudent");
        }
        List<Student> list = null;
        try {
            Assert.assertTrue(studentService.getStudentByFNameLNameDBirth(testStudent));
            list = studentService.getStudentsByTeacherId(dbTeacher.getId());
        } catch (Exception e) {
            fail("fail getStudentByFNameLNameDBirth or getStudentsByTeacherId");
        }
        Student dbStudent = new Student();
        int count = 0;
        for (Student s: list) {
            if (testStudent.equals(s)) {
                dbStudent = s;
                count++;
            }
        }
        assertEquals(count, 1);
        try {
            studentService.deleteStudentById(dbStudent.getId(), testTeacher.getId());
        } catch (Exception e) {
            fail("fail deleteStudentById");
        }
    }

    @AfterTest
    public void resetDate() {
        jdbcTemplate.execute("DELETE FROM student;");
        jdbcTemplate.execute("DELETE FROM teacher;");
    }
}