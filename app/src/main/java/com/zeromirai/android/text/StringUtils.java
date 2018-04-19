package com.zeromirai.android.text;

/**
 * Created by initialize on 2018/4/8.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by ASUS on 2018/3/10.
 */
public final class StringUtils {
    /*限制读入文件最大大小为512KB*/
    private static final int FILE_SIZE_LIMIT = 0x100000;
    public static int saveStringToFile(String absolutePath,String src) throws IOException {
        if(absolutePath == null || absolutePath.length() < 3 || src == null || src.length() < 1)
            return -1;
        FileWriter fw = new FileWriter(absolutePath);
        BufferedWriter bufw = new BufferedWriter(fw);
        bufw.write(src);
        bufw.flush();
        bufw.close();
        return 0;
    }
    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }
    public static String readStringFromFile(String absolutePath,String charSet) throws IOException{
        if(absolutePath == null || absolutePath.length() < 3)
            return "WRONG_PATH";
        File tFile = new File(absolutePath);
        if(!tFile.exists() || tFile.isDirectory() || tFile.length() > FILE_SIZE_LIMIT){
            return "TOO BIG FILE!";
        }
        if(!"GBK".equalsIgnoreCase(charSet) && !"UTF8".equalsIgnoreCase(charSet)){
            return "UNSUPPORTED_CHARSET";
        }
        BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream(absolutePath),charSet));
        String singleLine = null;
        StringBuffer stringBuffer = new StringBuffer();
        char[] charBuffer=new char[32];
        int ci = 0;
        do{
            ci = bufr.read(charBuffer);
            stringBuffer.append(charBuffer);
        }while(ci > 1);
        bufr.close();
        return stringBuffer.toString();
    }
}

