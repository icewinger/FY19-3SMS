package com.neuedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.entity.Student;
/**
 * �ӿڵ�ʵ��
 * @author Administrator
 *
 */
public class StudentDaoimpl implements StudentDao {
	//Connection�ĳ�Ա����
	private Connection conn;
	public StudentDaoimpl(Connection conn) 
	{
		this.conn = conn;
		
	}
	//���ѧ��
	@Override
	public void addOneStudent(Student student) {
		String sql = "INSERT INTO student VALUES (?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getStuId());
			pstmt.setString(2, student.getStuName());
			pstmt.setInt(3, student.getStuAge());
			pstmt.setString(4, student.getStuSex());
			int executeUpdate = pstmt.executeUpdate();
			if(executeUpdate!=0)
			{
				System.out.println("��ӳɹ�");
			}else
				System.out.println("���ʧ������������");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//�޸�ѧ��
	@Override
	public void upDateOneStudent(Object object,Object object2,String id) {
		String sql = "UPDATE student set " + object + " = ? where stuId = ?";
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setObject(1, object2);
			pre.setString(2, id);
			int executeUpdate = pre.executeUpdate();
			if(executeUpdate!=0)
			{
				System.out.println("��ӳɹ�");
			}else
				System.out.println("���ʧ������������");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//����IDɾ��ѧ����Ϣ
	@Override
	public void delOneStudent(String stuId) {
		String sql = "delete  FROM student where stuId = ?";
		try {
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, "stu11");
			int executeUpdate = prepareStatement.executeUpdate();
			if(executeUpdate!=0)
			{
				System.out.println("ɾ���ɹ�");
			}else
				System.out.println("ɾ��ʧ������������");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Student> getAllStudent() {
		String sql = "SELECT * FROM student";
		List<Student> list =  new ArrayList<>();
		try {
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next())
			{
				String id = rs.getString("stuId");
				String name = rs.getString("stuName");
				int age = rs.getInt("stuAge");
				String sex = rs.getString("stuSex");
				Student student = new Student(id,name,age,sex);
				list.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//����ID��ѯѧ����Ϣ
	@Override
	public Student getStudentInFoBystuId(String stuId) {
		String sql = "SELECT * FROM student WHERE stuId = ?";
		Student student = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, stuId);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String id = rs.getString("stuId");
				String name = rs.getString("stuName");
				int age = rs.getInt("stuAge");
				String sex = rs.getString("stuSex");
				student = new Student(id,name,age,sex);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}
	
	//ͨ��ѧ��ѧ�Ų�ѯ�γ���Ϣ
	@Override
	public List<String> getCourseInfoByStuId(String stuId) {
		List<String> courseName = new ArrayList<>();
		String sql = "SELECT courseName FROM stuAndCourse JOIN course ON cId = courseId WHERE sId = ?";
		try {
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, stuId);
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next())
			{
				String string = rs.getString("courseName");
				courseName.add(string);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseName;
	}

}
