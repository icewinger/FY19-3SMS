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
		System.out.println("**********��ѡ��Ҫ��������Ϣ��Ӧ����****************");
		System.out.println(
				"*1.��ӿγ�      2.�޸Ŀγ�       3.ɾ���γ�     4.����ѧ�Ų�ѯѡ�����      5.���ݿγ̺Ų�ѯѡ��ÿγ�ѧ������Ϣ      6.���ݿγ̺����ѧϰ�˿γ̵�ѧ��      7.���ݿγ̺�ɾ��ѧϰ�˿γ̵�ѧ��     8.�����ϼ��˵�");
		System.out.println("**************************************");
		System.out.println("��ѡ��:");
		int nextInt = scanner.nextInt();
		switch (nextInt) {
		case 1:
			Integer id = this.case1();
			System.out.println("������γ�����");
			String courseName = scanner.next();
			Course course = new Course(id, courseName);
			cd.addOneClass(course);
			System.out.println("��ӳɹ����Զ������ϲ�Ŀ¼");
			CourseManagerIndex();
			break;
		case 2:
			Integer id2 = this.case2();
			System.out.println("�������޸ĺ�Ŀγ�����");
			String courseName2 = scanner.next();
			cd.updateOneClass(courseName2, id2);
			System.out.println("�޸ĳɹ����Զ������ϲ�Ŀ¼");
			CourseManagerIndex();
			break;
		case 3:
			Integer id3 = this.case2();
			cd.delOneClass(id3);
			System.out.println("ɾ���ɹ����Զ������ϲ�Ŀ¼");
			CourseManagerIndex();
			break;
		case 4:
			String id4 = this.case3();
			List<Course> case4 = case4(id4);
			for (Course course2 : case4) {
				System.out.println(course2);
			}
			System.out.println("��ѯ��ϣ��Զ������ϲ�Ŀ¼");
			CourseManagerIndex();
			break;
		case 5:
			Integer id5 = this.case2();
			List<Student> case5 = case5(id5);
			for (Student student : case5) {
				System.out.println(student);
			}
			System.out.println("��ѯ��ϣ��Զ������ϲ�Ŀ¼");
			CourseManagerIndex();
			break;
		case 6:
			String id6 = this.case3();
			Integer courseid = this.case601();
			System.out.println(courseid);
			case6(id6,courseid);
			cd.addOneStudentByClassId(id6, courseid);
			System.out.println("�����ϣ��Զ������ϲ�Ŀ¼");
			CourseManagerIndex();
			break;
		case 7:
			String checkCourseName = this.checkCourseName();
			System.out.println();
			Integer checkCourseIdByCourseName = checkCourseIdByCourseName(checkCourseName);
			System.out.println(checkCourseIdByCourseName);
			cd.depOneStudentByClassId(checkCourseIdByCourseName);
			System.out.println("������ϣ��Զ������ϲ�Ŀ¼");
			CourseManagerIndex();
			break;
		case 8:
			ManagerPage mp = new ManagerPage();
			mp.stuManagerIndex();
		}
	}

	public Integer case1() {
		System.out.println("������γ�ID");
		Integer id = scanner.nextInt();
		boolean checkId = sc.checkCourseId(id);
		if (checkId == true) {
			return id;
		} else {
			System.out.println("��ID�Ѵ��ڣ�����������");
			this.case1();
		}
		return null;
	}

	public Integer case2() {
		System.out.println("������γ�ID");
		Integer id = scanner.nextInt();
		boolean checkId = sc.checkCourseId(id);
		if (checkId == false) {
			return id;
		} else {
			System.out.println("��ID�����ڣ�����������");
			this.case2();
		}
		return null;
	}

	public String case3() {
		System.out.println("������ѧ��ID");
		String id = scanner.next();
		boolean checkId = sc.checkId(id);
		if (checkId == true) {
			return id;
		} else {
			System.out.println("��ID�����ڣ�����������");
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
		System.out.println("������γ�ID");
		Integer id = scanner.nextInt();
		System.out.println(id);
		boolean checkCourseId = sc.checkCourseId(id);
		if (checkCourseId == false) {
		} else {
			System.out.println("û�д˿γ̣�����������");
			case601();
		}
		return id;
	}

	public void case6(String str,Integer id) {
		boolean cf = sc.checkCourseIdForStudentCourse(str, id);
		if (cf == false) {
		} else {
			System.out.println("��ѧ����ѡ���˿γ̣�����������");
			case601();
		}
	}
		public String checkCourseName()
		{
			System.out.println("������γ���");
			String couresName = scanner.next();
			boolean checkCourseName = sc.checkCourseName(couresName);
			if(checkCourseName==true) 
			{
				return couresName;
			}else {
				System.out.println("�γ��������������������");
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
