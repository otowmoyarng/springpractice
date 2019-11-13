package jp.co.product.system.sys.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public class SystemParameterCache {

	Map<String, Object> map = null;
	
	SystemParameterCache() {
		map = new HashMap<String, Object>();
		for (int counter = 1; counter <= 5; counter++)
			map.put("key" + counter, counter);
	}
	
	@Cacheable(value = "SystemParameter", key = "#key")
	public Object getParam(String key) {
		return getParam(key, false);
	}
	
	@Cacheable(value = "SystemParameter", key = "#key")
	public Object getParam(String key, boolean iscacheupdate) {
		return map.get(key);
	}
	
	@CacheEvict(value = "SystemParameter", key = "#key")
	private void updateCache(String key) {
		map.put(key, getRealValue(key));
	}
	
	private Object getRealValue(String key) {
		return Math.random();
	}
}
