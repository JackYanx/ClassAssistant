package com.zeromirai.android.share;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.widget.Toast;

import java.io.File;

/**
 * Created by initialize on 2018/4/9.
 */

public class SystemShare {
    public static boolean shareText(Context context, String str){
        Intent intent1=new Intent(Intent.ACTION_SEND);
        intent1.putExtra(Intent.EXTRA_TEXT,str);
        intent1.setType("text/plain");
        context.startActivity(Intent.createChooser(intent1,"ShareText"));
        return true;
    }

    public static boolean shareImage(Context context, String filePath){
        File file = new File(filePath);
        if (file.exists()) {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            share.setType(getMimeType(file.getAbsolutePath()));//此处可发送多种文件
            share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(Intent.createChooser(share, "ShareFile"));
        } else {
            Toast.makeText(context,"文件不存在,分享取消",Toast.LENGTH_SHORT);
            return false;
        }
        return true;
    }

    //获取文件MIME类型
    private static String getMimeType(String filePath) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        String mime = "*/*";
        if (filePath != null) {
            try {
                mmr.setDataSource(filePath);
                mime = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
            } catch (IllegalStateException e) {
                return mime;
            } catch (IllegalArgumentException e) {
                return mime;
            } catch (RuntimeException e) {
                return mime;
            }
        }
        return mime;
    }
}
