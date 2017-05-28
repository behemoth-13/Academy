package com.afanasyeu.academy.service.validators;

import com.afanasyeu.academy.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("studentValidator")
public class StudentValidator implements Validator {

    private static final String REGEX_NAME = "(^[A-Z]?[a-z]+$)|(^[А-Я]?[а-я]+$)";
    private static final String REGEX_EMAIL = "^[a-z0-9.]{3,25}@[a-z.]{3,10}\\.[a-z]{2,5}$";

    @Override
    public boolean supports(Class<?> aClass) {
        return Student.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Student student = (Student) target;
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        Date dateOfBirth = student.getDateOfBirth();
        String email = student.getEmailAddress();

        validateNames(firstName, "firstName", errors);
        validateNames(lastName, "lastName", errors);

        if (dateOfBirth.after(new Date())) {
            errors.rejectValue("dateOfBirth", "date.student");
        }

        Pattern p = Pattern.compile(REGEX_EMAIL);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            errors.rejectValue("emailAddress", "email.student");
        }
    }

    private void validateNames(String check, String field, Errors errors) {
        if (check.length() < 3 || check.length() > 30) {
            errors.rejectValue(field, "length.student");
        } else {
            Pattern p = Pattern.compile(REGEX_NAME);
            Matcher m = p.matcher(check);
            if (!m.matches()) {
                errors.rejectValue(field, "pattern.student");
            }
        }
    }
}
