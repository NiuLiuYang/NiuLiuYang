package com.nly.common.utils.properties;

import java.util.Date;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class PropertiesUtil {
	// ���򿪶����Դ�ļ�ʱ��������Դ�ļ�
    private static HashMap<String, PropertiesUtil> configMap = new HashMap<String, PropertiesUtil>();
    // ���ļ�ʱ�䣬�жϳ�ʱʹ��
    private Date loadTime = null;
    // ��Դ�ļ�
    private ResourceBundle resourceBundle = null;
    // Ĭ����Դ�ļ�����
    private static final String NAME = "config";
    // ����ʱ��
    private static final Integer TIME_OUT = 60 * 1000;

    // ˽�й��췽������������
    private PropertiesUtil(String name) {
        this.loadTime = new Date();
        this.resourceBundle = ResourceBundle.getBundle(name);
    }

    public static synchronized PropertiesUtil getInstance() {
        return getInstance(NAME);
    }

    public static synchronized PropertiesUtil getInstance(String name) {
        PropertiesUtil conf = configMap.get(name);
        if (null == conf) {
            conf = new PropertiesUtil(name);
            configMap.put(name, conf);
        }
        // �ж��Ƿ�򿪵���Դ�ļ��Ƿ�ʱ1����
        if ((new Date().getTime() - conf.getLoadTime().getTime()) > TIME_OUT) {
            conf = new PropertiesUtil(name);
            configMap.put(name, conf);
        }
        return conf;
    }

    // ����key��ȡvalue
    public String get(String key) {
        try {
            String value = resourceBundle.getString(key);
            return value;
        }catch (MissingResourceException e) {
            return "";
        }
    }

    // ����key��ȡvalue(����)
    public Integer getInt(String key) {
        try {
            String value = resourceBundle.getString(key);
            return Integer.parseInt(value);
        }catch (MissingResourceException e) {
            return null;
        }
    }

    // ����key��ȡvalue(����)
    public boolean getBool(String key) {
        try {
            String value = resourceBundle.getString(key);
            if ("true".equals(value)) {
                return true;
            }
            return false;
        }catch (MissingResourceException e) {
            return false;
        }
    }

    public Date getLoadTime() {
        return loadTime;
    }
}
