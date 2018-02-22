package org.spring.springboot.dao;

import java.util.Map;

public interface UserDao {
	
	public void insertLog(Map<String, Object> map);

	public void insertLogentry(Map<String,Object> map);

	public void insertDel(Map<String,Object> map);

	public void insertDelentry(Map<String,Object> map);

	public void insertEn(Map<String,Object> map);

	public void insertEntry(Map<String,Object> map);

	public void checkResult(Map<String,Object> map);

	public void response(Map<String,Object> map);

	public void declare(Map<String,Object> map);

	public void check(Map<String,Object> map);
}
