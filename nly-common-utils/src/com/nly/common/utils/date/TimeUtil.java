package com.nly.common.utils.date;

import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

public class TimeUtil {
	public static String toTimeString(long time) {
		TimeUtil t = new TimeUtil(time);
		int day = t.get(TimeUtil.DAY);
		int hour = t.get(TimeUtil.HOUR);
		int minute = t.get(TimeUtil.MINUTE);
		int second = t.get(TimeUtil.SECOND);
		StringBuilder sb = new StringBuilder();
		if (day > 0){
			sb.append(day).append("��");
		}
		if (hour > 0){
			sb.append(hour).append("ʱ");
		}
		if (minute > 0){
			sb.append(minute).append("��");
		}
		if (second > 0){
			sb.append(second).append("��");
		}
		return sb.toString();
	}
	
    /**
     * ʱ���ֶγ�������ʾ���롱
     */
    public final static int SECOND = 0;

    /**
     * ʱ���ֶγ�������ʾ���֡�
     */
    public final static int MINUTE = 1;

    /**
     * ʱ���ֶγ�������ʾ��ʱ��
     */
    public final static int HOUR = 2;

    /**
     * ʱ���ֶγ�������ʾ���족
     */
    public final static int DAY = 3;

    /**
     * ��������������ֵ
     */
    private final int[] maxFields = { 59, 59, 23, Integer.MAX_VALUE - 1 };

    /**
     * �������������Сֵ
     */
    private final int[] minFields = { 0, 0, 0, Integer.MIN_VALUE };

    /**
     * Ĭ�ϵ��ַ�����ʽʱ��ָ���
     */
    private String timeSeparator = ":";

    /**
     * ʱ����������
     */
    private int[] fields = new int[4];

    /**
     * �޲ι��죬�����ֶ���Ϊ 0
     */
    public TimeUtil() {
        this(0, 0, 0, 0);
    }

    /**
     * ʹ��ʱ���ֹ���һ��ʱ��
     * @param hour      Сʱ
     * @param minute    ����
     */
    public TimeUtil(int hour, int minute) {
        this(0, hour, minute, 0);
    }

    /**
     * ʹ��ʱ���֡��빹��һ��ʱ��
     * @param hour      Сʱ
     * @param minute    ����
     * @param second    ��
     */
    public TimeUtil(int hour, int minute, int second) {
        this(0, hour, minute, second);
    }

    /**
     * ʹ��һ���ַ�������ʱ��<br/>
     * Time time = new Time("14:22:23");
     * @param time      �ַ�����ʽ��ʱ�䣬Ĭ�ϲ��á�:����Ϊ�ָ���
     */
    public TimeUtil(String time) {
        this(time, null);
//    	System.out.println(time);
    }

    /**
     * ʹ��ʱ����빹��ʱ��
     * @param time
     */
    public TimeUtil(long time){
    	this(new Date(time));
    }
    
    /**
     * ʹ�����ڶ�����ʱ��
     * @param date
     */
    public TimeUtil(Date date){
    	this(DateFormatUtils.formatUTC(date, "HH:mm:ss"));
    }

    /**
     * ʹ���졢ʱ���֡��빹��ʱ�䣬����ȫ�ַ��Ĺ���
     * @param day       ��
     * @param hour      ʱ
     * @param minute    ��
     * @param second    ��
     */
    public TimeUtil(int day, int hour, int minute, int second) {
        initialize(day, hour, minute, second);
    }

    /**
     * ʹ��һ���ַ�������ʱ�䣬ָ���ָ���<br/>
     * Time time = new Time("14-22-23", "-");
     * @param time      �ַ�����ʽ��ʱ��
     */
    public TimeUtil(String time, String timeSeparator) {
        if(timeSeparator != null) {
            setTimeSeparator(timeSeparator);
        }
        parseTime(time);
    }

    /**
     * ����ʱ���ֶε�ֵ
     * @param field     ʱ���ֶγ���
     * @param value     ʱ���ֶε�ֵ
     */
    public void set(int field, int value) {
        if(value < minFields[field]) {
            throw new IllegalArgumentException(value + ", time value must be positive.");
        }
        fields[field] = value % (maxFields[field] + 1);
        // ���н�λ����
        int carry = value / (maxFields[field] + 1);
        if(carry > 0) {
            int upFieldValue = get(field + 1);
            set(field + 1, upFieldValue + carry);
        }
    }

    /**
     * ���ʱ���ֶε�ֵ
     * @param field     ʱ���ֶγ���
     * @return          ��ʱ���ֶε�ֵ
     */
    public int get(int field) {
        if(field < 0 || field > fields.length - 1) {
            throw new IllegalArgumentException(field + ", field value is error.");
        }
        return fields[field];
    }

    /**
     * ��ʱ����С��ӡ����㣬������һ��ʱ��
     * @param time      ��Ҫ�ӵ�ʱ��
     * @return          ������ʱ��
     */
    public TimeUtil addTime(TimeUtil time) {
    	TimeUtil result = new TimeUtil();
        int up = 0;     // ��λ��־
        for (int i = 0; i < fields.length; i++) {
            int sum = fields[i] + time.fields[i] + up;
            up = sum / (maxFields[i] + 1);
            result.fields[i] = sum % (maxFields[i] + 1);
        }
        return result;
    }

    /**
     * ��ʱ����С��������㣬����ȥһ��ʱ��
     * @param time      ��Ҫ����ʱ��
     * @return          ������ʱ��
     */
    public TimeUtil subtractTime(TimeUtil time) {
    	TimeUtil result = new TimeUtil();
        int down = 0;       // ��λ��־
        for (int i = 0, k = fields.length - 1; i < k; i++) {
            int difference = fields[i] + down;
            if (difference >= time.fields[i]) {
                difference -= time.fields[i];
                down = 0;
            } else {
                difference += maxFields[i] + 1 - time.fields[i];
                down = -1;
            }
            result.fields[i] = difference;
        }
        result.fields[DAY] = fields[DAY] - time.fields[DAY] + down;
        return result;
    }

    /**
     * ���ʱ���ֶεķָ���
     * @return
     */
    public String getTimeSeparator() {
        return timeSeparator;
    }

    /**
     * ����ʱ���ֶεķָ����������ַ�����ʽ��ʱ�䣩
     * @param timeSeparator     �ָ����ַ���
     */
    public void setTimeSeparator(String timeSeparator) {
        this.timeSeparator = timeSeparator;
    }

    private void initialize(int day, int hour, int minute, int second) {
        set(DAY, day);
        set(HOUR, hour);
        set(MINUTE, minute);
        set(SECOND, second);
    }

    private void parseTime(String time) {
        if(time == null) {
            initialize(0, 0, 0, 0);
            return;
        }
        String t = time;
        int field = DAY;
        set(field--, 0);
        int p = -1;
        while((p = t.indexOf(timeSeparator)) > -1) {
            parseTimeField(time, t.substring(0, p), field--);
            t = t.substring(p + timeSeparator.length());
        }
        parseTimeField(time, t, field--);
    }

    private void parseTimeField(String time, String t, int field) {
        if(field < SECOND || t.length() < 1) {
            parseTimeException(time);
        }
        char[] chs = t.toCharArray();
        int n = 0;
        for(int i = 0; i < chs.length; i++) {
            if(chs[i] <= ' ') {
                continue;
            }
            if(chs[i] >= '0' && chs[i] <= '9') {
                n = n * 10 + chs[i] - '0';
                continue;
            }
            parseTimeException(time);
        }
        set(field, n);
    }

    private void parseTimeException(String time) {
        throw new IllegalArgumentException(time + ", time format error, HH"
                + this.timeSeparator + "mm" + this.timeSeparator + "ss");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(fields[DAY]).append(',').append(' ');
        buildString(sb, HOUR).append(timeSeparator);
        buildString(sb, MINUTE).append(timeSeparator);
        buildString(sb, SECOND);
        return sb.toString();
    }

    private StringBuilder buildString(StringBuilder sb, int field) {
        if(fields[field] < 10) {
            sb.append('0');
        }
        return sb.append(fields[field]);
    }

    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + Arrays.hashCode(fields);
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final TimeUtil other = (TimeUtil) obj;
        if (!Arrays.equals(fields, other.fields)) {
            return false;
        }
        return true;
    }
    
}
