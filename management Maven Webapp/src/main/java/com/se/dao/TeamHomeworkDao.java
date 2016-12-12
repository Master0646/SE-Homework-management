package com.se.dao;

import java.util.List;

import org.apache.commons.collections4.Get;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.stereotype.Repository;

import com.se.pojo.Homework;
import com.se.pojo.HomeworkFile;
import com.se.pojo.TeamHomework;

@Repository("teamHomeworkDao")
public interface TeamHomeworkDao {
	
	//С����ҵ����
	@Select("select * from team_homework where team_id=#{team_id}")
	List<TeamHomework> getTeamHomeWorks(String team_id);
	
	@Select("select * from team_homework order by submit_time desc")
	List<TeamHomework> getTeamHomeworkViewData();
	
	@Select("select * from team_homework where homework_id=#{homework_id} and team_id=#{team_id}")
	TeamHomework getHomeworkByID(@Param("homework_id")String homework_id,@Param("team_id")String team_id);
	
	//teamHomeworkView
	@Select("select * from team_homework,homework_table where "
			+ "homework_name like concat(concat('%',#{homework_name}),'%')  submit_time=to_date(#{submit_time},'yyyy-mm-dd') and team_homework.homework_id=homework_table.homework_id")
	List<TeamHomework> getTeamHomeworkViewDataByNameTime(@Param("homework_name")String homework_name,@Param("submit_time")String submit_time); 
	
	
	@Select("select * from team_homework,homework_table where homework_name like concat(concat('%',#{homework_name}),'%') and team_homework.homework_id=homework_table.homework_id")
	List<TeamHomework> getTeamHomeworkViewDataByName(@Param("homework_name")String homework_name); 

	@Select("select * from team_homework,homework_table where homework_name like concat(concat('%',#{homework_name}),'%') and team_id=#{team_id}")
	List<TeamHomework> getTeamHomeworkViewDataByNameTeamID(@Param("homework_name") String homework_name, @Param("team_id") String team_id);
	
	@Select("select * from team_homework,homework_table where "
			+ "homework_name like concat(concat('%',#{homework_name}),'%') and team_id=#{team_id} and submit_time=to_date(#{submit_time},'yyyy-mm-dd')")
	List<TeamHomework> getTeamHomeworkViewDataByNameTeamIDTime(@Param("homework_name") String homework_name, @Param("team_id") String team, @Param("submit_time") String submit_time);
	
	//
	@Select("select homework_name from homework_table where homework_id=#{homework_id}")
	String getHomeworkName(String homework_id);
	
	@Update("update team_homework set submit_time=to_date(#{submit_time},'yyyy-mm-dd'),submitter=#{submitter}"
			+ ",status=#{status},grade=#{grade},correctInfo=#{correctInfo},student_comment=#{student_comment} "
			+ " where homework_id=#{homework_id} and team_id=#{team_id}")
	void updateTeamHomework(TeamHomework teamHomework);
	
	@Insert("insert into team_homework(team_id,homework_id,submit_time,submitter,status,grade,correctInfo,student_comment) "
			+ " values(#{team_id},#{homework_id},to_date(#{submit_time},'yyyy-mm-dd'),#{submitter},#{status},#{grade},#{correctInfo},#{student_comment})")
	void addHomework(TeamHomework teamHomework);
	
	@Update("update team_homework set student_comment=#{comment} where homework_id=#{homework_id} and team_id=#{team_id}")
	void setTeamHomeworkComment(@Param("homework_id")String homework_id,@Param("team_id")String team_id,@Param("comment")String comment);
	
	//��ҵ�ļ��������ݿ����
	@Insert("insert into homework_file(file_name,file_path,homework_id,team_id) values(#{file_name},#{file_path},#{homework_id},#{team_id})")
	void addHomeworkFile(HomeworkFile file );
	
	@Select("select * from homework_file where homework_id=#{homework_id} and TEAM_ID=#{team_id}")
	List<HomeworkFile> getTeamHomeworkFiles(@Param("homework_id")String homework_id,@Param("team_id")String team_id);
	
	@Select("select count(*) from homework_file where file_name=#{file_name} and file_path=#{file_path}")
	int isFileExist(@Param("file_name")String file_name,@Param("file_path")String file_path);
	
}
