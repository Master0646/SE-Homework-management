package com.se.pojo;


/**
 * @author zzs
 *	��ӦUser_table�� ������������һһ��Ӧ
 * �û�Ȩ��:0->����Ա��1->��ʦ��2->���̣�3->ѧ����
 */
public class User {
	private String user_id;
	private int user_role;
	private String user_name;
	private String user_pwd;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getUser_role() {
		return user_role;
	}
	public void setUser_role(int user_role) {
		this.user_role = user_role;
	}

	
	
}
