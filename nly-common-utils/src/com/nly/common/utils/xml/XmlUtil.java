package com.nly.common.utils.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * xml��������
 * @author NLY
 */
@SuppressWarnings("rawtypes")
public class XmlUtil {

	private static String path;
	public static XmlUtil init(String file) throws IOException{
		ClassPathResource resource = new ClassPathResource(file); 
		path = resource.getURL().getPath().replace("20%", ""); 
		return new XmlUtil();
	}
	
	/**
	 * ��ȡ���ڵ�
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static Element getRootElement() throws Exception{
		SAXReader saxReader = new SAXReader();
	    Document document = saxReader.read(new File(path));
	    Element root = document.getRootElement();
	    return root;
	}
	
	/**
	 * ��ȡָ���ڵ�
	 * @param file
	 * @param name
	 * @return
	 */
	public static List<Element> getElements(Element el,String key,List<Element> list) throws Exception{
	    for (Iterator it=el.elementIterator() ; it.hasNext();){
            Element e = (Element) it.next();
            if(e.getName().equals(key)) list.add(e);
            getElements(e,key,list);
        }
	    return list;
	}

	/**
	 * ��ȡָ���ڵ�ָ������ֵΪxxx�Ľڵ㼯��
	 * @param el �ڵ�
	 * @param parent �ڵ�����
	 * @param pId ��������
	 * @param pVal ����ֵ
	 * @param list ����
	 * @return
	 * @throws Exception
	 */
	public static List<Element> getElements(Element el,String parent,String pId,String pVal, List<Element> list) throws Exception{
	    for (Iterator it=el.elementIterator() ; it.hasNext();){
            Element e = (Element) it.next();
            if(e.getName().equals(parent) && e.attributeValue(pId).equals(pVal)) list.add(e);
            getElements(e, parent, pId, pVal,list);
        }
	    return list;
	}
	
	/**
	 * ��ȡָ���ڵ�ָ�������µ�ָ���ڵ�ļ���
	 * @param parent ���ڽڵ�����
	 * @param pId ��������
	 * @param pVal ����ֵ
	 * @param key �ڵ�����
	 * @return
	 * @throws Exception
	 */
	public static List<Element> getElements(String parent,String pId,String pVal,String key) throws Exception{
		List<Element> pList = new ArrayList<Element>();
		List<Element> kList = new ArrayList<Element>();
		Element root = getRootElement();
		if(root.getName().equals(parent) && pVal.equals(root.attributeValue(pId))){
			pList.add(root);
		}
		getElements(root, parent, pId, pVal,pList);
		for(Element p : pList){
			getElements(p, key,kList);
		}
		return kList;
	}
	
	/**
	 * ����xml��Ԫ�ػ�ȡֵ
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public String getXMLValue(String name) throws Exception {
		Element root = getRootElement();
	    Map<String,String> map = new HashMap<String,String>();
	    getText(root,map);
	    return map.get(name);
	}
	
	/**
	 * ��ȡxml����Ԫ�ص�ȡֵ
	 * @param file
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getText(Element iter,Map<String,String> map){
		for (Iterator it=iter.elementIterator() ; it.hasNext();){
            Element e = (Element) it.next();
            map.put(e.getName(), e.getText().trim());
            getText(e,map);
        }
		return map;
	}
	
	/**
	 * java beanת��ΪXML�ַ���
	 * @param object Ҫת����bean����
	 * @param clazz ע��class��
	 * @param isAonn  �Ƿ��ע��
	 * @return ת���ɹ���xml�ַ���
	 */
	public static String toXml(Object object, Class<?> clazz, boolean isAonn) {
		XStream xstream = new XStream(new DomDriver());
		xstream.setMode(XStream.NO_REFERENCES);
		if (isAonn) {
			xstream.processAnnotations(clazz);
		}
		String result = xstream.toXML(object);
		return result;
	}
	
	/**
	 * xml�ַ���ת��Ϊjava bean����
	 * @param xml �����xml�ַ���
	 * @return ת���ɹ���java bean����
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromXml(String xml) {
		XStream xstream = new XStream();
		T result = (T) xstream.fromXML(xml);
		return result;
	}
	
	/**
	 * xml�ַ���ת��Ϊjava bean����
	 * @param xml �����xml�ַ���
	 * @param mapAlias ��������
	 * @return ת���ɹ���java bean����
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromXml(String xml, Map<String, Class<?>> mapAlias) {
		XStream xstream = new XStream();
		Set<String> keys = mapAlias.keySet();
		for (String key : keys) {
			xstream.alias(key, mapAlias.get(key));
		}
		T result = (T) xstream.fromXML(xml);
		return result;
	}
	
	/**
	 * ��ȡָ����ǩ��ָ������ֵ�ļ���
	 * @param key �ڵ�����
	 * @param name ����
	 * @return
	 * @throws Exception
	 */
	public static List<String> getXmlAttributeValue(String key,String name) throws Exception {
		 List<String> list =new ArrayList<String>();
		 List<Element> keyList = new ArrayList<Element>();
		 Element root = getRootElement();
		 if(root.getName().equals(key)) keyList.add(root);
		 getElements(root, key,keyList);
		 for(Element e : keyList){
			 list.add(e.attributeValue(name));
		 }
	     return  list;
	}
	
	/**
	 * ��ȡָ���ڵ�ָ�������µ�ָ���ڵ��ָ�����Եļ���
	 * @param parent ���ڽڵ�����
	 * @param pId ��������
	 * @param pVal ����ֵ
	 * @param key �ڵ�����
	 * @param keyId ��������
	 * @return
	 * @throws Exception
	 */
	public static List<String> getXmlAttributeValue(String parent,String pId,String pVal,String key,String keyId) throws Exception{
		List<String> list =new ArrayList<String>();
		List<Element> kList = getElements(parent,pId,pVal,key);
		for(Element k : kList){
			list.add(k.attributeValue(keyId));
		}
		return list;
	}
	
	
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		try{
			List<String> app = init("/MyXml.xml").getXmlAttributeValue("Items","text");
			//List<String> app = init("/MyXml.xml").getXmlAttributeValue("Items","id","systemmanagement","Item","text");
			for(String b : app){
				System.out.println(b);
			}
		}catch(Exception e){
			
		}
	}
	
}
