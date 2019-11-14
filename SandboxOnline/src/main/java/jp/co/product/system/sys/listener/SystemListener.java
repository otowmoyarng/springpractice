package jp.co.product.system.sys.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jp.co.product.system.sys.cache.SystemParameterUtils;

public class SystemListener implements ServletContextListener {

	/**
	 * 
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("�V�X�e���N����");
		SystemParameterUtils.initializeCache();
	}

	/**
	 * 
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
