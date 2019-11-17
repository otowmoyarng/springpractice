package jp.co.product.system.app.controller;

import jp.co.product.system.common.SystemLogger;

public abstract class ProductBaseConroller {

	protected SystemLogger logger = null;
	
	/**
	 * コンストラクタ
	 * ロガーを初期化する
	 */
	public ProductBaseConroller() {
		logger = new SystemLogger(getInnertype());
	}
	
	/**
	 * ロガーインスタンス取得
	 */
	protected abstract Class<?> getInnertype();
}
