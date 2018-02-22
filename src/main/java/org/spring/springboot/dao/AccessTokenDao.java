package org.spring.springboot.dao;


import java.util.List;
import java.util.Map;

import org.spring.springboot.domain.AccessToken;




public interface AccessTokenDao {
	
	public int update(Map<String,Object> map);
	
	public int updateMsg(Map<String,Object> map);
	
	public Map<String,Object> getToken(); 
	
	public List<AccessToken> listByMsgId(AccessToken accessToken);
	
	public Map<String,Object> getYQbyName(String account_name);
	
}
