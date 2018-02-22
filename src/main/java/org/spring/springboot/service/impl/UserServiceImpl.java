package org.spring.springboot.service.impl;

import org.spring.springboot.dao.UserDao;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	@Override
	public void insertLog(Map<String, Object> map) {
		userDao.insertLog(map);
	}

	@Override
	public void insetLogentry(Map<String, Object> map) {
		userDao.insertLogentry(map);
	}

	@Override
	public void insertDel(Map<String, Object> map) {
		userDao.insertDel(map);
	}

	@Override
	public void insertDelentry(Map<String, Object> map) {
		userDao.insertDelentry(map);
	}

	@Override
	public void insertEn(Map<String, Object> map) {
		userDao.insertEn(map);
	}

	@Override
	public void insertEntry(Map<String, Object> map) {
		userDao.insertEntry(map);
	}

	@Override
	public void checkResult(Map<String, Object> map) {
		userDao.checkResult(map);
	}

	@Override
	public void response(Map<String, Object> map) {
		userDao.response(map);
	}

	@Override
	public void declare(Map<String, Object> map) {
		userDao.declare(map);
	}

	@Override
	public void check(Map<String, Object> map) {
		userDao.check(map);
	}
}
