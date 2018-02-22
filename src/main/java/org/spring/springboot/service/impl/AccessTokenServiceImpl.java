package org.spring.springboot.service.impl;

import org.spring.springboot.dao.AccessTokenDao;
import org.spring.springboot.domain.AccessToken;
import org.spring.springboot.service.AccessTokenService;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenServiceImpl implements  AccessTokenService{
	@Autowired
	public  AccessTokenDao accessTokenDao ;
	
	
	public int update(Map<String, Object> map) {
		return accessTokenDao.update(map);
	}
	
	public int updateMsg(Map<String, Object> map) {
		return accessTokenDao.updateMsg(map);
	}

	public Map<String,Object> getToken(){
		return accessTokenDao.getToken();
	}
	
	public List<AccessToken> listByMsgId(AccessToken accessToken) {
		return accessTokenDao.listByMsgId(accessToken);
	}
	
	public Map<String,Object> getYQbyName(String account_name)
	{
		return accessTokenDao.getYQbyName(account_name);
	}
}
