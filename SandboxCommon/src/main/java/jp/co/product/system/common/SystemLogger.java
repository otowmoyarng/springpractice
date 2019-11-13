/**
 * 
 */
package jp.co.product.system.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ログ出力共通クラス
 * 
 */
public class SystemLogger {

	private Logger logger;
	
	/**
	 * コンストラクタ
	 * 
	 * @param	cls	
	 * @see	org.slf4j.LoggerFactory#getLogger(Class)
	 */
	public SystemLogger(Class<?> cls) {
		logger = LoggerFactory.getLogger(cls);
	}
	
	/**
	 * エラー出力
	 * 
	 * @param	msg	ログ出力内容
	 * @see	org.slf4j.Logger#error(String)
	 */
	public void error(String msg) {
		logger.error(msg);
	}
	
	/**
	 * インフォメーション出力
	 * 
	 * @param	msg	ログ出力内容
	 * @see	org.slf4j.Logger#error(String)
	 */
	public void info(String msg) {
		logger.info(msg);
	}
	
	/**
	 * デバッグ出力
	 * 
	 * @param	msg	ログ出力内容
	 * @see	org.slf4j.Logger#error(String)
	 */
	public void debug(String msg) {
		if (logger.isDebugEnabled())
			logger.debug(msg);
	}
}
