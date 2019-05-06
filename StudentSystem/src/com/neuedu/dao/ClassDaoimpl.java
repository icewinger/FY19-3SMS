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
	//增加课程
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
				System.out.println("添加成功");
			}else
				System.out.println("添加失败请重新输入");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//修改课程
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
				System.out.println("添加成功");
			}else
				System.out.println("添加失败请重新输入");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//删除课程
	@Override
	public void delOneClass(Object object) {
		String sql = "delete from course where courseId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, object);
			int executeUpdate = ps.executeUpdate();
			if(executeUpdate!=0)
			{
				System.out.println("删除成功");
			}else
				System.out.println("删除失败请重新输入");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
