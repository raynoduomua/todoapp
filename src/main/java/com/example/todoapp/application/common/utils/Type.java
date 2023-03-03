package com.example.todoapp.application.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class Type {

	/**
	 * 性別Map生成
	 * @return 性別Map
	 */
	public static Map<String, Integer> getGenderMap() {

		Map<String, Integer> genderMap = new LinkedHashMap<>();

		genderMap.put("男性", 1);
		genderMap.put("女性", 2);
		genderMap.put("その他", 3);
		genderMap.put("選択しない", 4);

		return genderMap;
	}

}
