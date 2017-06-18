package com.nly.common.utils.base;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

public class LogUtil {
	 public static Logger log = Logger.getLogger(LogUtil.class);
	  
	  /**
	   * ��ӡ����
	   * 
	   * @param obj
	   */
	  public static void warn(Object obj) {
	      try{
	    	  String location = getCodeLocation();
	          /*** �Ƿ����쳣  ***/
	          if (obj instanceof Exception) {
	              Exception e = (Exception) obj;
	              StringWriter sw = new StringWriter();
	              e.printStackTrace(new PrintWriter(sw, true));
	              String str = sw.toString();
	              log.warn(location + str);
	          } else {
	              log.warn(location + obj.toString());
	          }
	      }catch (Exception e) {
	          // TODO: handle exception
	          e.printStackTrace();
	      }
	  }
	  
	  public static void debug(Object obj) {
	      try{
	    	  String location = getCodeLocation();
	          /*** �Ƿ����쳣  ***/
	          if (obj instanceof Exception) {
	              Exception e = (Exception) obj;
	              StringWriter sw = new StringWriter();
	              e.printStackTrace(new PrintWriter(sw, true));
	              String str = sw.toString();
	              log.debug(location + str);
	          } else {
	              log.debug(location + obj.toString());
	          }
	      }catch (Exception e) {
	      }
	  }
	  /**
	   * ��ӡ��Ϣ
	   * 
	   * @param obj
	   */
	  public static void info(Object obj) {
	      try{
	    	  String location = getCodeLocation();
	          /*** �Ƿ����쳣  ***/
	          if (obj instanceof Exception) {
	              Exception e = (Exception) obj;
	              StringWriter sw = new StringWriter();
	              e.printStackTrace(new PrintWriter(sw, true));
	              String str = sw.toString();
	              log.info(location + str);
	          } else {
	              log.info(location + obj.toString());
	          }
	      }catch (Exception e) {
	          e.printStackTrace();
	      }
	  }
	  public static void error(Object obj) {
	      try{
	          /*** ��ȡ�����Ϣ�Ĵ����λ�� ***/
	          String location = getCodeLocation();
	          /*** �Ƿ����쳣  ***/
	          if (obj instanceof Exception) {
	              Exception e = (Exception) obj;
	              StringWriter sw = new StringWriter();
	              e.printStackTrace(new PrintWriter(sw, true));
	              String str = sw.toString();
	              log.error(location + str);
	          } else {
	              log.error(location + obj.toString());
	          }
	      }catch (Exception e) {
	      }
	  }
	  /**
	   * ��ӡ����
	   * 
	   * @param obj
	   */
	  public static void error(String message,Object obj) {
	      try{
	          /*** ��ȡ�����Ϣ�Ĵ����λ�� ***/
	          String location = getCodeLocation();
	          /*** �Ƿ����쳣  ***/
	          if (obj instanceof Exception) {
	              Exception e = (Exception) obj;
	              StringWriter sw = new StringWriter();
	              e.printStackTrace(new PrintWriter(sw, true));
	              String str = sw.toString();
	              log.error(location+str+message);
	          } else {
	              log.error(location + obj.toString());
	          }
	      }catch (Exception e) {
	      }
	  }

	  /**
	   * ��ȡ���ô˺����Ĵ����λ��
	   * @return ����.����.������(����)
	   */
	  public static String getCodeLocation(){
	      try{
	          /*** ��ȡ�����Ϣ�Ĵ����λ�� ***/
	          String location = "";
	          StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
	          location = stacks[2].getClassName() + "." + stacks[2].getMethodName()
	                  + "(" + stacks[2].getLineNumber() + ")";
	          return location;
	      }catch (Exception e) {
	    	  LogUtil.error("",e);
	          return "";
	      }
	  }
}
