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
 * MAC地址工具
 * 
 */
public class MacUtil {

	/**
	 * 获取当前操作系统名称. return 操作系统名称 例如:windows,Linux,Unix等.
	 */
	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}

	/**
	 * 根据ip地址获取MAC地址
	 * 
	 * @created 2017-3-6 上午10:14:38
	 * @param ia
	 * @return
	 * @throws SocketException
	 */
	public static String getLocalMac(InetAddress ia) throws SocketException {
		// 获取网卡，获取地址
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		StringBuffer sb = new StringBuffer("");
		if (mac != null && mac.length > 0) {
			for (int i = 0; i < mac.length; i++) {
				if (i != 0) {
					sb.append("-");
				}
				// 字节转换为整数
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
	 * 获取mac地址
	 * 
	 * @created 2017-3-6 上午10:17:18
	 * 
	 * @return mac地址集合（多网卡）
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
	 * 获取本机的IP地址
	 * 
	 * @created 2017-3-6 上午10:15:19
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
	 * 获取linux主机名
	 * 
	 * @created 2017-3-6 上午11:21:42
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
	 * 获取主机名
	 * 
	 * @created 2017-3-6 上午11:21:52
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
