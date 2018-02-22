package org.spring.springboot.controller;

import net.sf.json.JSONArray;
import org.spring.springboot.domain.Delivery;
import org.spring.springboot.domain.Entry;
import org.spring.springboot.domain.Logistics;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/")
public class UserCtrl {
	@Autowired
	private UserService userService;

	private Map<String, Object> map = new HashMap<String, Object>();
	private Map<String, Object> mapentry = new HashMap<String, Object>();

	/**
	 * 运单申报反馈接口
	 * @param EData
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value="运单反馈",notes="运单反馈")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "EData", value = "EData", required = true, dataType = "String"),
    })
	@RequestMapping(value = "wLogisticsResponse",method= RequestMethod.POST)
	public String Log(String EData) {
		try {
			List<Logistics> list = new ArrayList<>();
			String result = URLDecoder.decode(EData,"utf-8");
			System.out.println(result);
			JSONObject json = JSONObject.fromObject(result);
			String version = json.getString("version");
			String sendercode = json.getString("senderCode");
			String tradeName = json.getString("tradeName");
			String transCode = json.getString("transCode");
			String sendTime = json.getString("sendTime");
			JSONArray jsonarray = json.getJSONArray("wLogisticsResponse");
			map.put("version",version);
			map.put("senderCode",sendercode);
			map.put("tradeName",tradeName);
			map.put("transCode",transCode);
			map.put("sendTime",StringtoDate(sendTime));
			userService.insertLog(map);
			String log_id = map.get("id").toString();
			if(!jsonarray.isEmpty()){
				JSONObject json1 = new JSONObject();
				for(int i=0;i<jsonarray.size();i++){
					Logistics log = new Logistics();
					json1 = jsonarray.getJSONObject(i);
					String logisticCode = json1.getString("logisticCode");
					String villNo = json1.getString("villNo");
					String rspCode = json1.getString("rspCode");
					String rspMsg = json1.getString("rspMsg");
					log.setId(UUID.randomUUID().toString());
					log.setLog_id(log_id);
					log.setLogisticCode(logisticCode);
					log.setRspCode(rspCode);
					log.setRspMsg(rspMsg);
					log.setVillNo(villNo);
					list.add(log);
				}
				mapentry.put("logentrylist",list);
			}
			userService.insetLogentry(mapentry);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}


	/**
	 * 入库明细单申报反馈接口
	 * @param EData
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value="入库明细单反馈",notes="入库明细单反馈")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "EData", value = "EData", required = true, dataType = "String"),
	})
	@RequestMapping(value = "wDeliveryResponse",method= RequestMethod.POST)
	public String del(String EData) {
		try {
			List<Delivery> list = new ArrayList<>();
			String result = URLDecoder.decode(EData,"utf-8");
			JSONObject json = JSONObject.fromObject(result);
			String version= json.getString("version");
			String senderCode= json.getString("senderCode");
			String tradeName= json.getString("tradeName");
			String transCode= json.getString("transCode");
			String sendTime= json.getString("sendTime");
			map.put("version",version);
			map.put("senderCode",senderCode);
			map.put("tradeName",tradeName);
			map.put("transCode",transCode);
			map.put("sendTime",StringtoDate(sendTime));
			userService.insertDel(map);
			String del_id = map.get("id").toString();
			JSONArray jsonarray = json.getJSONArray("wDeliveryResponse");
			System.out.println(jsonarray);
			if(!jsonarray.isEmpty()){
				JSONObject json1 = new JSONObject();
				for(int i=0;i<jsonarray.size();i++){
					Delivery del = new Delivery();
					json1 = jsonarray.getJSONObject(i);
					String deliveryCode = json1.getString("deliveryCode");
					String billNo = json1.getString("billNo");
					String rspCode = json1.getString("rspCode");
					String rspMsg = json1.getString("rspMsg");
					del.setId(UUID.randomUUID().toString());
					del.setDel_id(del_id);
					del.setDeliveryCode(deliveryCode);
					del.setBillNo(billNo);
					del.setRspCode(rspCode);
					del.setRspMsg(rspMsg);
					list.add(del);
				}
				mapentry.put("delentrylist",list);
			}
			userService.insertDelentry(mapentry);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}



	/**
	 * 进境申报反馈接口
	 * @param EData
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value="进境反馈",notes="进境反馈")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "EData", value = "EData", required = true, dataType = "String"),
	})
	@RequestMapping(value = "wEntryResponse",method= RequestMethod.POST)
	public String entry(String EData) {
		try {
			List<Entry> list = new ArrayList<>();
			String result = URLDecoder.decode(EData,"utf-8");
			JSONObject json = JSONObject.fromObject(result);
			String version= json.getString("version");
			String transCode= json.getString("transCode");
			String sendTime= json.getString("sendTime");
			String serverType= json.getString("serverType");
			map.put("version",version);
			map.put("transCode",transCode);
			map.put("sendTime",StringtoDate(sendTime));
			map.put("serverType",serverType);
			userService.insertEn(map);
			String entry_id = map.get("id").toString();
			JSONArray jsonarray = json.getJSONArray("wEntryResponse");
			if(!jsonarray.isEmpty()){
				JSONObject json1 = new JSONObject();
				for(int i=0;i<jsonarray.size();i++){
					Entry entry = new Entry();
					json1 = jsonarray.getJSONObject(i);
					String rspCode = json1.getString("rspCode");
					String rspMsg = json1.getString("rspMsg");
					String billNo = json1.getString("billNo");
					String assBillNo = json1.getString("assBillNo");
					String entryID = json1.getString("entryID");
					String entryCode = json1.getString("entryCode");
					String custCode = json1.getString("custCode");
					entry.setId(UUID.randomUUID().toString());
					entry.setEn_id(entry_id);
					entry.setRspCode(rspCode);
					entry.setRspMsg(rspMsg);
					entry.setBillNo(billNo);
					entry.setAssBillNo(assBillNo);
					entry.setEntryID(entryID);
					entry.setEntryCode(entryCode);
					entry.setCustCode(custCode);
					list.add(entry);
				}
				mapentry.put("entrylist",list);
			}
			userService.insertEntry(mapentry);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}



	/**
	 * 备案审核结果返回接口
	 * @param EData
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value="备案审核结果返回",notes="备案审核结果返回")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "EData", value = "EData", required = true, dataType = "String"),
	})
	@RequestMapping(value = "checkResult",method= RequestMethod.POST)
	public String checkResult(String EData) {
		try {
			String result = URLDecoder.decode(EData,"utf-8");
			JSONObject json = JSONObject.fromObject(result);
			String version= json.getString("version");
			String commitTime= json.getString("commitTime");
			String serialNumber= json.getString("serialNumber");
			String aprrMan= json.getString("aprrMan");
			String orgCode= json.getString("orgCode");
			String apprType= json.getString("apprType");
			String tradeCode= json.getString("tradeCode");
			String brandId= json.getString("brandId");
			String cargoCrossId= json.getString("cargoCrossId");
			String brandCode= json.getString("brandCode");
			String codeId= json.getString("codeId");
			String apprRtn= json.getString("apprRtn");
			String apprAdvice= json.getString("apprAdvice");
			String apprTime= json.getString("apprTime");
			String sp1= json.getString("sp1");
			String sp2= json.getString("sp2");
			String sp3= json.getString("sp3");
			map.put("id",UUID.randomUUID().toString());
			map.put("version",version);
			map.put("commitTime",StringtoDate(commitTime));
			map.put("serialNumber",serialNumber);
			map.put("aprrMan",aprrMan);
			map.put("orgCode",orgCode);
			map.put("apprType",apprType);
			map.put("tradeCode",tradeCode);
			map.put("brandId",brandId);
			map.put("cargoCrossId",cargoCrossId);
			map.put("brandCode",brandCode);
			map.put("codeId",codeId);
			map.put("apprRtn",apprRtn);
			map.put("apprAdvice",apprAdvice);
			map.put("apprTime",StringtoDate(apprTime));
			map.put("sp1",sp1);
			map.put("sp2",sp2);
			map.put("sp3",sp3);
			userService.checkResult(map);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}



	/**
	 * 备案审核结果返回接口通讯应答
	 * @param EData
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value="备案审核结果返回接口通讯应答",notes="备案审核结果返回接口通讯应答")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "EData", value = "EData", required = true, dataType = "String"),
	})
	@RequestMapping(value = "response",method= RequestMethod.POST)
	public String response(String EData) {
		try {
			String result = URLDecoder.decode(EData,"utf-8");
			JSONObject json = JSONObject.fromObject(result);
			String declaraType= json.getString("declaraType");
			String coCode= json.getString("coCode");
			String tradeCode= json.getString("tradeCode");
			String brandId= json.getString("brandId");
			String brandCode= json.getString("brandCode");
			String codeId= json.getString("CodeId");
			String cargoCrossId= json.getString("cargoCrossId");
			String recvTime= json.getString("recvTime");
			String rtnCode= json.getString("rtnCode");
			String rtnDesc= json.getString("rtnDesc");
			String spt1= json.getString("spt1");
			String spt2= json.getString("spt2");
			String spt3= json.getString("spt3");
			map.put("id",UUID.randomUUID().toString());
			map.put("declaraType",declaraType);
			map.put("coCode",coCode);
			map.put("tradeCode",tradeCode);
			map.put("brandId",brandId);
			map.put("brandCode",brandCode);
			map.put("codeId",codeId);
			map.put("cargoCrossId",cargoCrossId);
			map.put("recvTime",StringtoDate(recvTime));
			map.put("rtnCode",rtnCode);
			map.put("rtnDesc",rtnDesc);
			map.put("spt1",spt1);
			map.put("spt2",spt2);
			map.put("spt3",spt3);
			userService.response(map);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}


	/**
	 * 备案申报类接口
	 * @param EData
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value="备案申报类接口",notes="备案申报类接口")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "EData", value = "EData", required = true, dataType = "String"),
	})
	@RequestMapping(value = "declare",method= RequestMethod.POST)
	public String declare(String EData) {
		try {
			String result = URLDecoder.decode(EData,"utf-8");
			JSONObject json = JSONObject.fromObject(result);
			String declaraType= json.getString("declaraType");
			String coCode= json.getString("coCode");
			String tradeCode= json.getString("tradeCode");
			String brandId= json.getString("brandId");
			String brandCode= json.getString("brandCode");
			String codeId= json.getString("codeId");
			String cargoCrossId= json.getString("cargoCrossId");
			String recvTime= json.getString("recvTime");
			String rtnCode= json.getString("rtnCode");
			String rtnDesc= json.getString("rtnDesc");
			String sp1= json.getString("sp1");
			String sp2= json.getString("sp2");
			String sp3= json.getString("sp3");
			map.put("id",UUID.randomUUID().toString());
			map.put("declaraType",declaraType);
			map.put("coCode",coCode);
			map.put("tradeCode",tradeCode);
			map.put("brandId",brandId);
			map.put("brandCode",brandCode);
			map.put("codeId",codeId);
			map.put("cargoCrossId",cargoCrossId);
			map.put("recvTime",StringtoDate(recvTime));
			map.put("rtnCode",rtnCode);
			map.put("rtnDesc",rtnDesc);
			map.put("sp1",sp1);
			map.put("sp2",sp2);
			map.put("sp3",sp3);
			userService.declare(map);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}


	/**
	 * 备案审批信息查询类接口
	 * @param EData
	 * @return
	 */
	@ResponseBody
	@ApiOperation(value="备案审批信息查询类接口",notes="备案审批信息查询类接口")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "EData", value = "EData", required = true, dataType = "String"),
	})
	@RequestMapping(value = "check",method= RequestMethod.POST)
	public String check(String EData) {
		try {
			String result = URLDecoder.decode(EData,"utf-8");
			JSONObject json = JSONObject.fromObject(result);
			String aprrMan= json.getString("aprrMan");
			String orgCode= json.getString("orgCode");
			String apprType= json.getString("apprType");
			String tradeCode= json.getString("tradeCode");
			String brandId= json.getString("brandId");
			String brandCode= json.getString("brandCode");
			String codeId= json.getString("codeId");
			String cargoCrossId= json.getString("cargoCrossId");
			String rtnCode= json.getString("rtnCode");
			String rtnDesc= json.getString("rtnDesc");
			String apprRtn= json.getString("apprRtn");
			String apprAdvice= json.getString("apprAdvice");
			String apprTime= json.getString("apprTime");
			String spt1= json.getString("spt1");
			String spt2= json.getString("spt2");
			String spt3= json.getString("spt3");
			map.put("id",UUID.randomUUID().toString());
			map.put("aprrMan",aprrMan);
			map.put("orgCode",orgCode);
			map.put("apprType",apprType);
			map.put("tradeCode",tradeCode);
			map.put("brandId",brandId);
			map.put("brandCode",brandCode);
			map.put("codeId",codeId);
			map.put("cargoCrossId",cargoCrossId);
			map.put("rtnCode",rtnCode);
			map.put("rtnDesc",rtnDesc);
			map.put("apprRtn",apprRtn);
			map.put("apprAdvice",apprAdvice);
			map.put("apprTime",StringtoDate1(apprTime));
			map.put("spt1",spt1);
			map.put("spt2",spt2);
			map.put("spt3",spt3);
			userService.check(map);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}


	public String GetUtf8(String  str){
		try {
			str = new String(str.getBytes("iso8859-1"),
					"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	public Date StringtoDate(String str){
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Date StringtoDate1(String str){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
