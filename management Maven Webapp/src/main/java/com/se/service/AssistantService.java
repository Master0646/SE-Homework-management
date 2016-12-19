package com.se.service;

import java.util.List;

import com.se.pojo.Assistant;
import com.se.pojo.Course;

public interface AssistantService {
	List<Assistant> getTeacherAssistants(String teacher_id);
	Assistant getTeamAssistant(String team_id);
	void setTeamAssistant(String team_id,String assistant_id);
	
	List<String> getAssitantTeams(String assistant_id);
	
	//�����ݶ�Ϊ������Э���Ŀγ̺�����������ʦһ��
	List<Course> getAssistantCourses(String assistant_id);
}
