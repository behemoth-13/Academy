package com.afanasyeu.academy.mappers;

import com.afanasyeu.academy.model.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import org.springframework.stereotype.Repository;

@Repository
public interface TeacherMapper {
	@Insert("INSERT INTO teacher(userName, password) VALUES"
			+ "(#{userName},#{password})")
	void insertTeacher(Teacher teacher);
		
	@Select("SELECT ID as id, USERNAME as userName, PASSWORD as password "
			+ "FROM teacher WHERE userName = #{userName}")
	Teacher getTeacherByUserName(String userName);
}
