package org.spring.springboot.weixin;

import java.io.UnsupportedEncodingException;

public class StringUtil {
	
	public static String GetUtf8(String  str){     
     	 
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
