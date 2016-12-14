package com.se.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.se.service.impl.HomeworkServiceImpl;

@Controller
public class FileController {

	@Resource
	HomeworkServiceImpl homeworkService;
	
	@GetMapping("file/download")
	@ResponseBody
	public void fileDownload(String team_id,String homework_id,String file_name) throws UnsupportedEncodingException{
		HttpServletResponse response=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		HttpServletRequest request =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String path= request.getSession().getServletContext().getRealPath("/WEB-INF/upload/studentHomework/");
		path=path+"\\"+homeworkService.getHomework(homework_id).getCourse_id()+"\\"+homework_id+"\\"+team_id+"\\";
		String file_name1=file_name;
		file_name=new String(file_name.getBytes("iso8859-1"),"utf-8");	//������ı�������

        InputStream inputStream = null;  
        OutputStream outputStream = null;
        File file=new File(path,file_name);
        try{
        	inputStream = new FileInputStream(file);  
        	outputStream = new BufferedOutputStream(response.getOutputStream());   
          
            byte[] buffer = new byte[inputStream.available()];  
            inputStream.read(buffer);
            inputStream.close();
            
            response.reset();
         // ����response��Header  
        	//1.�����ļ�ContentType���ͣ��������ã����Զ��ж������ļ�����  
            response.setContentType("multipart/form-data");  
            response.addHeader("Content-Disposition","attachment;filename=" + file_name1);  
            response.addHeader("Content-Length", "" + file.length());  
            
            outputStream.write(buffer);
            outputStream.close();
            outputStream.flush();
        	
        }catch(Exception e){
        	e.printStackTrace();
        }
		
		
	}
}
