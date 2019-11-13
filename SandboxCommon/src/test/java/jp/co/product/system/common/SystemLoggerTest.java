package jp.co.product.system.common;

import org.junit.Test;

public class SystemLoggerTest {

	private static final SystemLogger log = new SystemLogger(SystemLoggerTest.class);

	@Test
	public void test() {
		log.debug("debug");
		log.info("Info");
		log.error("error");
	}
}
