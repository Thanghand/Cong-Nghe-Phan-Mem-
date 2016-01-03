package com.smartchef.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import com.smartchef.model.ValueComparator;

public class LoadConstant {
	public static String ACTION_TIME = "TIME";
	public static String ACTION_FESTIVAL = "FESTIVAL";
	public static String ACTION_ALL = "ALL";
	public static String ACTION_HOT = "HOT";
	public static String TITTLE_FAVORITE = "Favorite";
	public static String TITTLE_HISTORY = "History";
	public static String FOLLOWING = "Following";
	public static String FOLLOWER = "Follower";
	public static String STATUS_FOLLOW = "StatusFollow";
	public static String DIET = "diet";
	public static String VEGETABLEFOOD = "vegetablefood";
	public static String GAINWEIGHT = "gainweight";
	public static String WEIGHTLIFTING = "weightlifting";
	public static String ALLSEEN = "allSeen";

	public static TreeMap<String, Integer> SortByValue(Map<String, Integer> map) {
		ValueComparator vc = new ValueComparator(map);
		TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>(vc);
		sortedMap.putAll(map);

		return sortedMap;
	}

	public static String getIPAddress() {
		Enumeration<NetworkInterface> nets;
		String ipAdress = "";
		String wireLessAdapter = "Wireless Network";
		try {
			nets = NetworkInterface.getNetworkInterfaces();
			for (NetworkInterface netint : Collections.list(nets)) {
				if (netint.getDisplayName().contains(wireLessAdapter)) {
					Enumeration<InetAddress> inetAddresses = netint
							.getInetAddresses();
					for (InetAddress inetAddress : Collections
							.list(inetAddresses)) {
						ipAdress = inetAddress.toString();
						break;
					}
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ipAdress;
	}
}
