package com.afanasyeu.academy.service;

import com.afanasyeu.academy.mappers.TeacherMapper;
import com.afanasyeu.academy.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherMapper teacherMapper;
	
	@Transactional
	public void insertTeacher(Teacher teacher) {
		teacherMapper.insertTeacher(teacher);
	}

	public Teacher getTeacherByLogin(String userName, String password) {
		Teacher teacher = teacherMapper.getTeacherByUserName(userName);
		if(teacher != null && teacher.getPassword().equals(password)) {
			return teacher;
		}
		return null;
	}

	public boolean getTeacherByUserName(String userName) {
		Teacher teacher = teacherMapper.getTeacherByUserName(userName);
		if(teacher != null) {
			return true;
		}
		return false;
	}
}
