package com.fiap.iot.model;

import java.util.HashMap;

public class CallbackData {
	
	private static int count;
	
	public static HashMap<Integer, String> dadosMap = new HashMap<Integer, String>();
	
	public static void put(String msg) {
		dadosMap.put(count++, msg);
		System.out.println(dadosMap);
	}
}
