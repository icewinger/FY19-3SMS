package com.neuedu.view;

import java.util.Scanner;

import com.neuedu.dao.StudentDaoimpl;
import com.neuedu.entity.Student;
import com.neuedu.util.DBUtils;

public class ManagerPage {
	
	public ManagerPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	StudentDaoimpl sd = new StudentDaoimpl(DBUtils.getConnection());
	StudentClient sc = new StudentClient();
	CourseManager cm = new CourseManager();
	
	Scanner scanner = new Scanner(System.in);
	public void stuManagerIndex()
	{
		System.out.println("**********请选择要操作的信息对应数字****************");
		System.out.println("*1.添加学生      2.修改学生       3.删除学生      4.查询所有学生    5.根据ID查询学生     6.课程管理     7.系统退出");
		System.out.println("**************************************");
		System.out.println("请选择:");
		int nextInt = scanner.nextInt();
		switch(nextInt)
		{
		case 1: 
			String id = this.case1();
			System.out.println("请输入学生姓名");
			String name = scanner.next();
			System.out.println("请输入学生年龄");
			int age = scanner.nextInt();
			System.out.println("请输入学生性别");
			String sex = scanner.next();
			Student student = new Student(id,name,age,sex);
			sd.addOneStudent(student);
			System.out.println("输入完毕，自动返回上层目录");
			stuManagerIndex();
			break;
		case 2:
			String id2 = this.case2();
			System.out.println("请输入要修改的项目列名");
			String name2 = scanner.next();
			System.out.println("请输入修改后的内容");
			String str = scanner.next();
			sd.upDateOneStudent(name2, str, id2);
			System.out.println("修改完毕，自动返回上层目录");
			stuManagerIndex();
			break;
		case 3:
			String id3 = this.case2();
			sd.delOneStudent(id3);
			System.out.println("删除完毕，自动返回上层目录");
			stuManagerIndex();
			break;
		case 4:
			sd.getAllStudent();
			System.out.println("删除完毕，自动返回上层目录");
			stuManagerIndex();
			break;
		case 5:
			String id4 = this.case2();
			Student studentInFoBystuId = sd.getStudentInFoBystuId(id4);
			System.out.println(studentInFoBystuId);
			System.out.println("查询完毕，自动返回上层目录");
			stuManagerIndex();
			break;
		case 6:
			cm.CourseManagerIndex();
		case 7:
			System.exit(0);
		}
	}
	
	public String case1()
	{
		System.out.println("请输入ID");
		String id = scanner.next();
		boolean checkId = sc.checkId(id);
		if(checkId==false)
		{
			return id;
		}else {
		System.out.println("此ID已存在，请重新输入");
		this.case1();}
		return null;
	}
	public String case2()
	{
		System.out.println("请输入ID");
		String id = scanner.next();
		boolean checkId = sc.checkId(id);
		if(checkId==true)
		{
			return id;
		}else {
		System.out.println("此ID不存在，请重新输入");
		this.case2();}
		return null;
	}
}
