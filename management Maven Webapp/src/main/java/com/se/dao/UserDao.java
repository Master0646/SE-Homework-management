package com.se.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.se.pojo.User;

@Repository("userDao")
public interface UserDao {
	@Select("select * from user_table where user_name=#{username} and user_pwd= #{password}")
	User getUser(@Param("username")  String username,@Param("password") String password);
}