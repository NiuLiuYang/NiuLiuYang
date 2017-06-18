package com.nly.common.utils.base;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class MathUtil {

	private MathUtil() {
	}

	// Ĭ�����㾫��
	private static int DEF_SCALE = 10;

	/**
	 * �ṩ��������ת��ΪBigDecimal
	 * 
	 * @param object ԭʼ����
	 * @return BigDecimal
	 */
	public static final BigDecimal bigDecimal(Object object) {
		if (object == null) {
			throw new NullPointerException();
		}
		BigDecimal result;
		try {
			result = new BigDecimal(String.valueOf(object).replaceAll(",", ""));
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Please give me a numeral.Not " + object);
		}
		return result;
	}

	/**
	 * �ṩ(���)��ȷ�ļӷ����㡣
	 * 
	 * @param num1 ������
	 * @param num2 ����
	 * @return ���������ĺ�
	 */
	public static final Double add(Object num1, Object num2) {
		BigDecimal result = bigDecimal(num1).add(bigDecimal(num2));
		return result.setScale(DEF_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * �ṩ(���)��ȷ�ļ������㡣
	 * 
	 * @param num1 ������
	 * @param num2 ����
	 * @return ���������Ĳ�
	 */
	public static final Double subtract(Object num1, Object num2) {
		BigDecimal result = bigDecimal(num1).subtract(bigDecimal(num2));
		return result.setScale(DEF_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * �ṩ(���)��ȷ�ĳ˷����㡣
	 * 
	 * @param num1 ������
	 * @param num2 ����
	 * @return ���������Ļ�
	 */
	public static final Double multiply(Object num1, Object num2) {
		BigDecimal result = bigDecimal(num1).multiply(bigDecimal(num2));
		return result.setScale(DEF_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * �ṩ(���)��ȷ�ĳ������㣬�����������������ʱ������Ϊ10λ���Ժ�������������롣
	 * 
	 * @param num1 ������
	 * @param num2 ����
	 * @return ������������
	 */
	public static final Double divide(Object num1, Object num2) {
		return divide(num1, num2, DEF_SCALE);
	}

	/**
	 * �ṩ(���)��ȷ�ĳ������㡣 �����������������ʱ����scale����ָ�����ȣ��Ժ�������������롣
	 * 
	 * @param num1 ������
	 * @param num2 ����
	 * @param scale ��ʾ��ʾ��Ҫ��ȷ��С�����Ժ�λ��
	 * @return ������������
	 */
	public static final Double divide(Object num1, Object num2, Integer scale) {
		if (scale == null) {
			scale = DEF_SCALE;
		}
		num2 = num2 == null || Math.abs(new Double(num2.toString())) == 0 ? 1 : num2;
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal result = bigDecimal(num1).divide(bigDecimal(num2), scale, BigDecimal.ROUND_HALF_UP);
		return result.doubleValue();
	}

	/**
	 * �ṩ��ȷ��С��λ�������봦��
	 * 
	 * @param num ��Ҫ�������������
	 * @param scale С���������λ
	 * @return ���������Ľ��
	 */
	public static final Double round(Object num, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal result = bigDecimal(num).divide(bigDecimal("1"), scale, BigDecimal.ROUND_HALF_UP);
		return result.doubleValue();
	}

	/**
	 * ��ȡstart��end����������,������start+end
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static final BigDecimal getRandom(int start, int end) {
		return new BigDecimal(start + Math.random() * end);
	}

	/**
	 * ��ʽ��
	 * 
	 * @param obj
	 * @param pattern
	 * @return
	 */
	public static final String format(Object obj, String pattern) {
		if (obj == null) {
			return null;
		}
		if (pattern == null || "".equals(pattern)) {
			pattern = "#";
		}
		DecimalFormat format = new DecimalFormat(pattern);
		return format.format(bigDecimal(obj));
	}

	/** �Ƿ����� */
	public static final boolean isNumber(Object object) {
		Pattern pattern = Pattern.compile("\\d+(.\\d+)?$");
		return pattern.matcher(object.toString()).matches();
	}

	public static final void main(String[] args) {
		System.out.println(add(1.000001, 2.10));
	}
	
}
