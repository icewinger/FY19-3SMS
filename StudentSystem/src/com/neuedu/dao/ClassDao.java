package com.neuedu.dao;

import com.neuedu.entity.Course;

public interface ClassDao {
	
	//���ӿγ�
	void addOneClass (Course course);
	
	//�޸Ŀγ�
	void updateOneClass(Object object,Object object2);
	
	//ɾ���γ�
	void delOneClass(Object object);
	

	
}	
