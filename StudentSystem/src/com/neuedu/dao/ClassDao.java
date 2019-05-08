package com.neuedu.dao;

import com.neuedu.entity.Course;

public interface ClassDao {
	
	//增加课程
	void addOneClass (Course course);
	
	//修改课程
	void updateOneClass(Object object,Object object2);
	
	//删除课程
	void delOneClass(Object object);
	
	//根据课程号添加学习此课程的学生
	void addOneStudentByClassId(Object object,Object object2);
	
	//根据课程号删除学习此课程的学生
	void depOneStudentByClassId(Object object);
}	
