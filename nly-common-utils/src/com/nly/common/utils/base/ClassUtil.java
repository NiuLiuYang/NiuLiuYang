package com.nly.common.utils.base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射操作类
 * @author NLY
 *
 */
@SuppressWarnings("rawtypes")
public class ClassUtil {

	/**
     * 根据对象，返回一个class对象，用于获取方法
     */
    public static Class<?> getClass(Object obj) {
        try {
            return Class.forName(obj.getClass().getName());
        } catch (Exception e) {
        	 LogUtil.error(e.getMessage());
            return null;
        }
    }
	
	
	/**
	 * 通过反射操作对象
	 * 
	 * @param obj
	 *            操作的对象
	 * @param att
	 *            操作的属性
	 * @param value
	 *            设置的值
	 * @param type
	 *            参数的属性
	 * @author liwq 2015-07-17
	 * */
	public static void setter(Object obj, String att, Object value,
			Class<?> type) {
		try {
			Method method = obj.getClass().getMethod("set" + att, type);
			method.invoke(obj, value);
		} catch (Exception e) {
			 LogUtil.error(e.getMessage());
		}
	}

	/**
	 * 通过反射动态取得对象的值
	 * 
	 * @param obj
	 *            操作的对象
	 * @param att
	 *            操作的属性
	 * */
	public static Object getter(Object obj, String att) {
		try {
			Method method = obj.getClass().getMethod("get" + att);
			return method.invoke(obj);
		} catch (Exception e) {
			 LogUtil.error(e.getMessage());
		}
		return null;
	}
	
	
	 
	/**
	 * 通过反射直接设置对象的值
	 * 
	 * @param obj
	 *            操作的对象
	 * @param att
	 *            操作的属性
	 * @param value
	 *            需要设置的值
	 * */
	public static void setField(Object obj, String att, Object value) {
		try {
			Field field = obj.getClass().getDeclaredField(att);
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			LogUtil.error(e.getMessage());
		}
	}

	/**
	 * 通过反射直接取得对象的值
	 * 
	 * @param obj
	 *            操作的对象
	 * @param att
	 *            操作的属性
	 * */
	public static Object getField(Object obj, String att) {
		try {
			Field field = obj.getClass().getDeclaredField(att);
			field.setAccessible(true);
			return field.get(obj);
		} catch (Exception e) {
			 LogUtil.error(e.getMessage());
		}
		return null;
	}

	public static Object getSupperFile(Object obj, String att) {
		try {
			Field field = obj.getClass().getSuperclass().getDeclaredField(att);
			field.setAccessible(true);
			return field.get(obj);
		} catch (Exception e) {
			 LogUtil.error(e.getMessage());
		}
		return null;

	}
	
	

	/**
	 * 获取当前类泛型类型
	 * @param entity
	 * @return
	 */
	public static  Class  getEntityClass(Object entity){
		ParameterizedType parameterizedType = (ParameterizedType)entity.getClass().getGenericSuperclass(); 

		Class  entityClass= (Class)(parameterizedType.getActualTypeArguments()[0]); 
		return entityClass;
	}
	
	/**
	 * 得到类中的所有属性集合 
	 * @return
	 */
	public static Field[] getField(Object obj){
		Class<?> userCla = getClass(obj);
		return userCla == null ? null : userCla.getDeclaredFields();
	}
	
	/**
	 * 将对象转换成一个Map
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> getObjectOrMap(Object obj) throws Exception{
		Map<String, Object> map = new HashMap<String,Object>();
	    Field[] fs = getField(obj);
	    if(fs == null) return map;
	    for(int i = 0 ; i < fs.length; i++){  
           Field f = fs[i];  
           f.setAccessible(true);//设置些属性是可以访问的 
           Object val = f.get(obj);//得到此属性的值
           map.put(f.getName(), val);
	     }
	    return map;
	}
	
}
