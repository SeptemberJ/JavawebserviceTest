package org.spring.springboot.service.impl;

import java.util.List;
import java.util.Map;

import org.spring.springboot.dao.ItemDao;
import org.spring.springboot.domain.Item;
import org.spring.springboot.domain.Wxuser;
import org.spring.springboot.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	private ItemDao itemDao;

	@Override
	public Item itemSearch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return itemDao.itemSearch(map);
	}

	@Override
	public String discount(Map<String, Object> map) {
		return itemDao.discount(map);
	}

	@Override
	public void history(Map<String, Object> map) {
		itemDao.history(map);
	}

	@Override
	public Wxuser query(Map<String, Object> map) {
		return itemDao.query(map);
	}

	@Override
	public void submit(Map<String, Object> map) {
		itemDao.submit(map);
	}


}
