package com.se.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.se.pojo.User;
import com.se.service.UserService;

@Controller
public class LoginController {
			
	@Resource
	private UserService userService;
	
	/**
	 * �û���¼����֤������Ҫ���밲ȫ��飺����
	 * @param user_id
	 * @param password
	 * �û�Ȩ��:0->����Ա��1->��ʦ��2->���̣�3->ѧ����
	 */
	@RequestMapping(value="login")
	public ModelAndView login(@RequestParam String user_id,@RequestParam String password,HttpSession httpSession){
		String message;
		User user=userService.checkUser(user_id, password);
		System.out.println(user.getUser_role());
		ModelAndView mv=null;
		if(user!=null){
			message="�û���֤�ɹ�";
			httpSession.setAttribute("user_id", user_id);
			if(user.getUser_role()==0){
				mv=new ModelAndView("/manager/manager_index","user",user);
			}
			else if(user.getUser_role()==1){
				mv=new ModelAndView("/teacher/teacher_index","teacher_id",user_id);
			}
			else if(user.getUser_role()==2){
				mv=new ModelAndView("/assistant/assistant_index","user",user);
			}
			else if(user.getUser_role()==3){
				mv=new ModelAndView("/student/student_index","user",user);
			}
		}
		else{
			message="�û���֤ʧ��";
			mv = new ModelAndView("/login");
		}
		return mv;
	}
	
	
	@RequestMapping("/logout")
	public ModelAndView logout(){
		return new ModelAndView("/login");
	}
}
