package com.admin.framework.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class CommonLocalCache implements LocalCache {

	private static ConcurrentHashMap<String, LocalCacheItem> cache = new ConcurrentHashMap<>();

	public static boolean containsKey(String key) {
		return cache.containsKey(key);
	}

	/**
	 * 缓存值
	 * 
	 * @author:
	 * @date: 2021年3月6日 下午5:24:20
	 * @param key
	 * @param value
	 */
	public static void put(String key, Object value) {
		LocalCacheItem item = new LocalCacheItem(0, System.currentTimeMillis(), value);
		cache.put(key, item);
	}
	
	public static void delete(String key) {
		if(containsKey(key)) {
			cache.remove(key);
		}
	}

	/**
	 * 缓存值-指定缓存时间
	 * 
	 * @author:
	 * @date: 2021年3月6日 下午5:35:05
	 * @param key
	 * @param value
	 * @param cacheTime 缓存时间
	 * @param unit      缓存时间单位
	 */
	public static void put(String key, Object value, long cacheTime, TimeUnit unit) {
		LocalCacheItem item = new LocalCacheItem(unit.toMillis(cacheTime), System.currentTimeMillis(), value);
		cache.put(key, item);
	}

	/**
	 * 获取值
	 * 
	 * @author:
	 * @date: 2021年3月6日 下午5:34:28
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(String key) {
		LocalCacheItem item = cache.get(key);
		if (item == null) {
			return null;
		}
		return (T) item.getValue();
	}

	@Override
	public void refresh() {
		for(String key : cache.keySet()){
			LocalCacheItem item = cache.get(key);
			long currentTime = System.currentTimeMillis();
			if(item.getCacheTime()>0 && currentTime - item.getCreateTime() > item.getCacheTime()) {
	       		cache.remove(key);
	       	}
		}
	}

}
