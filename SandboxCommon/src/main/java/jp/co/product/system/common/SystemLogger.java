/**
 * 
 */
package jp.co.product.system.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ���O�o�͋��ʃN���X
 * 
 */
public class SystemLogger {

	private Logger logger;
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param	cls	
	 * @see	org.slf4j.LoggerFactory#getLogger(Class)
	 */
	public SystemLogger(Class<?> cls) {
		logger = LoggerFactory.getLogger(cls);
	}
	
	/**
	 * �G���[�o��
	 * 
	 * @param	msg	���O�o�͓��e
	 * @see	org.slf4j.Logger#error(String)
	 */
	public void error(String msg) {
		logger.error(msg);
	}
	
	/**
	 * �C���t�H���[�V�����o��
	 * 
	 * @param	msg	���O�o�͓��e
	 * @see	org.slf4j.Logger#error(String)
	 */
	public void info(String msg) {
		logger.info(msg);
	}
	
	/**
	 * �f�o�b�O�o��
	 * 
	 * @param	msg	���O�o�͓��e
	 * @see	org.slf4j.Logger#error(String)
	 */
	public void debug(String msg) {
		if (logger.isDebugEnabled())
			logger.debug(msg);
	}
}
