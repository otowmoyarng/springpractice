package jp.co.product.system.batch.demo;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import jp.co.product.system.common.SystemLogger;


/**
 * Dummy {@link ItemWriter} which only logs data it receives.
 */
@Component("writer")
public class ExampleItemWriter implements ItemWriter<Object> {

	//private static final Log log = LogFactory.getLog(SystemLoggerExampleItemWriter.class);
	private static final SystemLogger log = new SystemLogger(ExampleItemWriter.class);
	
	/**
	 * @see ItemWriter#write(java.util.List)
	 */
	public void write(List<? extends Object> data) throws Exception {
//		log.info(data);
		log.debug("debug" + data.toString());
		log.info("Info" + data.toString());
		log.error("error" + data.toString());
	}
}
