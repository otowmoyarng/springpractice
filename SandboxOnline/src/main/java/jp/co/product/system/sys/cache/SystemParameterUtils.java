package jp.co.product.system.sys.cache;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public final class SystemParameterUtils {

	private static Map<String, Object> cache;
	
//	static {
//		cache = new HashMap<String, Object>();
//		cache.put("search_count", 100);
//		cache.put("csv_count", 1000);
//		cache.put("search_time", 60);
//	}
	
	/**
	 * パラメータマスタをキャッシュする
	 */
	public static void initializeCache() {
		
		System.out.println("キャッシュ開始");
		cache = new HashMap<String, Object>();
		cache.put("search_count", 100);
		cache.put("csv_count", 1000);
		cache.put("search_time", 60);
		System.out.println("キャッシュ終了");
		System.out.println("キャッシュ件数：" + cache.size());
	}

	/**
	 * パラメーター文字列を取得する
	 * @param	paramcd	パラメーターコ－ド
	 * @return	パラメーター文字列
	 */
	public static String getPString(String paramcd) {
		// パラメーターコードがキャッシュに存在しない場合
		// パラメーターコードから取得した型がStringでない場合
		System.out.println("実行メソッド：getPString");
		System.out.println("キャッシュ件数：" + cache.size());
		return cache.get(paramcd).toString();
	}

	/**
	 * パラメーター数値を取得する
	 * @param	paramcd	パラメーターコ－ド
	 * @return	パラメーター数値
	 */
	public static BigDecimal getPNumber(String paramcd) {
		// パラメーターコードがキャッシュに存在しない場合
		// パラメーターコードから取得した型がStringでない場合
		System.out.println("実行メソッド：getPNumber");
		System.out.println("キャッシュ件数：" + cache.size());
		return new BigDecimal(cache.get(paramcd).toString());
	}
}
