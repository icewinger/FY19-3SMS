package com.neuedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.neuedu.entity.Course;

public class ClassDaoimpl implements ClassDao {
	private Connection conn;
	public ClassDaoimpl(Connection conn) {
		this.conn = conn;
	}
	//���ӿγ�
	@Override
	public void addOneClass(Course course) {
		String sql = "INSERT INTO course VALUES (?,?) ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, course.getCourseId());
			ps.setString(2, course.getCourseName());
			int executeUpdate = ps.executeUpdate();
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

	//�޸Ŀγ�
	@Override
	public void updateOneClass(Object object, Object object2) {
		String sql = "update course set courseName = ? WHERE courseId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, object);
			ps.setObject(2, object2);
			int executeUpdate = ps.executeUpdate();
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
	//ɾ���γ�
	@Override
	public void delOneClass(Object object) {
		String sql = "delete from course where courseId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, object);
			int executeUpdate = ps.executeUpdate();
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

}
