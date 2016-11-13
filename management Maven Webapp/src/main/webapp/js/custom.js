	

/**
 * 老师界面：向服务器提交课程信息，并给界面的隐藏label添加生成的course_id
 */
function addCourseInfo(teacher_id){
	var course_name=$("#course_name").val();
	var description=$("#description").val();
	$.ajax({
		type : "post",
		url :　"teacher/addCourse",
		data :{
			"teacher_id" : teacher_id,
			"course_name" : course_name,
			"description" : description
		},
		dataType :"json",
		success : function(data){
			$("#course_id").val(data);
		}
	});

}
/**
 * 老师界面：向服务器添加小组配置
 */
function addTeamConfig(){
	var course_id=$("#course_id").val();
	var team_max=$("#t_max").val();
	var team_min=$("#t_min").val();
	var year=$("#t_year").val();
	var class_id=$("#t_class").val();
	$.ajax({
		type : "post",
		url :　"teacher/addTeamConfig",
		data :{
			"course_id" : course_id,
			"team_max" : team_max,
			"team_min" : team_min,
			"year" : year,
			"class_id" : class_id
		},
		dataType :"json",
		success : function(data){
			alert("配置完成");
		}
	});	
}







				  	