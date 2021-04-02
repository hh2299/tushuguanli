package com.admin.framework.cache;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LocalCacheManage implements CommandLineRunner{
 
	@Autowired private List<LocalCache> localCaches;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 应用启动初始化
	 */
	@Override
	public void run(String... args) throws Exception {
		// 1分钟刷新一次，时间可以自定义。这里作为心跳线程，定时刷新缓存
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(localCaches!=null && !localCaches.isEmpty()){
					for(LocalCache cache : localCaches){
						try {
							cache.refresh();
						} catch (Exception e) {
							logger.error("本地缓存更新异常", e);
						}
					}
				}
			}
		}, 0, 60000);
	}
	

}
