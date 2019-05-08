package com.neuedu.view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.util.DBUtils;

public class StudentClient {

	public boolean checkId(String id) {
		String sql = "SELECT stuId FROM student";
		PreparedStatement prepareStatement;
		try {
			prepareStatement = DBUtils.getConnection().prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			String str;
			while (rs.next()) {
				str = rs.getString("stuId");
				if (id.equals(str)) {
					return true;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkCourseId(Integer id) {
		String sql = "SELECT courseId FROM course";

		try {
			PreparedStatement prepareStatement = DBUtils.getConnection().prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				Integer str ; 
				str = rs.getInt("courseId");
				if (str.equals(id)) {
					return false;
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
	
	public boolean checkCourseIdForStudentCourse(String str,Integer inte)
	{
		String sql = "SELECT cId FROM stuandcourse WHERE sId = ?";
		try {
			PreparedStatement prepareStatement = DBUtils.getConnection().prepareStatement(sql);
			prepareStatement.setString(1, str);
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next())
			{
				Integer in = rs.getInt("cId");
				if(in.equals(inte))
				{
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean checkCourseName(String str)
	{
		String sql = "SELECT courseName FROM course";
		try {
			PreparedStatement prepareStatement = DBUtils.getConnection().prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next())
			{
				String name = rs.getString("courseName");
				if(name.equals(str))
				{
					return true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
