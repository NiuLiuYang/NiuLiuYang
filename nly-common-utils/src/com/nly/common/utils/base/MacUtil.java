package com.nly.common.utils.base;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * MAC��ַ����
 * 
 */
public class MacUtil {

	/**
	 * ��ȡ��ǰ����ϵͳ����. return ����ϵͳ���� ����:windows,Linux,Unix��.
	 */
	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}

	/**
	 * ����ip��ַ��ȡMAC��ַ
	 * 
	 * @created 2017-3-6 ����10:14:38
	 * @param ia
	 * @return
	 * @throws SocketException
	 */
	public static String getLocalMac(InetAddress ia) throws SocketException {
		// ��ȡ��������ȡ��ַ
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		StringBuffer sb = new StringBuffer("");
		if (mac != null && mac.length > 0) {
			for (int i = 0; i < mac.length; i++) {
				if (i != 0) {
					sb.append("-");
				}
				// �ֽ�ת��Ϊ����
				int temp = mac[i] & 0xff;
				String str = Integer.toHexString(temp);
				if (str.length() == 1) {
					sb.append("0" + str);
				} else {
					sb.append(str);
				}
			}
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * ��ȡmac��ַ
	 * 
	 * @created 2017-3-6 ����10:17:18
	 * 
	 * @return mac��ַ���ϣ���������
	 */
	public static List<String> getMac() {
		List<String> macs = new ArrayList<String>();
		try {
			InetAddress[] ias;
			List<InetAddress> address = getIPAddress();
			for (InetAddress ip : address) {
				ias = InetAddress.getAllByName(ip.getHostAddress());
				for (InetAddress ia : ias) {
					String mac = getLocalMac(ia);
					if (mac != null && mac.length() > 0) {
						macs.add(mac);
					}
				}
			}
		} catch (UnknownHostException e) {
			 LogUtil.error(e.getMessage());
		} catch (SocketException e) {
			 LogUtil.error(e.getMessage());
		}
		return macs;
	}

	/**
	 * ��ȡ������IP��ַ
	 * 
	 * @created 2017-3-6 ����10:15:19
	 * @return
	 * @throws SocketException
	 */
	public static List<InetAddress> getIPAddress() throws SocketException {
		Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface
				.getNetworkInterfaces();
		InetAddress ip = null;
		List<InetAddress> address = new ArrayList<InetAddress>();
		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
					.nextElement();
			Enumeration<InetAddress> addresses = netInterface
					.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address) {
					address.add(ip);
				}
			}
		}
		return address;
	}

	/**
	 * ��ȡlinux������
	 * 
	 * @created 2017-3-6 ����11:21:42
	 * @return
	 */
	public static String getHostNameForLiunx() {
		try {
			return (InetAddress.getLocalHost()).getHostName();
		} catch (UnknownHostException uhe) {
			String host = uhe.getMessage(); // host = "hostname: hostname"
			if (host != null) {
				int colon = host.indexOf(':');
				if (colon > 0) {
					return host.substring(0, colon);
				}
			}
			return "UnknownHost";
		}
	}

	/**
	 * ��ȡ������
	 * 
	 * @created 2017-3-6 ����11:21:52
	 * @return
	 */
	public static String getHostName() {
		if (System.getenv("COMPUTERNAME") != null) {
			return System.getenv("COMPUTERNAME");
		} else {
			return getHostNameForLiunx();
		}
	}
	
}
