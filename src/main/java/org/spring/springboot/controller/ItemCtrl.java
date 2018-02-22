package org.spring.springboot.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.spring.springboot.domain.Wxuser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.text.SimpleDateFormat;
import java.util.*;

import org.spring.springboot.domain.Item;
import org.spring.springboot.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/")
public class ItemCtrl {
	@Autowired
	private ItemService itemService;

	private Map<String, Object> map = new HashMap<String, Object>();

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 物料查询
	 *
	 * @param partno
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "物料查询", notes = "物料查询")
	@RequestMapping(value = "itemsearch", method = RequestMethod.GET)
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "partno", value = "助记码", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "ftype", value = "客户类型", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "oppen_id", value = "oppen_id", required = true, dataType = "String")
	})
	public Item itemsearch(@RequestParam String partno, @RequestParam String ftype, @RequestParam String oppen_id) {
		try {
			map.put("partno", partno);
			map.put("ftype", ftype);
			map.put("openid", oppen_id);
			String discount = itemService.discount(map);
			Item item = itemService.itemSearch(map);
			item.setDiscount(discount);
			Wxuser wxuser = itemService.query(map);
			String id = UUID.randomUUID().toString();
			map.put("id", id);
			item.setOrder_id(id);
			map.put("fusername", wxuser.getFusername());
			map.put("fmobile", wxuser.getFmobile());
			map.put("fcompanyname", wxuser.getFcompanyname());
			map.put("ftime", sdf.format(new Date()));
			map.put("fname", item.getFname());
			map.put("fhelpcode", partno);
			itemService.history(map);
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 提交订单
	 *
	 * @param orders
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value = "提交订单", notes = "提交订单")
	@RequestMapping(value = "submit", method = RequestMethod.GET)
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "orders", value = "订单", required = true, dataType = "String")
	})
	public int submitorder(@RequestParam String orders) {
		System.out.println(orders);
		try {
			JSONObject order1 = JSONObject.fromObject(orders);
			JSONArray order = order1.getJSONArray("order");
			if (!order.isEmpty()) {
				JSONObject json = new JSONObject();
				for (int i = 0; i < order.size(); i++) {
					json = order.getJSONObject(i);
					String id = json.getString("id");
					String fnumber = json.getString("fnumber");
					String fdiscount = json.getString("fdiscount");
					String ftotal = json.getString("ftotal");
					map.put("id", id);
					map.put("fnumber", fnumber);
					map.put("fdiscount", fdiscount);
					map.put("ftotal", ftotal);
					itemService.submit(map);
				}
				return 1;
			}else {
				return 3;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

}
