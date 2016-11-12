package com.se.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.format.CellTextFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.se.dao.StudentDao;
import com.se.pojo.Student;
import com.se.service.StudentService;
import com.se.util.ExcelHelper;

@Service("student_service")
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentDao studentDao;
	
	/**
	 * excel����:
	 * student_id | student_name | class_id
	 * @focus:������ˣ���������д���������ݵ����
	 * @todo:���ӱ����֤,���ܺ�����Ҫ���������������Ϊ��һ����д�����ݡ�
	 */
	public void addStudentList(Workbook wb) {		
			//Read the sheets
			for(int sheetNum=0;sheetNum<wb.getNumberOfSheets();sheetNum++){
				Sheet sheet=wb.getSheetAt(sheetNum);
				if(sheet==null) continue;
				//Read the rows,begin from row one
				for(int rowNum=1;rowNum<sheet.getPhysicalNumberOfRows();rowNum++){
					Row row=sheet.getRow(rowNum);
					if(row!=null){
						Student student=new Student();
						student.setStudent_id(getValue(row.getCell(0)));
						student.setStudent_name(getValue(row.getCell(1)));
						student.setClass_id(getValue(row.getCell(2)));
						studentDao.addStudent(student);
					}
				}
			}
		
	}
	public void readStudentInfo(String filePath,MultipartFile[] files){
		File dirPath=new File(filePath);
		if(!dirPath.exists())
			dirPath.mkdirs();
		if(files.length!=0){
			for(MultipartFile file : files){
				try {
					File tempFile=new File(dirPath,file.getOriginalFilename());
					if(!ExcelHelper.validateExcel(tempFile.getPath())) continue;
					file.transferTo(tempFile);	
					InputStream is=new FileInputStream(tempFile);
					addStudentList(ExcelHelper.getExCelWorkbook(is,tempFile.getPath()));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}				
			}
		}
	}

	@Override
	public List<Student> getCourseStudent(String course_id) {
		return studentDao.getStudentList(course_id); 
		
	}
	@SuppressWarnings("deprecation")
	private String getValue(Cell cell){
		cell.setCellType(CellType.STRING);
		return cell.getStringCellValue();
	}

}
