package com.afanasyeu.academy.service;

import com.afanasyeu.academy.model.Teacher;

public interface TeacherService {
	void insertTeacher(Teacher teacher);
	Teacher getTeacherByLogin(String userName, String password);
	boolean getTeacherByUserName(String userName);
}
