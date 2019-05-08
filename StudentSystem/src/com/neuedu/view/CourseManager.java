package com.neuedu.view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.neuedu.dao.ClassDaoimpl;
import com.neuedu.entity.Course;
import com.neuedu.entity.Student;
import com.neuedu.util.DBUtils;

public class CourseManager {

	public CourseManager() {
		super();
	}

	StudentClient sc = new StudentClient();
	ClassDaoimpl cd = new ClassDaoimpl(DBUtils.getConnection());
	
	
	Scanner scanner = new Scanner(System.in);

	public void CourseManagerIndex() {
		System.out.println("**********请选择要操作的信息对应数字****************");
		System.out.println(
				"*1.添加课程      2.修改课程       3.删除课程     4.根据学号查询选课情况      5.根据课程号查询选择该课程学生的信息      6.根据课程号添加学习此课程的学生      7.根据课程号删除学习此课程的学生     8.返回上级菜单");
		System.out.println("**************************************");
		System.out.println("请选择:");
		int nextInt = scanner.nextInt();
		switch (nextInt) {
		case 1:
			Integer id = this.case1();
			System.out.println("请输入课程名称");
			String courseName = scanner.next();
			Course course = new Course(id, courseName);
			cd.addOneClass(course);
			System.out.println("添加成功，自动返回上层目录");
			CourseManagerIndex();
			break;
		case 2:
			Integer id2 = this.case2();
			System.out.println("请输入修改后的课程名称");
			String courseName2 = scanner.next();
			cd.updateOneClass(courseName2, id2);
			System.out.println("修改成功，自动返回上层目录");
			CourseManagerIndex();
			break;
		case 3:
			Integer id3 = this.case2();
			cd.delOneClass(id3);
			System.out.println("删除成功，自动返回上层目录");
			CourseManagerIndex();
			break;
		case 4:
			String id4 = this.case3();
			List<Course> case4 = case4(id4);
			for (Course course2 : case4) {
				System.out.println(course2);
			}
			System.out.println("查询完毕，自动返回上层目录");
			CourseManagerIndex();
			break;
		case 5:
			Integer id5 = this.case2();
			List<Student> case5 = case5(id5);
			for (Student student : case5) {
				System.out.println(student);
			}
			System.out.println("查询完毕，自动返回上层目录");
			CourseManagerIndex();
			break;
		case 6:
			String id6 = this.case3();
			Integer courseid = this.case601();
			System.out.println(courseid);
			case6(id6,courseid);
			cd.addOneStudentByClassId(id6, courseid);
			System.out.println("添加完毕，自动返回上层目录");
			CourseManagerIndex();
			break;
		case 7:
			String checkCourseName = this.checkCourseName();
			System.out.println();
			Integer checkCourseIdByCourseName = checkCourseIdByCourseName(checkCourseName);
			System.out.println(checkCourseIdByCourseName);
			cd.depOneStudentByClassId(checkCourseIdByCourseName);
			System.out.println("操作完毕，自动返回上层目录");
			CourseManagerIndex();
			break;
		case 8:
			ManagerPage mp = new ManagerPage();
			mp.stuManagerIndex();
		}
	}

	public Integer case1() {
		System.out.println("请输入课程ID");
		Integer id = scanner.nextInt();
		boolean checkId = sc.checkCourseId(id);
		if (checkId == true) {
			return id;
		} else {
			System.out.println("此ID已存在，请重新输入");
			this.case1();
		}
		return null;
	}

	public Integer case2() {
		System.out.println("请输入课程ID");
		Integer id = scanner.nextInt();
		boolean checkId = sc.checkCourseId(id);
		if (checkId == false) {
			return id;
		} else {
			System.out.println("此ID不存在，请重新输入");
			this.case2();
		}
		return null;
	}

	public String case3() {
		System.out.println("请输入学生ID");
		String id = scanner.next();
		boolean checkId = sc.checkId(id);
		if (checkId == true) {
			return id;
		} else {
			System.out.println("此ID不存在，请重新输入");
			this.case3();
			return null;
		}
	}

	public List<Course> case4(String inte) {
		List<Course> list = new ArrayList<>();
		String sql = "SELECT courseId,courseName\r\n" + "FROM course\r\n" + "JOIN stuandcourse\r\n"
				+ "ON cId = courseId\r\n" + "WHERE sId = ?;";
		try {
			PreparedStatement prepareStatement = DBUtils.getConnection().prepareStatement(sql);
			prepareStatement.setString(1, inte);
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				Integer int1 = rs.getInt("courseId");
				String string = rs.getString("courseName");
				Course course = new Course(int1, string);
				list.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Student> case5(Integer inte) {
		List<Student> list = new ArrayList<>();
		String sql = "SELECT stuId,stuName,stuAge,stuSex FROM student\r\n" + "JOIN stuandcourse\r\n"
				+ "ON sId = stuId\r\n" + "WHERE cId = ?";
		try {
			PreparedStatement prepareStatement = DBUtils.getConnection().prepareStatement(sql);
			prepareStatement.setInt(1, inte);
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				String id = rs.getString("stuId");
				String name = rs.getString("stuName");
				int age = rs.getInt("stuAge");
				String sex = rs.getString("stuSex");
				Student student = new Student(id, name, age, sex);
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public Integer case601()
	{
		System.out.println("请输入课程ID");
		Integer id = scanner.nextInt();
		System.out.println(id);
		boolean checkCourseId = sc.checkCourseId(id);
		if (checkCourseId == false) {
		} else {
			System.out.println("没有此课程，请重新输入");
			case601();
		}
		return id;
	}

	public void case6(String str,Integer id) {
		boolean cf = sc.checkCourseIdForStudentCourse(str, id);
		if (cf == false) {
		} else {
			System.out.println("此学生已选过此课程，请重新输入");
			case601();
		}
	}
		public String checkCourseName()
		{
			System.out.println("请输入课程名");
			String couresName = scanner.next();
			boolean checkCourseName = sc.checkCourseName(couresName);
			if(checkCourseName==true) 
			{
				return couresName;
			}else {
				System.out.println("课程名输入错误，请重新输入");
				checkCourseName();
				return null;
			}
		}
		public Integer checkCourseIdByCourseName(String str)
		{
			String sql = "SELECT courseId FROM course WHERE courseName = ? ;";
			Integer inte =null;
			try {
				PreparedStatement prepareStatement = DBUtils.getConnection().prepareStatement(sql);
				prepareStatement.setString(1, str);
				ResultSet rs = prepareStatement.executeQuery();
				while(rs.next())
				{
					inte = rs.getInt("courseId");
				}
				return inte;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
		}
}
