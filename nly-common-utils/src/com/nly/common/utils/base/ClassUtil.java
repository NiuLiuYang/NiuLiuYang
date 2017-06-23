package com.nly.common.utils.base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

/**
 * ���������
 * @author NLY
 *
 */
@SuppressWarnings("rawtypes")
public class ClassUtil {

	/**
     * ���ݶ��󣬷���һ��class�������ڻ�ȡ����
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
	 * ͨ�������������
	 * 
	 * @param obj
	 *            �����Ķ���
	 * @param att
	 *            ����������
	 * @param value
	 *            ���õ�ֵ
	 * @param type
	 *            ����������
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
	 * ͨ�����䶯̬ȡ�ö����ֵ
	 * 
	 * @param obj
	 *            �����Ķ���
	 * @param att
	 *            ����������
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
	 * ͨ������ֱ�����ö����ֵ
	 * 
	 * @param obj
	 *            �����Ķ���
	 * @param att
	 *            ����������
	 * @param value
	 *            ��Ҫ���õ�ֵ
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
	 * ͨ������ֱ��ȡ�ö����ֵ
	 * 
	 * @param obj
	 *            �����Ķ���
	 * @param att
	 *            ����������
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
	 * ��ȡ��ǰ�෺������
	 * @param entity
	 * @return
	 */
	public static  Class  getEntityClass(Object entity){
		ParameterizedType parameterizedType = (ParameterizedType)entity.getClass().getGenericSuperclass(); 

		Class  entityClass= (Class)(parameterizedType.getActualTypeArguments()[0]); 
		return entityClass;
	}
	
	/**
	 * �õ����е��������Լ��� 
	 * @return
	 */
	public static Field[] getField(Object obj){
		Class<?> userCla = getClass(obj);
		return userCla == null ? null : userCla.getDeclaredFields();
	}
	
	/**
	 * ������ת����һ��Map
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
           f.setAccessible(true);//����Щ�����ǿ��Է��ʵ� 
           Object val = f.get(obj);//�õ������Ե�ֵ
           map.put(f.getName(), val);
	     }
	    return map;
	}
	
}
