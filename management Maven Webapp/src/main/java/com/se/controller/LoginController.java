package com.se.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.se.pojo.Course;
import com.se.pojo.TeamHomework;
import com.se.pojo.User;
import com.se.service.UserService;
import com.se.service.impl.AssistantServiceImpl;
import com.se.service.impl.CourseServiceImpl;
import com.se.service.impl.HomeworkServiceImpl;

@Controller
public class LoginController {
			
	@Resource
	private UserService userService;
	@Resource
	private HomeworkServiceImpl homeworkService;
	@Resource
	private AssistantServiceImpl assistantService;
	@Resource
	private CourseServiceImpl courseService;
	
	/**
	 * �û���¼����֤������Ҫ���밲ȫ��飺����
	 * @param user_id
	 * @param password
	 * �û�Ȩ��:0->����Ա��1->��ʦ��2->���̣�3->ѧ����
	 */
	@RequestMapping(value="login")
	public ModelAndView login(@RequestParam String user_id,@RequestParam String password,HttpSession httpSession){
	
		User user=userService.checkUser(user_id, password);
		if(user==null) return new ModelAndView("/login");
		ModelAndView mv=null;
		httpSession.setAttribute("user_id", user_id);
		if(user.getUser_role()==0){
			mv=new ModelAndView("/manager/manager_index","user",user);
		}
		else if(user.getUser_role()==1){
			mv=new ModelAndView("/teacher/teacher_index","teacher_id",user_id);
		}
		else if(user.getUser_role()==2){
			int num=0;
			for(String team_id : assistantService.getAssitantTeams(user_id)){
				for(TeamHomework homework:homeworkService.getTeamHomeworks(team_id)){
					if(homework.getStatus()==1){//���С����δ���ĵ���ҵ����δ������ҵ��С��+1
						num++;
						break;
					}
				}
			}
			Map<String, Object> data=new HashMap<String, Object>();
			data.put("user", user);
			data.put("num", num);
			mv=new ModelAndView("/assistant/assistant_index","data",data);
		}
		else if(user.getUser_role()==3){
				Map<String, Object> data=new HashMap<String, Object>();
				List<Course> courses=new ArrayList<Course>();
				for(String course_id:courseService.getStudentCourses(user_id)){
					courses.add(courseService.getCourse(course_id));
				}
				data.put("user", user);
				data.put("courses", courses);
				mv=new ModelAndView("/student/student_index","data",data);
		}
		return mv;
	}
	
	
	@RequestMapping("/logout")
	public ModelAndView logout(){
		return new ModelAndView("/login");
	}
}
