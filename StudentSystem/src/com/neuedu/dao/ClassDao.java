package com.neuedu.dao;

import com.neuedu.entity.Course;

public interface ClassDao {
	
	//增加课程
	void addOneClass (Course course);
	
	//修改课程
	void updateOneClass(Object object,Object object2);
	
	//删除课程
	void delOneClass(Object object);
	

	
}	
