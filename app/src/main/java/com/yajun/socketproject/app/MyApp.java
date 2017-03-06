package com.yajun.socketproject.app;

import cn.jpush.android.api.JPushInterface;

import com.activeandroid.ActiveAndroid;
import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;

/**
 * ���ڳ�ʼ��ImageLoader������ִ�г�ʼ��������ͼƬǰ���棬����Ǳ�ڿ�ָ������
 * @author Administrator
 *
 */
public class MyApp extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader(this);
		ActiveAndroid.initialize(this);
		//��ʼ���ٶȵ�ͼ
		SDKInitializer.initialize(this);
		initJPush(); 
		
	}

	private void initJPush() {
//		JPushInterface.setDebugMode(true);	// ���ÿ�����־,����ʱ��ر���־
        JPushInterface.init(this);     		// ��ʼ�� JPush
	}

	public void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCacheSize(50 * 1024 * 1024)
				// 50 Mb
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}
