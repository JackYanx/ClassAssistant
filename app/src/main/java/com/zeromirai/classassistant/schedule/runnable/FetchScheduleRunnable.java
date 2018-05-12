package com.zeromirai.classassistant.schedule.runnable;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.zeromirai.android.network.httpclient.BaseHttpClient;
import com.zeromirai.android.network.httpclient.BaseHttpConnection;
import com.zeromirai.android.text.RegexUtils;
import com.zeromirai.android.util.ZRLog;
import com.zeromirai.classassistant.schedule.Config;
import com.zeromirai.classassistant.common.cache.LocalCache;

import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by initialize on 2018/5/12.
 */

public class FetchScheduleRunnable implements Runnable {

    public static final String TAG = FetchScheduleRunnable.class.getName();

    private Context mContext;
    private Handler mHandler;
    private String sno;
    private String password;

    public FetchScheduleRunnable(Context context, Handler handler,String[] args){
        mContext = context;
        mHandler = handler;
        sno = args[0];
        password = args[1];
    }

    @Override
    public void run(){
        ZRLog.d(TAG,"LoginRunnableStarting");
        //此变量记录登录进度
        int processCode = 1;
        try{
            /*验证学号是否为空*/
            if(TextUtils.isEmpty(sno)){
                throw new Exception();
            }
            processCode = 2;

            /*验证学号是否合法*/
            if(!RegexUtils.isLegalSNO(sno)){
                throw new Exception();
            }
            processCode = 3;

            /*验证密码输入是否为空*/
            if(TextUtils.isEmpty(password)){
                throw new Exception();
            }
            processCode = 4;

            /*验证密码输入是否合法*/
            if(password.length() < 5){
                throw new Exception();
            }
            processCode = 5;

            /*检查网络连接*/
            if(!BaseHttpClient.isNetworkAvailable(mContext)){
                ZRLog.d(TAG, "Network is not available.Please check connection or permission about Internet");
                throw new Exception();
            }
            processCode = 6;

            /*构造参数和HttpConnection实例*/
            SortedMap<String,String> params = new TreeMap<>();
            params.put("userName",sno);
            params.put("password",password);
            params.put("type","xs");
            BaseHttpConnection connection = new BaseHttpConnection();
            connection.setInstanceCookieStorage(true);
            connection.setInstanceFollowRedirects(false);
            BaseHttpConnection.ResponseEntity s,c1,c2,c3,c4,c5;
            processCode = 7;

            /*登录操作*/
            s = connection.post("http://sso.jwc.whut.edu.cn//Certification//login.do",params);
            processCode = 8;

            /*判断返回内容是否为空*/
            if(TextUtils.isEmpty(s.htmlBody)){
                throw new Exception();
            }
            processCode = 9;

            /*判断返回信息是否有错误*/
            if(s.htmlBody.contains("用户名或密码错误")){
                throw new Exception();
            }
            processCode = 10;

            /*判断返回内容是否正常*/
            if(!s.htmlBody.contains("毕业设计")){
                ZRLog.e(TAG,"jwcLogin return contents ERROR:\n"+s.htmlBody);
                throw new Exception();
            }
            processCode = 11;

            /*登录子系统*/
            c1 = connection.get("http://202.114.90.180/Course/");
            //System.out.println("rdUrl1: "+c1.redirectUrl);
            c2 = connection.get(c1.redirectUrl);
            //System.out.println("rdUrl2: "+c2.redirectUrl);
            c3 = connection.get(c2.redirectUrl);
            //System.out.println("rdUrl3: "+c3.redirectUrl);
            c4 = connection.get(c3.redirectUrl);
            //System.out.println(c4.htmlBody);
            processCode = 12;

            /*获取个人课表*/
            c5 = connection.get("http://202.114.90.180/Course/grkbList.do");
            processCode = 13;

            /*判断返回内容是否为空*/
            if(TextUtils.isEmpty(c5.htmlBody)){
                throw new Exception();
            }
            processCode = 14;

            /*判断是否登录超时*/
            if(c5.htmlBody.contains("登录超时")){
                ZRLog.e(TAG,"jwcLogin logic ERROR");
                throw new Exception();
            }
            processCode = 15;

            /*判断返回内容是否正常*/
            if(!c5.htmlBody.contains("学年学期")){
                ZRLog.e(TAG,"jwc grkb return contents ERROR:\n"+c5.htmlBody);
                throw new Exception();
            }
            processCode = 16;

            /*开始解析html内容*/
            String[][][] re = parseSchedule(c5.htmlBody);
            processCode = 17;

            /*转换为JSONArray 实例*/
            JSONArray jsonArray = toJSONArray(re);
            processCode = 18;

            /*存储在SharedPreference中*/
            LocalCache localCache = new LocalCache(mContext);
            localCache.clearScheduleData();
            localCache.setScheduleData(jsonArray.toString());
            processCode = 19;

            /*缓存完成后将课表发送至服务器,方便班委查阅*/
            //TODO:...
            processCode = 20;

            processCode = 0;
        }catch (Exception e){
            ZRLog.d(TAG, Config.ERRMSG[processCode] + e.getMessage());
        }finally {
            sendMessage(processCode,Config.ERRMSG[processCode]);
            ZRLog.d(TAG,"FetchScheduleRunnable");
        }
    }

    private static JSONArray toJSONArray(String[][][] arr){
        if(arr == null)return null;
        JSONArray jsonArray = new JSONArray();
        for(int i = 0;i < arr.length;i++){
            JSONArray jsonArray1 = new JSONArray();
            for(int j = 0;j < arr[i].length;j++){
                JSONArray jsonArray2 = new JSONArray();
                for(int k = 0;k < arr[i][j].length;k++){
                    jsonArray2.put(arr[i][j][k]);
                }
                jsonArray1.put(jsonArray2);
            }
            jsonArray.put(jsonArray1);
        }
        return jsonArray;
    }

    private static String[][][] parseSchedule(String html){
        String threeD[][][] = new String[19][5][7];   //定义19周一天5节课 一周7天，横着存
        String arr[][] = new String[5][7];
        Document doc = Jsoup.parse(html);
        Elements trs = doc.select("table").select("tr"); // 关键的一步 从html中把课表解析出来

        // 显示全部一开始总的
        for (int i = 2; i < trs.size(); i++) { // 由于我的html，i=0、1中的内容不关于课表，因此从2开始
            Elements tds = trs.get(i).select("td");
            for (int j = 1; j < tds.size(); j++) {
                // String txt = tds.get(j).text();
                arr[i - 2][j - 1] = tds.get(j).text();
                //System.out.print(arr[i - 2][j - 1] + "		");
            }
            //System.out.println("	");
        }
        //System.out.println(" ");

        for (int i = 0; i < 5; i++) { // 一天五节课
            for (int j = 0; j < 7; j++) { // 一周七天
                if ("".equals(arr[i][j]))
                    continue;

                String[] sArray = arr[i][j].split("\\)");
                String schname = ""; // 用于存储 切割的是(JD)之类的情况
                for (String s : sArray) {
                    if ("".equals(s))
                        continue;
                    String strIndex = "周";
                    // 判断字符串A中字符串B方法：
                    if (s.indexOf(strIndex) == -1) { // 如果indexOf() 返回-1，表示不包含
                        schname = s;
                        continue;
                    } else {
                        if (!"".equals(schname)) {
                            s = schname + ")" + s; // 相加
                            schname = ""; // 置空
                        }

                        //利用正则表达式 ， 匹配指定范围内的数字
                        String regEx = "[^0-9]";

                        // Pattern是一个正则表达式经编译后的表现模式
                        Pattern p = Pattern.compile(regEx);

                        // 一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查。
                        Matcher m = p.matcher(s);

                        // 将输入的字符串中非数字部分用空格取代并存入一个字符串
                        String string = m.replaceAll(" ").trim();

                        // 以空格为分割符在讲数字存入一个字符串数组中
                        String[] strArr = string.split(" ");

                        // 遍历数组转换数据类型输出
                        int index = 0; // 记录周数是第几个
                        int num[] = new int[2];
                        for (String s2 : strArr) {
                            if ("".equals(s2))
                                continue;
                            num[index++] = Integer.parseInt(s2);
                            if (index == 2)
                                break; // 跳出循环，确定周数范围
                        }

                        for (int t = num[0] - 1; t < num[1]; t++) { // 处理同一个时间段，按单双周两门课的问题
                            String strIndex1 = "单";
                            String strIndex2 = "双";
                            if ((s.indexOf(strIndex1) == -1) && (s.indexOf(strIndex2) == -1)) {
                                threeD[t][i][j] = s + ")";
                            } else if ((s.indexOf(strIndex1) != -1 && t % 2 == 0)
                                    || ((s.indexOf(strIndex2) != -1) && t % 2 == 1)) { // 如果indexOf() 返回-1，表示不包含
                                threeD[t][i][j] = s + ")";
                            }
                        }//end for
                    }
                }
            }
        }

//
//        //显示每一周的课表
//        for (int z = 0; z < 18; z++) {
//            System.out.println("第" + (z + 1) + "周");
//            for (int y = 0; y < 5; y++) {
//                for (int x = 0; x < 7; x++) {
//                    System.out.print(threeD[z][y][x] + "   --|--   ");
//                }
//                System.out.println("");
//            }
//            System.out.println("");
//        }
        return threeD;
    }
    public void sendMessage(int what, Object object) {
        Message message = mHandler.obtainMessage();
        message.what = what;
        message.obj = object;
        mHandler.sendMessage(message);
    }
}
