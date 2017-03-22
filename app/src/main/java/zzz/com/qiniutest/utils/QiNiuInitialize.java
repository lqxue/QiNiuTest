package zzz.com.qiniutest.utils;

import com.qiniu.android.common.Zone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;

/**
 * 初始化七牛文件上传管理器
 */
public class QiNiuInitialize {

    private static UploadManager instance;

    //私有构造
    private QiNiuInitialize() {
    }

    //获取UploadManager
    public static UploadManager getSingleton() {
        if (instance == null) {
            synchronized (QiNiuInitialize.class) {
                if (instance == null) {
                    instance = new UploadManager(new Configuration.Builder()
                            .zone(Zone.zone0) // 设置区域为华北
                            .build());
                }
            }
        }
        return instance;
    }
}
