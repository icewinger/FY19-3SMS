package com.neuedu.test;

import java.sql.Connection;

import com.neuedu.dao.ClassDao;
import com.neuedu.dao.ClassDaoimpl;
import com.neuedu.dao.StudentDao;
import com.neuedu.dao.StudentDaoimpl;
import com.neuedu.entity.Course;
import com.neuedu.util.DBUtils;

public class Test {
	public static void main(String[] args) {
		Connection connection = DBUtils.getConnection();
		StudentDao sd = new StudentDaoimpl(connection);
	//	Student student = new Student("stu11","王英坤",250,"男");
//		List<String> courseInfoByStuId = sd.getCourseInfoByStuId("stu11");
//		System.out.println(courseInfoByStuId);
//		sd.upDateOneStudent("stuAge","290", "stu11");
		//	sd.delOneStudent("stu11");
//		List<Student> allStudent = sd.getAllStudent();
//		System.out.println(allStudent);
		
//		Student studentInFoBystuId = sd.getStudentInFoBystuId("stu11");
//		System.out.println(studentInFoBystuId);
		ClassDao cd = new ClassDaoimpl(connection);
//		Course course = new Course(4,"语文");
//		cd.addOneClass(course);
		
		//cd.updateOneClass("数学", 4);
		cd.delOneClass(4);
	}
}
