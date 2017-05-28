package com.afanasyeu.academy.service.validators;

import com.afanasyeu.academy.model.Teacher;
import com.afanasyeu.academy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("teacherValidator")
public class TeacherValidator implements Validator {

    @Autowired
    private TeacherService teacherService;

    private static final String REGEX_USER_NAME = "(^[A-Z]?[a-z]+$)|(^[А-Я]?[а-я]+$)";
    private static final String REGEX_PASSWORD = "^[a-zA-Z0-9_*!\\^]+$";

    @Override
    public boolean supports(Class<?> aClass) {
        return Teacher.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Teacher teacher = (Teacher) target;
        String userName = teacher.getUserName();
        String password = teacher.getPassword();
        if (userName.length() < 3 || userName.length() > 30) {
            errors.rejectValue("userName", "length.teacher.username");
        } else {
            Pattern p = Pattern.compile(REGEX_USER_NAME);
            Matcher m = p.matcher(userName);
            if (!m.matches()) {
                errors.rejectValue("userName", "pattern.teacher.username");
            } else {
                if(teacherService.getTeacherByUserName(teacher.getUserName())) {
                    errors.rejectValue("userName", "exist.teacher");
                }
            }
        }
        if (password.length() < 8 || password.length() > 15) {
            errors.rejectValue("password", "length.teacher.password");
        } else {
            Pattern p = Pattern.compile(REGEX_PASSWORD);
            Matcher m = p.matcher(password);
            if (!m.matches()) {
                errors.rejectValue("password", "pattern.teacher.password");
            }
        }
    }
}