package com.zeromirai.android.storage;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.File;

/**
 * Created by initialize on 2018/4/8.
 */

public final class StorageUtils {
    public static final String TAG = "StorageUtils";
    public static String getExStoragePath() {
        return getExStorageDirectory().getAbsolutePath();
    }

    public static File getExStorageDirectory() {
        return Environment.getExternalStorageDirectory();
    }
    /**
     * 获得SD卡总大小
     *
     * @return
     */
    private static String getSDTotalSize(Context context) {
        File path = getExStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return Formatter.formatFileSize(context, blockSize * totalBlocks);
    }

    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @return
     */
    private static String getSDAvailableSize(Context context) {
        File path = getExStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(context, blockSize * availableBlocks);
    }

    /**
     * 判断ExternalStorage是否可读/写
     *
     * @return boolean
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 判断ExternalStorage是否可读
     *
     * @return
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


}
