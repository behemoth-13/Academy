package com.afanasyeu.academy.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    private Long id;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private Date dateOfBirth;

    private Long teacherId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getDateOfBirth() {
        if (dateOfBirth != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.format(dateOfBirth);
        }
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (!getFirstName().equals(student.getFirstName())) return false;
        if (!getLastName().equals(student.getLastName())) return false;
        if (!getEmailAddress().equals(student.getEmailAddress())) return false;
        if (!(getDateOfBirth().toString()).equals(student.getDateOfBirth().toString())) return false;
        return getTeacherId().equals(student.getTeacherId());
    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getEmailAddress().hashCode();
        result = 31 * result + getDateOfBirth().hashCode();
        result = 31 * result + getTeacherId().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", teacherId=" + teacherId +
                '}';
    }
}
