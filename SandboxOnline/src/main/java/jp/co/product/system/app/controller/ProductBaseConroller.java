package jp.co.product.system.app.controller;

import jp.co.product.system.common.SystemLogger;

public abstract class ProductBaseConroller {

	protected SystemLogger logger = null;
	
	/**
	 * �R���X�g���N�^
	 * ���K�[������������
	 */
	public ProductBaseConroller() {
		logger = new SystemLogger(getInnertype());
	}
	
	/**
	 * ���K�[�C���X�^���X�擾
	 */
	protected abstract Class<?> getInnertype();
}
