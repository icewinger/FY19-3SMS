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
		System.out.println("**********��ѡ��Ҫ��������Ϣ��Ӧ����****************");
		System.out.println("*1.���ѧ��      2.�޸�ѧ��       3.ɾ��ѧ��      4.��ѯ����ѧ��    5.����ID��ѯѧ��     6.�γ̹���     7.ϵͳ�˳�");
		System.out.println("**************************************");
		System.out.println("��ѡ��:");
		int nextInt = scanner.nextInt();
		switch(nextInt)
		{
		case 1: 
			String id = this.case1();
			System.out.println("������ѧ������");
			String name = scanner.next();
			System.out.println("������ѧ������");
			int age = scanner.nextInt();
			System.out.println("������ѧ���Ա�");
			String sex = scanner.next();
			Student student = new Student(id,name,age,sex);
			sd.addOneStudent(student);
			System.out.println("������ϣ��Զ������ϲ�Ŀ¼");
			stuManagerIndex();
			break;
		case 2:
			String id2 = this.case2();
			System.out.println("������Ҫ�޸ĵ���Ŀ����");
			String name2 = scanner.next();
			System.out.println("�������޸ĺ������");
			String str = scanner.next();
			sd.upDateOneStudent(name2, str, id2);
			System.out.println("�޸���ϣ��Զ������ϲ�Ŀ¼");
			stuManagerIndex();
			break;
		case 3:
			String id3 = this.case2();
			sd.delOneStudent(id3);
			System.out.println("ɾ����ϣ��Զ������ϲ�Ŀ¼");
			stuManagerIndex();
			break;
		case 4:
			sd.getAllStudent();
			System.out.println("ɾ����ϣ��Զ������ϲ�Ŀ¼");
			stuManagerIndex();
			break;
		case 5:
			String id4 = this.case2();
			Student studentInFoBystuId = sd.getStudentInFoBystuId(id4);
			System.out.println(studentInFoBystuId);
			System.out.println("��ѯ��ϣ��Զ������ϲ�Ŀ¼");
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
		System.out.println("������ID");
		String id = scanner.next();
		boolean checkId = sc.checkId(id);
		if(checkId==false)
		{
			return id;
		}else {
		System.out.println("��ID�Ѵ��ڣ�����������");
		this.case1();}
		return null;
	}
	public String case2()
	{
		System.out.println("������ID");
		String id = scanner.next();
		boolean checkId = sc.checkId(id);
		if(checkId==true)
		{
			return id;
		}else {
		System.out.println("��ID�����ڣ�����������");
		this.case2();}
		return null;
	}
}
