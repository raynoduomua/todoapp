package com.example.todoapp.application.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class Selection {

	/**
	 * 選択肢Map生成
	 * @return 選択肢Map
	 */
	public static Map<String, Integer> getSelectMap() {

		Map<String, Integer> selectMap = new LinkedHashMap<>();

		selectMap.put("未完了", 1);
		selectMap.put("完了", 2);
		selectMap.put("全て", 3);

		return selectMap;
	}

	/**
	 * ユーザ情報更新選択肢Map
	 * @return ユーザ情報変更Map
	 */
	public static Map<String, Integer> changeInfoMap() {

		Map<String, Integer> selectMap = new LinkedHashMap<>();

		selectMap.put("ユーザー名変更", 10);
		selectMap.put("メールアドレス変更", 20);
		selectMap.put("パスワード変更", 30);
		selectMap.put("性別変更", 40);

		return selectMap;
	}

}
