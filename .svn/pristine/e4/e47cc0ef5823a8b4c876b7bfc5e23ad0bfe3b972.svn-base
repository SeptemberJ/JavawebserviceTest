package org.spring.springboot.util;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class WxUtil extends StringUtil{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// @Autowired
//	private static UserInfoService userInfoService = new UserInfoService();
	private static Logger log = Logger.getLogger(WxUtil.class);
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) {
		try {
			// 将解析结果存储在HashMap中
			Map<String, String> map = new HashMap<String, String>();

			// 从request中取得输入流
			InputStream inputStream = request.getInputStream();
			// 读取输入流
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 得到根元素的所有子节点
			List<Element> elementList = root.elements();

			// 遍历所有子节点
			for (Element e : elementList)
				map.put(e.getName(), e.getText());

			// 释放资源
			inputStream.close();
			inputStream = null;
			return map;
		} catch (Exception e) {
			return null;
		}
	}

	public static Map<String, Object> oppen_id(HttpServletRequest request, HttpSession session) {
		String oppen_id = "";
		String code="";
		String access_token="";
		if (session.getAttribute("oppen_id") == null) {

			code = (String) request.getParameter("code");// 获取code值
			System.out.println("------------------------------------");
			System.out.println("code=" + code);
			System.out.println("------------------------------------");
			if (code != null) {
			
				String token_url = "https://api.weixin.qq.com/sns/jscode2session?appid="
						+ StringUtil.appid
						+ "&secret="
						+ StringUtil.appsecret
						+ "&js_code="
						+ code
						+ "&grant_type=authorization_code";
				System.out.println(token_url+"token_urltoken_url");
				// 获取用户的openid
				JSONObject json = new JSONObject();
			   CommonUtil commonUtil=new CommonUtil();
				json = CommonUtil.httpsRequest(token_url, "GET", null);
				if (json != null) {
					oppen_id = json.getString("openid");
					//access_token=json.getString("access_token");
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					System.out.println(json);
					 //session.put("oppen_id", oppen_id);
				}
			}
		} else {
			oppen_id = (String) session.getAttribute("oppen_id");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("code",code);
		map.put("oppen_id",oppen_id);
		map.put("access_token",access_token);
	//	session.setAttribute("code",code);
		return map;
	}

	public static Map<String, Object> oppenIdInfo(HttpServletRequest request,
			HttpSession session) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (session.getAttribute("oppen_id") == null) {
				
				Map<String, Object> map2  = WxUtil.oppen_id(request, session);
			//	String code  = (String) map2.get("code");
				String oppen_id = (String) map2.get("oppen_id");
				String access_token  = (String) map2.get("access_token");
				//AdvancedUtil advancedUtil = new AdvancedUtil();
				WeixinUserInfo wxi = new WeixinUserInfo();
	//			java.util.Date now = new java.util.Date();

		//		String c_now = now.toLocaleString();
//				String accessToken1 = CommonUtil.getToken2(appid,
//						appsecret,code).getAccessToken();
				log.info("--------------------");
				log.info("oppen_id====" + oppen_id+"......accessToken1====" + access_token);
				//wxi = advancedUtil.getUserInfo(access_token, oppen_id);
				// username = URLEncoder.encode(wxi.getNickname(), "utf-8");
				String realname = wxi.getNickname();
				String head_img_url = wxi.getHeadImgUrl();
				log.info("realname==" + realname+"....head_img_url=" + head_img_url);
				session.setAttribute("realname", realname);
				session.setAttribute("head_img", head_img_url);
				session.setAttribute("oppen_id", oppen_id);
				session.setAttribute("appid", appid);
				session.setAttribute("secret", appsecret);
				
				map = map2;
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
