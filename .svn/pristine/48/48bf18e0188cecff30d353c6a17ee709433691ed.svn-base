package org.spring.springboot.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.spring.springboot.service.impl.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

import java.awt.*;   
import java.awt.image.*;   
import java.io.*;
import java.net.URL;   


@Controller
@RequestMapping("/")
public class PartyCtrl{

	@Autowired
	private PartyService partyService;
	@Autowired
	Map<String, Object> map = new HashMap<String, Object>();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");	
	SimpleDateFormat Order = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
    Calendar c = Calendar.getInstance();
	

    
    /**党组织转接*/
    @ResponseBody
	@RequestMapping(value = "/updateparty.do",method= RequestMethod.POST)
    //@ApiImplicitParam(paramType="query", name = "fname", value = "fname", dataType = "String")
   
	public Object updateparty(@RequestBody String putInStorage, String fname,String fscard,String gzdw,String zrdzb,String zcwhy) {
    	List list = new ArrayList();
    	String massage = null;
    	Integer type = 0;
    	String partybranch = null;
    	String changeparty = null;
    	String yparty = null;
    	String report = null;
    	String ret_fileName = null;
    	String fileName = "";
    	String maxid = "";
    	String a = "";
    	
    	//查找最大id
    	List<Map<String,Object>> maxidlist =  partyService.selectpartyid();
    	if(maxidlist.size() != 0){
    		for(int i = 0;i < maxidlist.size();i++){
        		maxid = maxidlist.get(i).get("id").toString();
        	}
    	}
    	
    	
    	JSONObject json = JSONObject.fromObject(putInStorage);
    	JSONObject json1=json.getJSONObject("Info");
    	System.out.println(json1);
    	fname = json1.get("name").toString(); 
    	fscard = json1.get("id_card").toString(); 
    	gzdw = json1.get("work").toString(); 
    	zrdzb = json1.get("transferMaster").toString(); 
    	zcwhy = json1.get("transferReason").toString(); 
    	

    	try{
    	report = json1.get("introduce_letter").toString(); 

    	fileName = json1.get("FileName").toString(); 
    	a = fileName.substring(fileName.indexOf(".")-1, fileName.length());
    	fileName = GetUtf8(fileName);
    	System.out.println(report);

    	System.out.println(fileName);
 
    	
//保存图片上服务器  base64解码

    	String path = "C://apache-tomcat-7.0.47/webapps/upload/";
    	//String path = "F://";
		System.out.println("path="+path);
	        File targetFile = new File(path);  
	      
	        if(!targetFile.exists()){  
	        
	        	targetFile.mkdirs();    
				
	        }  

	           // 用于设置图片过大，存入临时文件    
	           DiskFileItemFactory factory = new DiskFileItemFactory();    
	           factory.setSizeThreshold(20 * 1024 * 1024); // 设定使用内存超过5M时，将产生临时文件并存储于临时目录中。    
	           factory.setRepository(new File(path)); // 设定存储临时文件的目录。    
	   
	           ServletFileUpload upload = new ServletFileUpload(factory);    
	           upload.setHeaderEncoding("UTF-8");   
	           String fda1 = report.substring(report.indexOf("data"), report.lastIndexOf(",")+1);	
	           System.out.println(fda1);
	           report = report.replaceAll(fda1, "");      
	               BASE64Decoder decoder = new BASE64Decoder();      
	               try {      
	                   // Base64解码      
	                   byte[] b = decoder.decodeBuffer(report);      
	                   for (int i = 0; i < b.length; ++i) {      
	                       if (b[i] < 0) {// 调整异常数据      
	                           b[i] += 256;      
	                       }      
	                   }      
	                   // 生成jpeg图片      
	                   //ret_fileName = new String(fileName).getBytes("gb2312" ) ;  
	                   fileName = String.valueOf(maxid) + new Date().getTime()+a;
	                   ret_fileName = fileName;    
	                   File file1 = new File(path + "/" + ret_fileName);    
	                   OutputStream out = new FileOutputStream(file1);      
	                   out.write(b);      
	                   out.flush();      
	                   out.close();      
	               } catch (Exception e) {      
	                   e.printStackTrace();      
	               }
    	}catch(Exception e){
    		System.out.println("没有上传图片-------");
    	}
  
	               String yparty1 = null;
	               map.put("fscard", fscard);    
	    List<Map<String,Object>> selectparty =   partyService.selectparty(map);  
	    if(selectparty.size() != 0){
	    	for(int partyi = 0;partyi < selectparty.size() ;partyi ++){
	    		yparty1 = selectparty.get(partyi).get("partybranch").toString();
	    	}
	    	System.out.println(yparty1+"-----");
    	
	    	if(yparty1.equals(zrdzb)){
	    		
	    		
	        	
	        	type = 0;
	    		massage = "转入党支部跟原党支部一样！";
	    		System.out.println("转入党支部跟原党支部一样-----");
	    	}else if(yparty1 != zrdzb){
	    		System.out.println("00000000");
	    		System.out.println(ret_fileName+"----");
	    		map.put("fname", fname);
	        	map.put("fscard", fscard);
	        	map.put("unit", gzdw);
	        	map.put("changeparty", zrdzb);
	        	map.put("zcwhy", zcwhy);
	        	map.put("fimage1", ret_fileName);
	        	type =  partyService.updateparty(map);
	    	}
    	
	    	/*map.put("fscard", fscard);
	    	List<Map<String,Object>> partylist =  partyService.selectparty(map);
	    	for(int i = 0;i < partylist.size();i++){
	    		partybranch = partylist.get(i).get("partybranch").toString();
	    		changeparty = partylist.get(i).get("changeparty").toString();
	    	
	    	}*/
	    }else{
	    	type = 0;
	    	massage = "还不是党员！";
	    }
    	System.out.println("---------------");
    	list.add(type);
    	list.add(massage);
    	return list;
    }
    
    
    
    /**入党申请
     * @throws ParseException */
    @ResponseBody
	@RequestMapping(value = "/applyparty.do",method= RequestMethod.POST)        //
   // @ApiImplicitParam(paramType="query", name = "putInStorage", value = "putInStorage", required=false, dataType = "String")
	public Object applyparty(@RequestBody String putInStorage) throws ParseException {
    	Integer type = 0;
    	String fname = null;
    	String unit = null;
    	String feedback = null;
    	String flow = null;
    	String subreports = null;
    	String subreports1 = null;
    	String fsex = null;
    	String fmz = null;
    	String fscard = null;
    	String fmobile = null;
    	String zw = null;
    	String fjg = null;
    	String csdate = null;
    	String xl = null;
    	Date cjgzdate = null;
    	String cjgzdate1 = null;
    	String fxx = null;
    	String faddress = null;
    	String id = null;
    	String rxdate = null;
    	String bydate = null;
    	String introducer1 = null;
    	String introducer2 = null;
    	String rdjsr = null;
    	String sxhb = null;   //思想汇报
    	String partybranch = null;   //党组织
    	String personal_img  = null;   //个人免冠相册
    	String ret_fileName  = null;   //个人免冠名
    	String maxid = null;
    	String fileName = null;
    	
    	
    	String sxhbimage  = null;   //个人免冠相册
    	String sxhbname  = null;   //个人免冠名
    	String ret_fileName1 = null;
    	String a = null;
    	String b1 = null;
    	
    	//查找最大id
    	List<Map<String,Object>> maxidlist =  partyService.selectapplyid();
    	if(maxidlist.size() != 0){
    		for(int i = 0;i < maxidlist.size();i++){
        		maxid = maxidlist.get(i).get("id").toString();
        	}
    	}
    	
    System.out.println(putInStorage);
    	JSONObject json = JSONObject.fromObject(putInStorage);
    	
    	JSONObject json1=json.getJSONObject("Info");
    	System.out.println(json1);
    	faddress = json1.get("address").toString(); 
    	
    	partybranch = json1.get("partybranch").toString(); 
  
    	xl = json1.get("education").toString(); 
    	fscard = json1.get("id_card").toString(); 
    	fname = json1.get("name").toString(); 
    	fmz = json1.get("nation").toString(); 
    	fsex = json1.get("sex").toString(); 
    	fjg = json1.get("origin").toString(); 
    	fileName = json1.get("FileName").toString();
    	
    	cjgzdate1 = json1.get("workTime").toString();

    	cjgzdate1 = cjgzdate1.replace("Z", " UTC");
    	
    	
    	System.out.println(cjgzdate1);
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
    	Date d = format.parse(cjgzdate1);
    	System.out.println(d);
    	
    	String c =  Order.format(d);
    	System.out.println(c);
    	fxx = json1.get("school").toString(); 
    	rxdate = json1.get("entryTime").toString(); 
    	

    	rxdate = rxdate.replace("Z", " UTC");
    	System.out.println(rxdate);
    	SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
    	Date rxdate2 = format2.parse(rxdate);
    	System.out.println(rxdate2);
    	
    	String c2 =  Order.format(rxdate2);
    	System.out.println(c2);
    	
    	bydate = json1.get("graduationTime").toString(); 
    	sxhbname =json1.get("FileName_report").toString();   //上传思想汇报名字
    	bydate = bydate.replace("Z", " UTC");
    	System.out.println(bydate);
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
    	Date bydate1 = format1.parse(bydate);
    	System.out.println(bydate1);
    	
    	String c1 =  Order.format(bydate1);
    	System.out.println(c1);
    	introducer1 = json1.get("introducer1").toString(); 
    	introducer2 = json1.get("introducer2").toString(); 
    	
    	rdjsr = introducer1 +"," + introducer2;
    	
    	
    	//图片
    	try{
    		a = fileName.substring(fileName.indexOf(".")-1, fileName.length());
    	personal_img = json1.get("personal_img").toString(); 
    	
    //	if(personal_img != ""  || personal_img.equals("")){
    	String path = "C://apache-tomcat-7.0.47/webapps/upload/";
    	//String path = "F://";
		System.out.println("path="+path);
	        File targetFile = new File(path);  
	      
	        if(!targetFile.exists()){  
	        
	        	targetFile.mkdirs();    
				
	        }  

	           // 用于设置图片过大，存入临时文件    
	           DiskFileItemFactory factory = new DiskFileItemFactory();    
	           factory.setSizeThreshold(20 * 1024 * 1024); // 设定使用内存超过5M时，将产生临时文件并存储于临时目录中。    
	           factory.setRepository(new File(path)); // 设定存储临时文件的目录。    
	   
	           ServletFileUpload upload = new ServletFileUpload(factory);    
	           upload.setHeaderEncoding("UTF-8");   
	           String fda1 = personal_img.substring(personal_img.indexOf("data"), personal_img.lastIndexOf(",")+1);	
	           System.out.println(fda1);
	           personal_img = personal_img.replaceAll(fda1, "");      
	               BASE64Decoder decoder = new BASE64Decoder();      
	               try {      
	                   // Base64解码      
	                   byte[] b = decoder.decodeBuffer(personal_img);      
	                   for (int i = 0; i < b.length; ++i) {      
	                       if (b[i] < 0) {// 调整异常数据      
	                           b[i] += 256;      
	                       }      
	                   }      
	                   // 生成jpeg图片      
	                   //ret_fileName = new String(fileName).getBytes("gb2312" ) ;  
	                   fileName = String.valueOf(maxid) + new Date().getTime()+a;
	                  
	                   //ret_fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1" ) ;     
	                   ret_fileName = fileName; 
	                   //File file = new File( ret_fileName);       
	                   File file1 = new File(path + "/" + ret_fileName);    
	                   OutputStream out = new FileOutputStream(file1);      
	                   out.write(b);      
	                   out.flush();      
	                   out.close();      
	               } catch (Exception e) {      
	                   e.printStackTrace();      
	               } 
    	}catch(Exception e){
    		System.out.println("没有上传免冠相册");
    	}
	               
	
    	map.put("fname", fname);
    	map.put("fcheck", 0);
    	map.put("fstatus", 0);
    	map.put("fsex", fsex);
    	map.put("fmz",fmz );
    	map.put("fscard",fscard );
    	map.put("xl", xl);
    	map.put("faddress", faddress);
    	map.put("fxx", fxx);
    	map.put("fjg", fjg);
    	map.put("cjgzdate", c);
    	map.put("rxdate", c2);
    	map.put("bydate", c1);
    	map.put("rdjsr", rdjsr);
    	map.put("fimage", ret_fileName);
    	map.put("partybranch", partybranch);
    	type= partyService.insertapply(map);
    	
    	
    	try{
    		
        	b1 = sxhbname.substring(sxhbname.indexOf(".")-1, sxhbname.length());
            //思想汇报
            sxhbimage = json1.get("report_img").toString();    //上传思想汇报图片
        	String path1 = "C://apache-tomcat-7.0.47/webapps/upload/";
    		System.out.println("path="+path1);
    	        File targetFile1 = new File(path1);  
    	      
    	        if(!targetFile1.exists()){  
    	        
    	        	targetFile1.mkdirs();    
    				
    	        }  

    	           // 用于设置图片过大，存入临时文件    
    	           DiskFileItemFactory factory1 = new DiskFileItemFactory();    
    	           factory1.setSizeThreshold(20 * 1024 * 1024); // 设定使用内存超过5M时，将产生临时文件并存储于临时目录中。    
    	           factory1.setRepository(new File(path1)); // 设定存储临时文件的目录。    
    	   
    	           ServletFileUpload upload1 = new ServletFileUpload(factory1);    
    	           upload1.setHeaderEncoding("UTF-8");   
    	           String fda1image = sxhbimage.substring(sxhbimage.indexOf("data"), sxhbimage.lastIndexOf(",")+1);	
    	           System.out.println(fda1image);
    	        sxhbimage = sxhbimage.replaceAll(fda1image, "");      
    	               BASE64Decoder decoder1 = new BASE64Decoder();      
    	               try {      
    	                   // Base64解码      
    	                   byte[] b = decoder1.decodeBuffer(sxhbimage);      
    	                   for (int i = 0; i < b.length; ++i) {      
    	                       if (b[i] < 0) {// 调整异常数据      
    	                           b[i] += 256;      
    	                       }      
    	                   }      
    	                   // 生成jpeg图片      
    	                   //ret_fileName = new String(fileName).getBytes("gb2312" ) ;  
    	                sxhbname = String.valueOf(maxid) + new Date().getTime()+b1;
    	                   //ret_fileName1 = new String(sxhbname.getBytes("gb2312"), "ISO8859-1" ) ;     
    	                   ret_fileName1 = sxhbname ;     
    	                   //File file = new File( ret_fileName);       
    	                   File file2 = new File(path1 + "/" + ret_fileName1);    
    	                   OutputStream out1 = new FileOutputStream(file2);      
    	                   out1.write(b);      
    	                   out1.flush();      
    	                   out1.close();      
    	               } catch (Exception e) {      
    	                   e.printStackTrace();      
    	               } 
	
    	
    	map.put("fscard", fscard);
    	List<Map<String,Object>> partylist1 =  partyService.selectapply(map);
    	
    	if(partylist1.size() != 0){
    		
    		for(int i = 0;i < partylist1.size();i++){
        		id = partylist1.get(i).get("id").toString();
        	}
    		map.put("sxid", id);
        	map.put("sxdate", new Date());
        	map.put("sxhb", ret_fileName1);
        	partyService.insertsxhb(map);

    	}
    	
    	}catch(Exception e){
    		System.out.println("没有上传思想汇报");
    	}
      	
    	return type;
    }
    
    
    
    /**更新党员信息*/
    @ResponseBody
	@RequestMapping(value = "/updatesubreports1.do",method= RequestMethod.GET)
	public Object updatesubreports1(String subreports1,String fscard) {
    	
    	
    	map.put("subreports1", subreports1);
    	map.put("fscard", fscard);
    	int type = partyService.updatesubreports1(map);
    	return type;
    }
    
    
    /**查看19大思维导图,机关建党,非公党链接*/
    @ResponseBody
	@RequestMapping(value = "/selectdt.do",method= RequestMethod.GET)
	public Object selectdt() {
    	
    	String fname = null;
    	String psdate = null;
    	String fgd = null;
    	String jgdj = null;
    	String lj = null;
    	String zt = null;
    	String dflz = null;
    	List<Map<String,Object>> vidolist =  partyService.selectdt();
    	for(int i = 0; i< vidolist.size();i++){
    	try{
    		fgd = vidolist.get(i).get("fgd").toString();
    	}catch(Exception e){
    		fgd = "";
    	}
    	try{
    		jgdj = vidolist.get(i).get("jgdj").toString();
    	}catch(Exception e){
    		jgdj = "";
    	}
    	try{
    		lj = vidolist.get(i).get("lj").toString();
    	}catch(Exception e){
    		lj = "";
    	}
    	try{
    		zt = vidolist.get(i).get("zt").toString();
    	}catch(Exception e){
    		zt = "";
    	}
    	try{
    		dflz = vidolist.get(i).get("dflz").toString();
    	}catch(Exception e){
    		dflz = "";
    	}
    	
    	vidolist.get(i).put("fgd", fgd);
    	vidolist.get(i).put("jgdj", jgdj);
    	vidolist.get(i).put("lj", lj);
    	vidolist.get(i).put("zt", zt);
    	vidolist.get(i).put("dflz", dflz);
    	}
    	
    	return vidolist;
    }
    
    

    
    
    /**查看替换图片*/
    @ResponseBody
	@RequestMapping(value = "/selectimage.do",method= RequestMethod.GET)
	public Object selectimage() {
    	
    	String partyapplyimage = null;
    	String partyapplyimage1 = null;
    	String zzgximage = null;
    	String zzgximage1 = null;
    	String lddyimage = null;
    	String lddyimage1 = null;
    	List<Map<String,Object>> vidolist =  partyService.selectimage();
    	for(int i = 0;i < vidolist.size(); i++){
    		try{
    		  partyapplyimage = vidolist.get(i).get("partyapplyimage").toString();
    		  String a = partyapplyimage.substring(partyapplyimage.length()-1,partyapplyimage.length());
    		  if(a.equals(",")){
      			partyapplyimage1 = "https://zgeqscjdglj.org/upload/"+partyapplyimage.substring(0,partyapplyimage.length()-1);
      			partyapplyimage1=partyapplyimage1.replaceAll("\\\\","/");
      		}else{
      			partyapplyimage1 = "https://zgeqscjdglj.org/upload/"+partyapplyimage;
      			partyapplyimage1=partyapplyimage1.replaceAll("\\\\","/");
      		}
    		}catch(Exception e){
    			partyapplyimage1 = "";
    		}
    		
    		
    		try{
    		   zzgximage = vidolist.get(i).get("zzgximage").toString();
    	   		String b = zzgximage.substring(zzgximage.length()-1,zzgximage.length());
    	   		if(b.equals(",")){
    				zzgximage1 = "https://zgeqscjdglj.org/upload/"+zzgximage.substring(0,zzgximage.length()-1);	
    				zzgximage1=zzgximage1.replaceAll("\\\\","/");
    			}else{
    				zzgximage1 = "https://zgeqscjdglj.org/upload/"+zzgximage;	
    				zzgximage1=zzgximage1.replaceAll("\\\\","/");
    			}
    		}catch(Exception e){
    			zzgximage1 = "";
    		}
    		
    		try{
    		lddyimage = vidolist.get(i).get("lddyimage").toString();

    		String c = lddyimage.substring(lddyimage.length()-1,lddyimage.length());
    		if(c.equals(",")){
				lddyimage1 = "https://zgeqscjdglj.org/upload/"+lddyimage.substring(0,lddyimage.length()-1);	
				lddyimage1=lddyimage1.replaceAll("\\\\","/");
			}else{
				lddyimage1 = "https://zgeqscjdglj.org/upload/"+lddyimage;
				lddyimage1=lddyimage1.replaceAll("\\\\","/");
			}
    		}catch(Exception e){
    			lddyimage1 = "";
    		}

    		
			vidolist.get(i).put("partyapplyimage1", partyapplyimage1);
			vidolist.get(i).put("zzgximage1", zzgximage1);
			vidolist.get(i).put("lddyimage1", lddyimage1);
    	}
    	
    	return vidolist;
    }
    
    
    /**查看视频标题*/
    @ResponseBody
	@RequestMapping(value = "/selectvideotitle.do",method= RequestMethod.GET)
	public Object selectvideotitle() {
    	
    	String fname = null;
    	String psdate = null;
    	String video1 = null;
    	String fimage = "";
    	List<Map<String,Object>> vidolist =  partyService.selectvideo();
    	for(int i = 0;i <vidolist.size();i++){
    		fname = vidolist.get(i).get("fname").toString();
    		psdate = vidolist.get(i).get("psdate").toString();
    		video1 = vidolist.get(i).get("video1").toString();
    		
    		try{
    		fimage = vidolist.get(i).get("fimage").toString();
    		System.out.println(fimage);
    		String b = fimage.substring(0, fimage.length()-1);
    		String a = fimage.substring(fimage.length()-1, fimage.length());
    		System.out.println(a+"-----");
    		if(a.equals(",")){
    			fimage = "https://zgeqscjdglj.org/upload/"+b;
    		}else{
    			fimage = "https://zgeqscjdglj.org/upload/"+fimage;
    		}
    		}catch(Exception e){
    			fimage = "";
    		}

    		vidolist.get(i).put("fimage", fimage);
    	}
    	return vidolist;
    }
    
    
    /**查看视频详情*/
    @ResponseBody
	@RequestMapping(value = "/selectvideonr.do",method= RequestMethod.GET)
    @ApiImplicitParam(paramType="query", name = "id", value = "id", required = true, dataType = "String")
	public Object selectvideonr(String id) {
    	
    	String fname = null;
    	String psdate = null;
    	String vidoe1 = "";
    	String fimage = null;
    	
    	map.put("id", id);
    	List<Map<String,Object>> vidolist =  partyService.selectvideo1(map);
    	for(int i = 0;i <vidolist.size();i++){
    		fname = vidolist.get(i).get("fname").toString();
    		psdate = vidolist.get(i).get("psdate").toString();
    		psdate = vidolist.get(i).get("psdate").toString();
    		fimage = vidolist.get(i).get("video1").toString();
    		try{
    		vidoe1 = vidolist.get(i).get("fimage").toString();
    	
    		System.out.println(vidoe1);
    		String b = vidoe1.substring(0, vidoe1.length()-1);
    		String a = vidoe1.substring(vidoe1.length()-1, vidoe1.length());
    		System.out.println(a+"-----");
    		if(a.equals(",")){
    			vidoe1 = "https://zgeqscjdglj.org/upload/"+b;
    		}else{
    		    vidoe1 = "https://zgeqscjdglj.org/upload/"+vidoe1;
    		}
    		}catch(Exception e){
    			vidoe1 = "";
    		}

    		vidolist.get(i).put("video1", vidoe1);
    		vidolist.get(i).put("fimage", fimage);
    	}
    	return vidolist;
    }
    
    /**上传视频*/
    @ResponseBody
	@RequestMapping(value = "/updatevideo.do",method= RequestMethod.GET)
	public Object insertvideo(String video,String fscard,String stud) {
    	String fname = null;
    	String partybranch = null;
    	map.put("fscard", fscard);
    	List<Map<String,Object>> partylist =  partyService.selectparty(map);
    	for(int i = 0;i < partylist.size();i++){
    		partybranch = partylist.get(i).get("partybranch").toString();
    		fname = partylist.get(i).get("fname").toString();
    	}
    	map.put("video", video);
    	map.put("fscard", fscard);
    	map.put("psdparty", partybranch);
    	map.put("stud", stud);
    	map.put("fname", fname);
    	map.put("psdate", new Date());
    	int type = partyService.updatevideo(map);
    	
    	
    	return type;
    }
    
    
    /**上传思想汇报
     * */
    @ResponseBody
	@RequestMapping(value = "/updatesxhb.do",method= RequestMethod.POST)
/*    @ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "report", value = "report", required = true, dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "id_card", value = "id_card", required = true, dataType = "String")
    })*/
	public Object updatesxhb(@RequestBody String putInStorage,MultipartFile file,String report,String id_card,HttpServletRequest request) {
    	Integer type = 0;
    	String id = null;
    	String sxhb = null;
    	String fileName = null;
    	String ret_fileName = null;// 返回给前端已修改的图片名称   
    	String b1 = "";
    	
    	JSONObject json = JSONObject.fromObject(putInStorage);
    	report = json.get("report").toString(); 
    	id_card = json.get("id_card").toString(); 
    	fileName = json.get("fileName").toString(); 
    	b1 = fileName.substring(fileName.indexOf(".")-1, fileName.length());
    	System.out.println(report);
    	System.out.println(id_card);
    	System.out.println(fileName);
 
    	
//保存图片上服务器  base64解码

    	String path = "C://apache-tomcat-7.0.47/webapps/upload/";
		System.out.println("path="+path);
	        File targetFile = new File(path);  
	      
	        if(!targetFile.exists()){  
	        
	        	targetFile.mkdirs();    
				
	        }  

	           // 用于设置图片过大，存入临时文件    
	           DiskFileItemFactory factory = new DiskFileItemFactory();    
	           factory.setSizeThreshold(20 * 1024 * 1024); // 设定使用内存超过5M时，将产生临时文件并存储于临时目录中。    
	           factory.setRepository(new File(path)); // 设定存储临时文件的目录。    
	   
	           ServletFileUpload upload = new ServletFileUpload(factory);    
	           upload.setHeaderEncoding("UTF-8");   
	           String fda1 = report.substring(report.indexOf("data"), report.lastIndexOf(",")+1);	
	           System.out.println(fda1);
	           report = report.replaceAll(fda1, "");      
	               BASE64Decoder decoder = new BASE64Decoder();      
	               try {      
	                   // Base64解码      
	                   byte[] b = decoder.decodeBuffer(report);      
	                   for (int i = 0; i < b.length; ++i) {      
	                       if (b[i] < 0) {// 调整异常数据      
	                           b[i] += 256;      
	                       }      
	                   }      
	                   // 生成jpeg图片      
	                   //ret_fileName = new String(fileName).getBytes("gb2312" ) ;  
	                   fileName = String.valueOf(new Date().getTime())+b1;
	                   ret_fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1" ) ;     
	                   File file1 = new File(path + "/" + ret_fileName);    
	                   OutputStream out = new FileOutputStream(file1);      
	                   out.write(b);      
	                   out.flush();      
	                   out.close();      
	               } catch (Exception e) {      
	                   e.printStackTrace();      
	               }      
  

    	map.put("fscard", id_card);
    	List<Map<String,Object>> partylist =  partyService.selectparty(map);
    	
    	map.put("fscard", id_card);
    	List<Map<String,Object>> partylist1 =  partyService.selectapply(map);
    	
    	if(partylist.size() != 0){
    		
    		for(int i = 0;i < partylist.size();i++){
        		id = partylist.get(i).get("id").toString();
        	}
    		map.put("sxid", id);
        	map.put("sxdate", new Date());
        	map.put("sxhb", fileName);
        	type =partyService.insertsxhb(map);
    		
    	}else if(partylist1.size() != 0){
    		for(int i = 0;i < partylist1.size();i++){
        		id = partylist1.get(i).get("id").toString();
        	}
    		
    		map.put("sxid", id);
        	map.put("sxdate", new Date());
        	map.put("sxhb", fileName);
        	type =partyService.insertsxhb(map);
    	}
    	System.out.println(id);
    	
    	
    	return type;
    }
    
    

    
    
    /**判断是否党员*/
    @ResponseBody
	@RequestMapping(value = "/dy.do",method= RequestMethod.GET)
    @ApiImplicitParam(paramType="query", name = "fscard", value = "fscard", required = true, dataType = "String")
	public Object dy(String fscard) {
    	String partybranch = null;
    	String partybranch1 = null;
    	Date partydate = null;
    	String fname = null;
    	String feedback = null;
    	String fstatus = null;
    	 String zrfeedback1 = "";
    	 String feedback1 = "";
    	 String ydate = "";
    	 String zrdate = "";
    	 String zrfeedback = "";
    	 String zrwhy = "";
    	 String zrwhy1 = "";
    	 String feedbacks = "";
    	 List list = new ArrayList();
    	List list1 = new ArrayList();
    	Integer type = null;
    	map.put("fscard", fscard);
    	List<Map<String,Object>> partylist =  partyService.selectparty(map);   //党员信息
    	System.out.println(partylist+"-----");
    	map.put("fscard", fscard);
    	List<Map<String,Object>> applylist =  partyService.selectapply(map);    //非党员信息
    	if(partylist.size() != 0){
    		System.out.println("是党员------------");
    		for(int i = 0;i < partylist.size();i++ ){
    			partybranch = partylist.get(i).get("partybranch").toString();
    			String changebranch = partylist.get(i).get("changeparty").toString();
    			fname = partylist.get(i).get("fname").toString();
    			partydate = (Date) partylist.get(i).get("partydate");
    			partybranch1 = sf.format(partydate);
    			
    			try{
    				 zrwhy = partylist.get(i).get("zrwhy").toString();
    			}catch(Exception e){
    				zrwhy = "";
    			}
    			
    			try{
    				 zrwhy1 = partylist.get(i).get("zrwhy1").toString();
    			}catch(Exception e){
    				zrwhy1 = "";
    			}
    			
    			try{
    				 feedbacks = partylist.get(i).get("feedback").toString();
    				 if(feedbacks.equals("同意")){
    	    			  feedback1 = partybranch + "同意申请"; 
    	    			}else{
    	    				feedback1 = partybranch + zrwhy1;
    	    			}
    			}catch(Exception e){
    				feedbacks = "";
    			}
    			try{
   				 zrfeedback = partylist.get(i).get("zrfeedback").toString();
   				 if(zrfeedback.equals("同意")){
   	    			   zrfeedback1 =changebranch + "同意申请"; 
   	    			}else{
   	    				zrfeedback1 = changebranch + zrwhy;
   	    		}
   			}catch(Exception e){
   				zrfeedback = "";
   			}
    			try{
    			 ydate = partylist.get(i).get("ydate").toString();
    			}catch(Exception e ){
    				ydate = "";
    			}
    			
    			try{
    			 zrdate = partylist.get(i).get("zrdate").toString();
    			}catch(Exception e){
    				zrdate = "";
    			}
    			
    			
    			
    			
    			partylist.get(i).put("partydate", partybranch1);
    			partylist.get(i).put("zrfeedback", zrfeedback1);  //转出党支部
    			partylist.get(i).put("feedback", feedback1);   //原党支部
    			partylist.get(i).put("zrdate", zrdate);   //原党支部
    			partylist.get(i).put("ydate", ydate);   //原党支部
    			
    		}
    		
    		list = partylist;
    		type = 0;
    	}else  if(applylist.size() != 0){
    		System.out.println("非党员--------------");
    		for(int i = 0;i < applylist.size();i++ ){
    			try{
    			 feedback = applylist.get(i).get("feedback").toString();
    			}catch(Exception e){
    				feedback = "";
    			}
    			String zt = applylist.get(i).get("fstatus").toString();
    			if(zt.equals("0")){
    				zt = "入党申请者";
    			}else if(zt.equals("1")){
    				zt = "入党积极分子";
    			}else if(zt.equals("2")){
    				zt = "预备党员";
    			}else if(zt.equals("3")){
    				zt = "党员";
    			}
    			applylist.get(i).put("feedback", feedback);
    			applylist.get(i).put("dangyuanzt", zt);
    		}
    		list = applylist;
    		type = 1;
    	}else{
    		System.out.println("没有该身份证--------------");
    		list = new ArrayList();
    		type = 2;
    	}
    	
    	
    	list1.add(type);
    	list1.add(list);
    	return list1;
    }
    
    
    
    /**显示学历*/
    @ResponseBody
	@RequestMapping(value = "/xl.do",method= RequestMethod.GET)
   // @ApiImplicitParam(paramType="query", name = "fscard", value = "fscard", required = true, dataType = "String")
	
	public Object xl() {
    	//response.setHeader("Access-Control-Allow-Origin", "*");
    	List<Map<String,Object>> xllist =  partyService.selectxl();
    	
    	return xllist;
    }
    
    /**显示党支部*/
    @ResponseBody
	@RequestMapping(value = "/selectpartybranch.do",method= RequestMethod.GET)
	public Object selectpartybranch() {
    	//response.setHeader("Access-Control-Allow-Origin", "*");
    	List<Map<String,Object>> xllist =  partyService.selectpartybranch();
    	
    	return xllist;
    }
    
    
    
    
    /**查看该党员反馈*/
    @ResponseBody
	@RequestMapping(value = "/fk.do",method= RequestMethod.GET)
    @ApiImplicitParam(paramType="query", name = "fscard", value = "fscard", required = true, dataType = "String")
	public Object fk(String fscard) {
    	List list = new ArrayList();
    	String feedback = "";
    	map.put("fscard", fscard);
    	List<Map<String,Object>> partylist =  partyService.selectparty(map);   //党员信息
    	System.out.println(partylist+"-----");
    	map.put("fscard", fscard);
    	List<Map<String,Object>> applylist =  partyService.selectapply(map);    //非党员信息
    	if(partylist.size() != 0){
    		System.out.println("是党员------------"); 		
    		feedback = "";
    	}else  if(applylist.size() != 0){
    		System.out.println("非党员--------------");
    		for(int i = 0;i < applylist.size();i++ ){
    			try{
    			 feedback = applylist.get(i).get("feedback").toString();
    			}catch(Exception e){
    				feedback = "";
    			}
    		}
    	
    	}else{
    		feedback = "";
    	}
    	
    	return feedback;
    }
    
    
    
    /**查看该党员申请填写信息*/
    @ResponseBody
	@RequestMapping(value = "/information.do",method= RequestMethod.GET)
    @ApiImplicitParam(paramType="query", name = "fscard", value = "fscard", required = true, dataType = "String")
    public Object information(String fscard) {
    	List list = new ArrayList();
    	Integer type = 0;
    	String fimage = null;
    	String zt = "";
    	map.put("fscard", fscard);
    	List<Map<String,Object>> partylist =  partyService.selectparty(map);   //党员信息
    	map.put("fscard", fscard);
    	List<Map<String,Object>> applylist =  partyService.selectapply(map);   //非党员资料
    	if(applylist.size() != 0){
    	for(int i = 0;i < applylist.size();i++ ){
			 zt = applylist.get(i).get("fstatus").toString();
			 fimage = applylist.get(i).get("fimage").toString();
			String check = applylist.get(i).get("fcheck").toString();
			if(zt.equals("0")){
				zt = "入党申请者";
			}else if(zt.equals("1")){
				zt = "入党积极分子";
			}else if(zt.equals("2")){
				zt = "预备党员";
			}else if(zt.equals("3")){
				zt = "党员";
			}
			
			if(check.equals("0")){
				check = "未审";
			}else if(check.equals("1")){
				check = "审核";
			}
			fimage =  "https://zgeqscjdglj.org/upload/"+fimage;
			applylist.get(i).put("dangyuanzt", zt);
			applylist.get(i).put("check", check);
			applylist.get(i).put("fimage", fimage);

    	}
    	list = applylist;
    	}
    	if(applylist.size() == 0){
    		list = partylist;
    	}
    	return list;
    }
    
    
    
    
    /**流动党员找组织
     * @throws ParseException */
    @ResponseBody
	@RequestMapping(value = "/lddyzzz.do",method= RequestMethod.POST)        //
   // @ApiImplicitParam(paramType="query", name = "putInStorage", value = "putInStorage", required=false, dataType = "String")
	public Object lddyzzz(@RequestBody String putInStorage) throws ParseException {
    	String fname = null;
    	String fsex = null;
    	String fmz = null;
    	String fscard = null;
    	String ybarch = null;
    	String zrbarch = null;
    	String fxl = null;
    	String fmobile = null;
    	String unit = null;
    	String dfjndate = null;
    	String rddate = null;
    	String dystatus = null;
    	String fimage = null;
    	String ret_fileName  = null;   //图片名
    	String fileName = null;
       	Integer  maxid = 0;
       	Integer type = 0;
    	//查找最大id
    	List<Map<String,Object>> maxidlist =  partyService.selectapplyid();
    	if(maxidlist.size() != 0){
    		for(int i = 0;i < maxidlist.size();i++){
        		maxid = Integer.valueOf(maxidlist.get(i).get("id").toString());
        	}
    	}
    	JSONObject json = JSONObject.fromObject(putInStorage);
    	JSONObject json1=json.getJSONObject("Info");
    	System.out.println(json1);
    	fname = json1.get("name").toString();
    	fsex = json1.get("sex").toString();
    	fmz = json1.get("nation").toString();
    	fscard = json1.get("id_card").toString();
    	ybarch = json1.get("OldOrg").toString();
    	zrbarch = json1.get("JoinOrg").toString();
    	fxl = json1.get("education").toString();
    	fmobile = json1.get("phone").toString();
    	unit = json1.get("position").toString();
    	dfjndate = json1.get("payDate").toString();
    	fileName = json1.get("FileName").toString();
    	dfjndate = dfjndate.replace("Z", " UTC");
    	System.out.println(dfjndate);
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
    	Date dfjndate1 = format1.parse(dfjndate);
    	System.out.println(dfjndate1);
    	String c1 =  Order.format(dfjndate1);
    	System.out.println(c1);
    	
    	
    	rddate = json1.get("entryPartyTime").toString();
    	rddate = rddate.replace("Z", " UTC");
    	System.out.println(rddate);
    	Date rddate1 = format1.parse(rddate);
    	System.out.println(rddate1);
    	String c2 =  Order.format(rddate1);
    	System.out.println(c2);
    	
    	dystatus = json1.get("status").toString();
    	//图片
    	
    	
    	try{
    	fimage = json1.get("file").toString();
    	String path = "C://apache-tomcat-7.0.47/webapps/upload/";
		System.out.println("path="+path);
	        File targetFile = new File(path);  
	      
	        if(!targetFile.exists()){  
	        
	        	targetFile.mkdirs();    
				
	        }  

	           // 用于设置图片过大，存入临时文件    
	           DiskFileItemFactory factory = new DiskFileItemFactory();    
	           factory.setSizeThreshold(20 * 1024 * 1024); // 设定使用内存超过5M时，将产生临时文件并存储于临时目录中。    
	           factory.setRepository(new File(path)); // 设定存储临时文件的目录。    
	   
	           ServletFileUpload upload = new ServletFileUpload(factory);    
	           upload.setHeaderEncoding("UTF-8");   
	           String fda1 = fimage.substring(fimage.indexOf("data"), fimage.lastIndexOf(",")+1);	
	           System.out.println(fda1);
	           fimage = fimage.replaceAll(fda1, "");   
	               BASE64Decoder decoder = new BASE64Decoder();      
	               try {      
	                   // Base64解码      
	                   byte[] b = decoder.decodeBuffer(fimage);      
	                   for (int i = 0; i < b.length; ++i) {      
	                       if (b[i] < 0) {// 调整异常数据      
	                           b[i] += 256;      
	                       }      
	                   }      
	                   // 生成jpeg图片      
	                   //ret_fileName = new String(fileName).getBytes("gb2312" ) ;
	                   // 生成jpeg图片   
	                   fileName = String.valueOf(maxid) + GetUtf8(fileName);
	                   ret_fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1" ) ;     
	                   File file = new File(ret_fileName);    
	                   OutputStream out = new FileOutputStream(file);      
	                   out.write(b);      
	                   out.flush();      
	                   out.close();      
	               } catch (Exception e) {      
	                   e.printStackTrace();      
	               }   
    	}catch(Exception e){
    		System.out.println("没有上传图片----");
    	}
    	
    	
    	map.put("fname", fname);
    	map.put("fsex", fsex);
    	map.put("fmz", fmz);
    	map.put("fscard", fscard);
    	map.put("ybarch", ybarch);
    	map.put("zrbarch", zrbarch);
    	map.put("fxl", fxl);
    	map.put("fmobile", fmobile);
    	map.put("unit", unit);
    	map.put("dfjndate", c1);
    	map.put("rddate", c2);
    	map.put("dystatus", dystatus);
    	map.put("fimage", ret_fileName);
    	 type = partyService.lddyzzz(map);
    	return type;
    }
    
    
    
    
    /**base64解密*/
    public static String decryptBASE64(String key){
    	byte[] bt;
    	try {
    	bt = (new BASE64Decoder()).decodeBuffer(key);
    	return new String(bt, "GB2312");
    	} catch (IOException e) {
    	e.printStackTrace();
    	return "";
    	}
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
    
   
}

