package org.spring.springboot.service;

import org.spring.springboot.domain.AccessToken;

import java.util.List;
import java.util.Map;

/**
 * 城市业务逻辑接口类
 *
 * Created by bysocket on 07/02/2017.
 */
public interface AccessTokenService {

	
	public int update(Map<String, Object> map);

	
	public int updateMsg(Map<String, Object> map);

	public Map<String,Object> getToken(); 

	public List<AccessToken> listByMsgId(AccessToken accessToken);
	
	public Map<String,Object> getYQbyName(String account_name);
}
