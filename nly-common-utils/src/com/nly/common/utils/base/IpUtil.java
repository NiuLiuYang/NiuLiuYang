package com.nly.common.utils.base;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {

	/**
	 * ��ȡ��¼�û�IP��ַ
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {  
        String ipAddress = request.getHeader("x-forwarded-for");  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getHeader("Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getRemoteAddr();  
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                //��������ȡ�������õ�IP  
                InetAddress inet=null;  
                try {  
                    inet = InetAddress.getLocalHost();  
                } catch (UnknownHostException e) {  
                	 LogUtil.error(e.getMessage());
                }  
                ipAddress= inet.getHostAddress();  
            }  
        }  
        //����ͨ�����������������һ��IPΪ�ͻ�����ʵIP,���IP����','�ָ�  
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
            if(ipAddress.indexOf(",")>0){  
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
            }  
        }  
        return ipAddress;   
	}
	
}
