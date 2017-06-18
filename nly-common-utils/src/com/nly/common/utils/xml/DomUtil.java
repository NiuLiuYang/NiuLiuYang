package com.nly.common.utils.xml;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.nly.common.utils.base.LogUtil;

/**
 * 
 * @author NLY
 *
 */
public class DomUtil {
	/**
	 * doc2String ��xml�ĵ�����תΪString
	 * 
	 * @return �ַ���
	 * @param document
	 */
	public static String doc2String(Document document) {
		String s = "";
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			t.setOutputProperty("encoding", "GB2312");// ����������⣬�Թ���GBK����
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			t.transform(new DOMSource(document), new StreamResult(bos));
			s = bos.toString();
		} catch (Exception ex) {
			 
			LogUtil.error("xml�ĵ�ת���ַ�������", ex);
		}
		return s;
	}

	/**
	 * �ַ���ת��Doc
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Document string2Doc(String xmlStr) {
		Document document = null;
		try {
			StringReader sr = new StringReader(xmlStr);

			InputSource is = new InputSource(sr);

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();

			document = builder.parse(is);
			 
		} catch (Exception e) {
			LogUtil.error("�ַ���ת��xml�ĵ�����", e);
		}

		return document;
	}
}
