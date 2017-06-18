package com.nly.common.utils.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

/**
 * json����
 * @author NLY
 */
@SuppressWarnings("rawtypes")
public class JsonUtil {

	public static final String START_ARRAY = "[";  
    public static final String END_ARRAY = "]";  
    

    
	public static String array2json(Object object) {
		JSONArray jsonArray = JSONArray.fromObject(object);
		return jsonArray.toString();
	}

	// ��JSONת��������,����valueClzΪ�����д�ŵĶ����Class
	public static Object json2Array(String json, Class valueClz) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		return JSONArray.toArray(jsonArray, valueClz);
	}

	// ��Collectionת����JSON
	public static String collection2json(Object object) {
		JSONArray jsonArray = JSONArray.fromObject(object);
		return jsonArray.toString();
	}

	// ��Mapת����JSON
	public static String map2json(Object object) {
		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
	}

	// ��JSONת����Map,����valueClzΪMap��value��Class,keyArrayΪMap��key
	@SuppressWarnings("unchecked")
	public static Map json2Map(Object[] keyArray, String json, Class valueClz) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		Map classMap = new HashMap();

		for (int i = 0; i < keyArray.length; i++) {
			classMap.put(keyArray[i], valueClz);
		}

		return (Map) JSONObject.toBean(jsonObject, Map.class, classMap);
	}

	// ��POJOת����JSON
	public static String bean2json(Object object) {
		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
	}

	// ��JSONת����POJO,����beanClzΪPOJO��Class
	public static Object json2Object(String json, Class beanClz) {
		return JSONObject.toBean(JSONObject.fromObject(json), beanClz);
	}

	// ��Stringת����JSON
	public static String string2json(String key, String value) {
		JSONObject object = new JSONObject();
		object.put(key, value);
		return object.toString();
	}
	@SuppressWarnings({ "unchecked" })
	public static final <T> List<T>  toBeanList(String jsonString,T t){
		 JSONArray json = JSONArray.fromObject(jsonString);
		 List<T> objectList = (List<T>)JSONArray.toCollection(json, t.getClass());
		 return objectList;
	}
	
	// ��JSONת����String
	public static String json2String(String json, String key) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		return jsonObject.get(key).toString();
	}

	/***
	 * ��List�������л�ΪJSON�ı�
	 */
	public static <T> String toJSONString(List<T> list) {
		JSONArray jsonArray = JSONArray.fromObject(list);

		return jsonArray.toString();
	}

	/***
	 * ���������л�ΪJSON�ı�
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object) {
		JSONArray jsonArray = JSONArray.fromObject(object);

		return jsonArray.toString();
	}

	/***
	 * ��JSON�����������л�ΪJSON�ı�
	 * 
	 * @param jsonArray
	 * @return
	 */
	public static String toJSONString(JSONArray jsonArray) {
		return jsonArray.toString();
	}
	
	public static String toJSONStringForJSONArray(JSONArray jsonArray) {
		return jsonArray.toString();
	}

	/***
	 * ��JSON�������л�ΪJSON�ı�
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static String toJSONString(JSONObject jsonObject) {
		return jsonObject.toString();
	}

	/***
	 * ������ת��ΪList����
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List toArrayList(Object object) {
		List arrayList = new ArrayList();

		JSONArray jsonArray = JSONArray.fromObject(object);

		Iterator it = jsonArray.iterator();
		while (it.hasNext()) {
			JSONObject jsonObject = (JSONObject) it.next();

			Iterator keys = jsonObject.keys();
			while (keys.hasNext()) {
				Object key = keys.next();
				Object value = jsonObject.get(key);
				arrayList.add(value);
			}
		}

		return arrayList;
	}


	/***
	 * ������ת��ΪJSON��������
	 * 
	 * @param object
	 * @return
	 */
	public static JSONArray toJSONArray(Object object) {
		return JSONArray.fromObject(object);
	}

	/***
	 * ������ת��ΪJSON����
	 * 
	 * @param object
	 * @return
	 */
	public static JSONObject toJSONObject(Object object) {
		return JSONObject.fromObject(object);
	}

	/***
	 * ������ת��ΪHashMap
	 * 
	 * @param object
	 * @return
	 */
	public static HashMap toHashMap(Object object) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		JSONObject jsonObject = JsonUtil.toJSONObject(object);
		Iterator it = jsonObject.keys();
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			Object value = jsonObject.get(key);
			data.put(key, value);
		}

		return data;
	}
	
	  /** 
	    * ��json��ʽ���ַ���������Map���� <li> 
	    * json��ʽ��{"name":"admin","retries":"3fff","testname":"ddd","testretries":"fffffffff"} 
	    */  
	   public static Map<String, Object> json2Map(String jsonStr)  {  
	       Map<String, Object> data = new HashMap<String, Object>();  
	       // ��json�ַ���ת����jsonObject  
	       JSONObject jsonObject = JSONObject.fromObject(jsonStr);  
	       Iterator it = jsonObject.keys();  
	       // ����jsonObject���ݣ���ӵ�Map����  
	       while (it.hasNext())  
	       {  
	           String key = String.valueOf(it.next());  
	           Object value = jsonObject.get(key);  
	           data.put(key, value);  
	       }  
	       return data;  
	   }  
	   
	   
	   /** 
	    * ��json��ʽ���ַ���������Map���� <li> 
	    * json��ʽ��{"name":"admin","retries":"3fff","testname":"ddd","testretries":"fffffffff"} 
	    */  
	   public static Map<String, List<Map<String, Object>>> json2MapList(String jsonStr)  {  
	       Map<String, List<Map<String, Object>>> data = new HashMap<String, List<Map<String, Object>>>();  
	       // ��json�ַ���ת����jsonObject  
	       JSONObject jsonObject = JSONObject.fromObject(jsonStr);  
	       Iterator it = jsonObject.keys();  
	       // ����jsonObject���ݣ���ӵ�Map����  
	       while (it.hasNext())  
	       {  
	           String key = String.valueOf(it.next());  
	           Object value = jsonObject.get(key);  
	           List<Map<String, Object>> list = toList(value);
	           data.put(key, list);  
	       }  
	       return data;  
	   }  

	/***
	 * ������ת��ΪList<Map<String,Object>>
	 * 
	 * @param object
	 * @return
	 */
	// ���ط�ʵ������(Map<String,Object>)��List
	public static List<Map<String, Object>> toList(Object object) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		JSONArray jsonArray = JSONArray.fromObject(object);
		for (Object obj : jsonArray) {
			JSONObject jsonObject = (JSONObject) obj;
			Map<String, Object> map = new HashMap<String, Object>();
			Iterator it = jsonObject.keys();
			while (it.hasNext()) {
				String key = (String) it.next();
				Object value = jsonObject.get(key);
				map.put((String) key, value);
			}
			list.add(map);
		}
		return list;
	}
	
	// ���ط�ʵ������(Map<String,Object>)��List
	public static List<Map<String, Object>> toList(JSONArray jsonArray) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Object obj : jsonArray) {
			JSONObject jsonObject = (JSONObject) obj;
			Map<String, Object> map = new HashMap<String, Object>();
			Iterator it = jsonObject.keys();
			while (it.hasNext()) {
				String key = (String) it.next();
				Object value = jsonObject.get(key);
				map.put((String) key, value);
			}
			list.add(map);
		}
		return list;
	}

	/***
	 * ��JSON��������ת��Ϊ�������͵�List
	 * 
	 * @param <T>
	 * @param jsonArray
	 * @param objectClass
	 * @return
	 */
	 
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static <T> List<T> toList(JSONArray jsonArray, Class<T> objectClass) {
		return JSONArray.toList(jsonArray, objectClass);
	}

	/***
	 * ������ת��Ϊ�������͵�List
	 * 
	 * @param <T>
	 * @param jsonArray
	 * @param objectClass
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static <T> List<T> toList(Object object, Class<T> objectClass) {
		JSONArray jsonArray = JSONArray.fromObject(object);

		return JSONArray.toList(jsonArray, objectClass);
	}

	/***
	 * ��JSON����ת��Ϊ�������͵Ķ���
	 * 
	 * @param <T>
	 * @param jsonObject
	 * @param beanClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toBean(JSONObject jsonObject, Class<T> beanClass) {
		return (T) JSONObject.toBean(jsonObject, beanClass);
	}

	/***
	 * ��������ת��Ϊ�������͵Ķ���
	 * 
	 * @param <T>
	 * @param object
	 * @param beanClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toBean(Object object, Class<T> beanClass) {
		JSONObject jsonObject = JSONObject.fromObject(object);

		return (T) JSONObject.toBean(jsonObject, beanClass);
	}

	/***
	 * ��JSON�ı������л�Ϊ���ӹ�ϵ��ʵ��
	 * 
	 * @param <T>
	 *            ����T ������ʵ������
	 * @param <D>
	 *            ����D �����ʵ������
	 * @param jsonString
	 *            JSON�ı�
	 * @param mainClass
	 *            ��ʵ������
	 * @param detailName
	 *            ��ʵ��������ʵ�����е���������
	 * @param detailClass
	 *            ��ʵ������
	 * @return
	 */
	public static <T, D> T toBean(String jsonString, Class<T> mainClass,
			String detailName, Class<D> detailClass) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray jsonArray = (JSONArray) jsonObject.get(detailName);

		T mainEntity = JsonUtil.toBean(jsonObject, mainClass);
		List<D> detailList = JsonUtil.toList(jsonArray, detailClass);

		try {
			BeanUtils.setProperty(mainEntity, detailName, detailList);
		} catch (Exception ex) {
			throw new RuntimeException("���ӹ�ϵJSON�����л�ʵ��ʧ�ܣ�");
		}

		return mainEntity;
	}


	/***
	 * ��JSON�ı������л�Ϊ���ӹ�ϵ��ʵ��
	 * 
	 * @param <T>
	 *            ��ʵ������
	 * @param jsonString
	 *            JSON�ı�
	 * @param mainClass
	 *            ��ʵ������
	 * @param detailClass
	 *            ����˶����ʵ������ʵ�����������ƺ�����
	 * @return
	 */
	public static <T> T toBean(String jsonString, Class<T> mainClass,
			HashMap<String, Class> detailClass) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		T mainEntity = JsonUtil.toBean(jsonObject, mainClass);
		for (Object key : detailClass.keySet()) {
			try {
				Class value = (Class) detailClass.get(key);
				BeanUtils.setProperty(mainEntity, key.toString(), value);
			} catch (Exception ex) {
				throw new RuntimeException("���ӹ�ϵJSON�����л�ʵ��ʧ�ܣ�");
			}
		}
		return mainEntity;
	}
	
	
	/** 
     * ����ת����JSON�ַ��� 
     *  
     * @param obj 
     *            ��Ҫת���Ķ��� 
     * @return �����string�ַ� 
     */  
    public static String toJson(Object obj) {  
        JSONObject jSONObject = JSONObject.fromObject(obj);  
        return jSONObject.toString();  
    }  
    
    /** 
     * JSON�ַ���ת���ɶ��� 
     *  
     * @param jsonString 
     *            ��Ҫת�����ַ��� 
     * @param type 
     *            ��Ҫת���Ķ������� 
     * @return ���� 
     */  
    @SuppressWarnings("unchecked")  
    public static <T> T fromJson(String jsonString, Class<T> type,Map<String, Class> classMap ) {
    	if(jsonString == null || "".equals(jsonString)){
    		return null;
    		
    	}
        JSONObject jsonObject = JSONObject.fromObject(jsonString);  
        return (T) JSONObject.toBean(jsonObject, type,classMap);  
    }  
    
  
    
    /** 
     * ��JSONArray����ת����list���� 
     *  
     * @param jsonArr 
     * @return 
     */  
    public static List<Object> jsonToList(JSONArray jsonArr) {  
        List<Object> list = new ArrayList<Object>();  
        for (Object obj : jsonArr) {  
            if (obj instanceof JSONArray) {  
                list.add(jsonToList((JSONArray) obj));  
            } else if (obj instanceof JSONObject) {  
                list.add(jsonToMap((JSONObject) obj));  
            } else {  
                list.add(obj);  
            }  
        }  
       
        return list;  
    }  
    
   
    
    /** 
     * ��json�ַ���ת����map���� 
     *  
     * @param json 
     * @return 
     */  
    public static Map<String, Object> jsonToMap(String json) {  
        JSONObject obj = JSONObject.fromObject(json);  
        return jsonToMap(obj);  
    }  
    /** 
     * ��JSONObjectת����map���� 
     *  
     * @param json 
     * @return 
     */  
    public static Map<String, Object> jsonToMap(JSONObject obj) {  
        Set<?> set = obj.keySet();  
        Map<String, Object> map = new HashMap<String, Object>(set.size());  
        for (Object key : obj.keySet()) {  
            Object value = obj.get(key);  
            if (value instanceof JSONArray) {  
                map.put(key.toString(), jsonToList((JSONArray) value));  
            } else if (value instanceof JSONObject) {  
                map.put(key.toString(), jsonToMap((JSONObject) value));  
            } else {  
                map.put(key.toString(), obj.get(key));  
            }  
  
        }  
        return map;  
    }  
	
}
