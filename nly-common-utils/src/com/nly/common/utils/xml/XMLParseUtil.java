package com.nly.common.utils.xml;

import java.io.File;  
import java.io.IOException;  
import java.io.InputStream;  
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;  
import javax.xml.xpath.XPath;  
import javax.xml.xpath.XPathConstants;  
import javax.xml.xpath.XPathExpression;  
import javax.xml.xpath.XPathExpressionException;  
import javax.xml.xpath.XPathFactory;  

import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;  
import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;  
import org.xml.sax.SAXException; 

/**
 * ����XML�Ĺ����࣬ʹ��XPath
 * @author nly
 *
 */
public class XMLParseUtil {

	private DocumentBuilder builder;  
    private XPath xpath;  
    
    /**
     * ��ȡDocument����
     * @return
     * @throws IOException
     * @throws SAXException
     */
    public Document getDocument(String file) throws IOException, SAXException{
    	Document doc = null;
		ClassPathResource resource = new ClassPathResource(file); 
		String path = resource.getURL().getPath().replace("%20", "");
		doc = parseDocument(path);
		return doc;
    }
  
    /** 
     * Ĭ�Ϲ��캯�� 
     *  
     * @throws ParserConfigurationException 
     *             ����XML���������� 
     */  
    public XMLParseUtil() throws ParserConfigurationException {  
        DocumentBuilderFactory domfactory = DocumentBuilderFactory  
                .newInstance();  
        builder = domfactory.newDocumentBuilder();  
        XPathFactory xpfactory = XPathFactory.newInstance();  
        xpath = xpfactory.newXPath();  
    }  
  
    /** 
     * ����·������XML�ĵ� 
     *  
     * <pre> 
     * ���ܲ�����û����ʽ�׳����쳣�� 
     * 1) MalformedURLException �������ļ�·�������Ҳ���Ҫ�������ļ��� 
     * 2) SAXParseException �� �ļ���ʽ�����޷������� 
     * </pre> 
     *  
     * @param path 
     *            �ļ�·�� 
     * @return Document���� 
     * @throws IOException 
     *             IO�쳣 
     * @throws SAXException 
     *             SAX�쳣 
     * @see [�ࡢ��#��������#��Ա] 
     */  
    public Document parseDocument(String path) throws IOException, SAXException {  
        return builder.parse(path);  
    }  
  
    /** 
     * �����ļ�����XML�ĵ� 
     *  
     * <pre> 
     * ���ܲ�����û����ʽ�׳����쳣�� 
     * 1) IllegalArgumentException ��������������������Ϊ�գ� 
     * 2) SAXParseException �� �ļ���ʽ�����޷������� 
     * </pre> 
     *  
     * @param file 
     *            �ļ� 
     * @return Document���� 
     * @throws IOException 
     *             IO�쳣 
     * @throws SAXException 
     *             SAX�쳣 
     * @see [�ࡢ��#��������#��Ա] 
     */  
    public Document parseDocument(File file) throws IOException, SAXException {  
        return builder.parse(file);  
    }  
  
    /** 
     * ��������������XML�ĵ� 
     *  
     * <pre> 
     * ���ܲ�����û����ʽ�׳����쳣�� 
     * 1) IllegalArgumentException ��������������������Ϊ�գ� 
     * 2) SAXParseException �� �ļ���ʽ�����޷������� 
     * </pre> 
     *  
     * @param is 
     *            ������ 
     * @return Document���� 
     * @throws IOException 
     *             IO�쳣 
     * @throws SAXException 
     *             SAX�쳣 
     * @see [�ࡢ��#��������#��Ա] 
     */  
    public Document parseDocument(InputStream is) throws IOException,  
            SAXException {  
        return builder.parse(is);  
    }  
  
    /** 
     * ͨ��xpathȡ�ýڵ��б� 
     *  
     * @param node 
     *            �ڵ� 
     * @param expression 
     *            XPath���ʽ 
     * @return NodeList 
     * @throws XPathExpressionException 
     *             XPath���ʽ�쳣 
     * @see [�ࡢ��#��������#��Ա] 
     */  
    public NodeList selectNodes(Node node, String expression)  
            throws XPathExpressionException {  
        // XPath�������XPath���ʽ  
        XPathExpression xpexpreesion = this.xpath.compile(expression);  
        Object object = xpexpreesion.evaluate(node, XPathConstants.NODESET);  
        return (NodeList) object;  
    }  
  
    /** 
     * ͨ��xpathȡ�õ����ڵ� 
     *  
     * @param node 
     *            �ڵ� 
     * @param expression 
     *            XPath���ʽ 
     * @return Node 
     * @throws XPathExpressionException 
     *             XPath���ʽ�쳣 
     * @see [�ࡢ��#��������#��Ա] 
     */  
    public Node selectSingleNode(Node node, String expression)  
            throws XPathExpressionException {  
        XPathExpression xpexpreesion = this.xpath.compile(expression);  
        Object object = xpexpreesion.evaluate(node, XPathConstants.NODE);  
        return (Node) object;  
    }  
  
    /** 
     * ����xpathȡ�ýڵ���ı�ֵ 
     *  
     * @param node 
     *            �ڵ� 
     * @param expression 
     *            XPath���ʽ 
     * @return String 
     * @throws XPathExpressionException 
     *             XPath���ʽ�쳣 
     * @see [�ࡢ��#��������#��Ա] 
     */  
    public String getNodeStringValue(Node node, String expression)  
            throws XPathExpressionException {  
        XPathExpression xpexpreesion = this.xpath.compile(expression);  
        Object object = xpexpreesion.evaluate(node, XPathConstants.STRING);  
        return (String) object;  
    }  
    
    /**
     * ����
     * @param args
     */
    public static void main(String[] args) {
    	try {
			XMLParseUtil xmlParse = new XMLParseUtil();
			Document doc = xmlParse.getDocument("/MyXml.xml");
			NodeList itemsNodeList = (NodeList) xmlParse.selectNodes(doc, "//Menus/Items[Item/@id='newProjectForm']");
			System.out.println(""+itemsNodeList.getLength());
			for(int i = 0; i < itemsNodeList.getLength(); i++){
				String itemText = xmlParse.getNodeStringValue(itemsNodeList.item(i), "./Item/@text");
				System.out.println(itemText);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
