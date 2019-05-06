package com.neuedu.dao;



import java.util.List;

import com.neuedu.entity.Course;
import com.neuedu.entity.Student;
/**
 * 学生接口
 * @author Administrator
 *
 */
public interface StudentDao {
	
	//添加学生
	void addOneStudent(Student student);
	
	//修改学生
	void upDateOneStudent(Object object,Object object2,String id);

	//删除学生
	void delOneStudent(String stuId);
	
	//查询所有学生
	List<Student> getAllStudent();
	
	//根据ID查询学生
	Student getStudentInFoBystuId(String stuId);
	
	//根据学生学号查询课程信息
	List<String> getCourseInfoByStuId(String stuId);
}
